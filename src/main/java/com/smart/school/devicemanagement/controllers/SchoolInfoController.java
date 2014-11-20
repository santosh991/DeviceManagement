package com.smart.school.devicemanagement.controllers;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
import com.smart.school.devicemanagement.common.utilities.PageListUtil;
import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.services.ISchoolInfoService;
import com.smart.school.devicemanagement.services.ISchoolInfoService;
import com.smart.school.devicemanagement.services.IUserService;
import com.smart.school.devicemanagement.web.domain.SchoolInfoModel;

@Controller
@RequestMapping(value = "/schoolInfo")
public class SchoolInfoController extends BaseController{


	@AuthPassport
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, SchoolInfo searchModel){ 	
		ISchoolInfoService schoolService = ProjectContext.getBean(ISchoolInfoService.class);
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        if (StringUtils.isBlank(searchModel.getStrName())) {
        	model.addAttribute("contentModel", schoolService.listPage( pageNo, pageSize,Order.asc("strName")));
		}else {
			model.addAttribute("contentModel", schoolService.listPage( pageNo, pageSize ,Order.asc("strName"),Restrictions.like("strName", searchModel.getStrName(),MatchMode.ANYWHERE)));
		}

        return "schoolInfo/list";
    }
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public String add(Model model){	
		IUserService userService = ProjectContext.getBean(IUserService.class);
		if(!model.containsAttribute("contentModel")){
			SchoolInfoModel schoolInfoModel=new SchoolInfoModel();
			schoolInfoModel.setPk(UUID.randomUUID().toString());
			
			model.addAttribute("contentModel", schoolInfoModel);
		}
		if (!model.containsAttribute("users")) {
			model.addAttribute("users", userService.getAll());
		}
		
        return "schoolInfo/add";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	public String add(HttpServletRequest request, Model model,@Valid @ModelAttribute("contentModel") SchoolInfoModel schoolInfoModel, BindingResult result){	
		
		if(result.hasErrors())
            return add(model);
		ISchoolInfoService schoolInfoService = ProjectContext.getBean(ISchoolInfoService.class);
		
		if(schoolInfoModel != null ){
			SchoolInfo schoolInfo = new SchoolInfo();
			schoolInfoModel.setPk(UUID.randomUUID().toString());
			
			BeanUtils.copyProperties(schoolInfoModel, schoolInfo);

			schoolInfoService.saveOrUpdate(schoolInfo);
        }
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="schoolInfo/list";
    	return "redirect:"+returnUrl;     	
	}
	
	@AuthPassport
	@RequestMapping(value = "/edit/{pk}", method = {RequestMethod.GET})
	public String edit(HttpServletRequest request, Model model, @PathVariable(value="pk") String pk) {	
		ISchoolInfoService schoolInfoService = ProjectContext.getBean(ISchoolInfoService.class);
		IUserService userService = ProjectContext.getBean(IUserService.class);
		if(!model.containsAttribute("contentModel")){
			SchoolInfo schoolInfo= schoolInfoService.getByPk(pk);
			SchoolInfoModel schoolInfoModel = new SchoolInfoModel();
			BeanUtils.copyProperties(schoolInfo, schoolInfoModel);
			model.addAttribute("contentModel", schoolInfoModel);
		}
		if (!model.containsAttribute("users")) {
			model.addAttribute("users", userService.getAll());
		}
		
        return "schoolInfo/edit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/edit/{pk}", method = {RequestMethod.POST})
    public String edit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") SchoolInfoModel editModel, @PathVariable(value="pk") String pk, BindingResult result)  {
        if(result.hasErrors())
            return edit(request, model, pk);
        ISchoolInfoService schoolInfoService = ProjectContext.getBean(ISchoolInfoService.class);
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        
        SchoolInfo schoolInfo = schoolInfoService.getByPk(pk);
        if (schoolInfo != null) {
        	BeanUtils.copyProperties(editModel, schoolInfo);
            schoolInfoService.saveOrUpdate(schoolInfo);
		}
        if(returnUrl==null)
        	returnUrl="schoolInfo/list";
    	return "redirect:"+returnUrl;      	
    }
	
	
	@AuthPassport
	@RequestMapping(value = "/delete/{pk}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="pk") String pk) {
		ISchoolInfoService schoolInfoService = ProjectContext.getBean(ISchoolInfoService.class);
		schoolInfoService.deleteByPk(pk);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="schoolInfo/list";
    	return "redirect:"+returnUrl;     	
	}
}
