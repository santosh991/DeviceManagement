package com.smart.school.devicemanagement.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.dao.impl.UserRoleDao;
import com.smart.school.devicemanagement.models.User;
import com.smart.school.devicemanagement.models.UserRole;
import com.smart.school.devicemanagement.services.IUserService;
import com.smart.school.devicemanagement.web.domain.UserLoginModel;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@RequestMapping(value="/login", method = {RequestMethod.GET})
    public String login(Model model) {
        if (!model.containsAttribute("userLoginModel")) {
        	model.addAttribute("userLoginModel", null);
		}
        return "login";
    }
	
	@RequestMapping(value="/login", method = {RequestMethod.POST})
	public String login(HttpServletRequest request,Model model,@Valid @ModelAttribute("userLoginModel") UserLoginModel userLoginModel, BindingResult result){
		if(result.hasErrors())
            return login(model);
		
		User user = ProjectContext.getBean(IUserService.class).verify(userLoginModel.getStrName(), userLoginModel.getPsd());
		if(user==null){
	        result.addError(new FieldError("userLoginModel","strName","用户名或密码错误。"));
	        result.addError(new FieldError("userLoginModel","psd","用户名或密码错误。"));
            return login(model);
        }
        else{
        	List<UserRole> userRoles = ProjectContext.getBean(UserRoleDao.class).getList("user", user);
        	if (userRoles.size() > 0) {
				
			}
//        	else if(account.getEnable()==false)
//        		result.addError(new FieldError("contentModel","username","此用户被禁用，不能登录。"));
//        	else
//        		result.addError(new FieldError("contentModel","username","此用户当前未被授权，不能登录。"));
//        	AccountAuth accountAuth=new AccountAuth(account.getId(), account.getName(), account.getUsername());
//        	AccountRole accountRole=new AccountRole(account.getRole().getId(), account.getRole().getName());
//        	List<AuthorityMenu> authorityMenus=new ArrayList<AuthorityMenu>();
//        	List<Authority> roleAuthorities=account.getRole().getAuthorities();
//        	
//        	for(Authority authority :roleAuthorities){
//        		if(authority.getParent()==null){
//        			AuthorityMenu authorityMenu=new AuthorityMenu(authority.getId(), authority.getName(), authority.getItemIcon(), authority.getUrl());
//        			
//        			List<AuthorityMenu> childrenAuthorityMenus=new ArrayList<AuthorityMenu>();
//        			for(Authority subAuthority :roleAuthorities){   				
//        				if(subAuthority.getParent()!=null && subAuthority.getParent().getId().equals(authority.getId()))
//        					childrenAuthorityMenus.add(new AuthorityMenu(subAuthority.getId(), subAuthority.getName(), subAuthority.getItemIcon(), subAuthority.getUrl()));
//        			}
//        			authorityMenu.setChildrens(childrenAuthorityMenus);
//        			authorityMenus.add(authorityMenu);
//        		}
//        	}
//        	
//    		List<PermissionMenu> permissionMenus=new ArrayList<PermissionMenu>(); 	
//        	for(Authority authority : roleAuthorities){ 	  		
//        		List<Authority> parentAuthorities=new ArrayList<Authority>();
//        		Authority tempAuthority=authority;
//        		while(tempAuthority.getParent()!=null){
//        			parentAuthorities.add(tempAuthority.getParent());
//        			tempAuthority=tempAuthority.getParent();
//        		}
//        		if(parentAuthorities.size()>=2)
//        			permissionMenus.add(new PermissionMenu(parentAuthorities.get(parentAuthorities.size()-1).getId(),parentAuthorities.get(parentAuthorities.size()-1).getName(),parentAuthorities.get(parentAuthorities.size()-2).getId(),parentAuthorities.get(parentAuthorities.size()-2).getName(),authority.getName(),authority.getMatchUrl()));
//        		else if(parentAuthorities.size()==1)
//        			permissionMenus.add(new PermissionMenu(parentAuthorities.get(0).getId(),parentAuthorities.get(0).getName(),authority.getId(),authority.getName(),authority.getName(),authority.getMatchUrl()));
//        		else
//        			permissionMenus.add(new PermissionMenu(authority.getId(),authority.getName(),null,null,authority.getName(),authority.getMatchUrl()));
//        	}
//        	accountRole.setAuthorityMenus(authorityMenus);
//        	accountRole.setPermissionMenus(permissionMenus);
//        	accountAuth.setAccountRole(accountRole);
//        	AuthHelper.setSessionAccountAuth(request, accountAuth);
        }
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="/home/index";
    	return "redirect:"+returnUrl; 	
	}
}
