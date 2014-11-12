package com.smart.school.devicemanagement.controllers;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smart.school.devicemanagement.auth.AuthHelper;
import com.smart.school.devicemanagement.auth.AuthPassport;
import com.smart.school.devicemanagement.common.BaseController;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.common.utilities.PageListUtil;
import com.smart.school.devicemanagement.models.CustomInfo;
import com.smart.school.devicemanagement.services.ICustomInfoService;
import com.smart.school.devicemanagement.web.domain.CustomInfoModel;

@Controller
@RequestMapping(value = "/customInfo")
public class CustomInfoController extends BaseController{

	@AuthPassport
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, CustomInfo searchModel){ 	
		ICustomInfoService customInfoService = ProjectContext.getBean(ICustomInfoService.class);
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);     
        
        PageList<CustomInfo> pageListNews = null;
        if (StringUtils.isBlank(searchModel.getStrName())) {
        	pageListNews = customInfoService.listPage(pageNo, pageSize , Order.desc("strName"));
        	
		}else {
//			SimpleExpression s = Restrictions.like("strName", searchModel.getStrName(),MatchMode.ANYWHERE);
			LogicalExpression expression = Restrictions.or(Restrictions.like("strName", searchModel.getStrName(),MatchMode.ANYWHERE),Restrictions.like("childrenName", searchModel.getStrName(),MatchMode.ANYWHERE));
			pageListNews =  customInfoService.listPage( pageNo, pageSize ,Order.desc("strName") ,expression);
		}
        model.addAttribute("contentModel", pageListNews);
        return "customInfo/list";
    }
	
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public String add(Model model){	
		
		if(!model.containsAttribute("contentModel")){
			CustomInfoModel customInfoModel= new CustomInfoModel();
			customInfoModel.setPk(UUID.randomUUID().toString());
			
			model.addAttribute("contentModel", customInfoModel);
		}
		
        return "customInfo/add";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	public String add(HttpServletRequest request, Model model,@Valid @ModelAttribute("contentModel") CustomInfoModel customInfoModel, BindingResult result){	
		
		if(result.hasErrors())
            return add(model);
		ICustomInfoService customInfoService = ProjectContext.getBean(ICustomInfoService.class);
		
		if(customInfoModel != null ){
			CustomInfo customInfo = new CustomInfo();
			customInfoModel.setPk(UUID.randomUUID().toString());
			
			BeanUtils.copyProperties(customInfoModel, customInfo);
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(calendar.MONTH, 1);
			customInfo.setExpirationTime(calendar);

//			customInfo.setUser(AuthHelper.getCurrUser(request));
			customInfoService.saveOrUpdate(customInfo);
        }
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="customInfo/list";
    	return "redirect:"+returnUrl;     	
	}
	
	@AuthPassport
	@RequestMapping(value = "/edit/{pk}", method = {RequestMethod.GET})
	public String edit(HttpServletRequest request, Model model, @PathVariable(value="pk") String pk) {	
		ICustomInfoService customInfoService = ProjectContext.getBean(ICustomInfoService.class);
		if(!model.containsAttribute("contentModel")){
			CustomInfo customInfo= customInfoService.getByPk(pk);
			CustomInfoModel customInfoModel = new CustomInfoModel();
			BeanUtils.copyProperties(customInfo, customInfoModel);
			model.addAttribute("contentModel", customInfoModel);
		}
		
        return "customInfo/edit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/edit/{pk}", method = {RequestMethod.POST})
    public String edit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") CustomInfoModel editModel, @PathVariable(value="pk") String pk, BindingResult result)  {
        if(result.hasErrors())
            return edit(request, model, pk);
        ICustomInfoService customInfoService = ProjectContext.getBean(ICustomInfoService.class);
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        
        CustomInfo customInfo = customInfoService.getByPk(pk);
        if (customInfo != null) {
        	BeanUtils.copyProperties(editModel, customInfo);
            customInfoService.saveOrUpdate(customInfo);
		}
        if(returnUrl==null)
        	returnUrl="customInfo/list";
    	return "redirect:"+returnUrl;      	
    }
	
	
	@AuthPassport
	@RequestMapping(value = "/delete/{pk}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="pk") String pk) {
		ICustomInfoService customInfoService = ProjectContext.getBean(ICustomInfoService.class);
		customInfoService.deleteByPk(pk);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="customInfo/list";
    	return "redirect:"+returnUrl;     	
	}
	
	

}
