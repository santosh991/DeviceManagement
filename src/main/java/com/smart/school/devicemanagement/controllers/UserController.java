package com.smart.school.devicemanagement.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.smart.school.devicemanagement.auth.AccountAuth;
import com.smart.school.devicemanagement.auth.AccountRole;
import com.smart.school.devicemanagement.auth.AuthHelper;
import com.smart.school.devicemanagement.auth.AuthPassport;
import com.smart.school.devicemanagement.auth.AuthorityMenu;
import com.smart.school.devicemanagement.auth.PermissionMenu;
import com.smart.school.devicemanagement.common.BaseController;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageListUtil;
import com.smart.school.devicemanagement.models.Authority;
import com.smart.school.devicemanagement.models.RoleInfo;
import com.smart.school.devicemanagement.models.User;
import com.smart.school.devicemanagement.services.IUserService;
import com.smart.school.devicemanagement.web.domain.UserLoginModel;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	private final String strUserLoginModel = "userLoginModel";
	
	
	@RequestMapping(value="/login", method = {RequestMethod.GET})
    public String login(Model model) {
        if (!model.containsAttribute(strUserLoginModel)) {
        	model.addAttribute(strUserLoginModel, new UserLoginModel());
		}
        return "account/login";
    }
	
	@RequestMapping(value="/login", method = {RequestMethod.POST})
	public String login(HttpServletRequest request,Model model,@Valid @ModelAttribute(strUserLoginModel) UserLoginModel userLoginModel, BindingResult result){
		if(result.hasErrors())
            return login(model);
		IUserService userService = ProjectContext.getBean(IUserService.class);
		User user = userService.verify(userLoginModel.getStrName(), userLoginModel.getPsd());
		if(user==null){
	        result.addError(new FieldError(strUserLoginModel,"strName","用户名或密码错误。"));
	        result.addError(new FieldError(strUserLoginModel,"psd","用户名或密码错误。"));
            return login(model);
        }
        else{
        	if (user.getEnumState() != 0) {
        		result.addError(new FieldError(strUserLoginModel,"strName","此用户被禁用，不能登录。"));
        		return login(model);
			}
        	
        	List<Authority> authorities = userService.getUserRightInfos(user);
        	List<RoleInfo> roles = userService.getRoles(user);
        	if (roles.size() > 0 && authorities.size() > 0) {
            	RoleInfo role = roles.get(0);
            	AccountAuth accountAuth=new AccountAuth(user.getPk(), user.getStrName());
            	AccountRole accountRole=new AccountRole(role.getPk(), role.getStrName());
            	List<AuthorityMenu> authorityMenus=new ArrayList<AuthorityMenu>();

            	for(Authority authority :authorities){
            		log.debug(authority.getStrName()+":"+authority.getUrl());
            		if(authority.getParent()==null){
            			AuthorityMenu authorityMenu=new AuthorityMenu(authority.getPk(), authority.getStrName(), authority.getItemIcon(), authority.getUrl());
            			
            			List<AuthorityMenu> childrenAuthorityMenus=new ArrayList<AuthorityMenu>();
            			for(Authority subAuthority :authorities){   				
            				if(subAuthority.getParent()!=null && subAuthority.getParent().getPk().equals(authority.getPk()))
            					childrenAuthorityMenus.add(new AuthorityMenu(subAuthority.getPk(), subAuthority.getStrName(), subAuthority.getItemIcon(), subAuthority.getUrl()));
            			}
            			authorityMenu.setChildrens(childrenAuthorityMenus);
            			authorityMenus.add(authorityMenu);
            		}
            	}

        		List<PermissionMenu> permissionMenus=new ArrayList<PermissionMenu>(); 	
            	for(Authority authority : authorities){ 	  		
            		List<Authority> parentAuthorities=new ArrayList<Authority>();
            		Authority tempAuthority=authority;
            		while(tempAuthority.getParent()!=null){
            			parentAuthorities.add(tempAuthority.getParent());
            			tempAuthority=tempAuthority.getParent();
            		}
            		if(parentAuthorities.size()>=2)
            			permissionMenus.add(new PermissionMenu(parentAuthorities.get(parentAuthorities.size()-1).getPk(),parentAuthorities.get(parentAuthorities.size()-1).getStrName(),parentAuthorities.get(parentAuthorities.size()-2).getPk(),parentAuthorities.get(parentAuthorities.size()-2).getStrName(),authority.getStrName(),authority.getMatchUrl()));
            		else if(parentAuthorities.size()==1)
            			permissionMenus.add(new PermissionMenu(parentAuthorities.get(0).getPk(),parentAuthorities.get(0).getStrName(),authority.getPk(),authority.getStrName(),authority.getStrName(),authority.getMatchUrl()));
            		else
            			permissionMenus.add(new PermissionMenu(authority.getPk(),authority.getStrName(),null,null,authority.getStrName(),authority.getMatchUrl()));
            	}
            	accountRole.setAuthorityMenus(authorityMenus);
            	accountRole.setPermissionMenus(permissionMenus);
            	accountAuth.setAccountRole(accountRole);
            	AuthHelper.setSessionAccountAuth(request, accountAuth);
			}
        	
        }
		log.debug("登陆成功");
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="/home/index";
    	return "redirect:"+returnUrl; 	
	}

	
	@AuthPassport
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, User searchModel){ 	
		IUserService userService = ProjectContext.getBean(IUserService.class);
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE); 
        
        if (StringUtils.isBlank(searchModel.getStrName())) {
        	model.addAttribute("contentModel", userService.listPage( pageNo, pageSize, Order.asc("strName")));
		}else {
			model.addAttribute("contentModel", userService.listPage( pageNo, pageSize , Order.asc("strName") ,Restrictions.like("strName", searchModel.getStrName(),MatchMode.ANYWHERE)));
		}
        return "account/list";
    }
	
	
}
