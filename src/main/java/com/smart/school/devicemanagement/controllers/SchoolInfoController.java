package com.smart.school.devicemanagement.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smart.school.devicemanagement.auth.AuthPassport;
import com.smart.school.devicemanagement.common.BaseController;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageListUtil;
import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.models.User;
import com.smart.school.devicemanagement.services.IDeviceInfoService;
import com.smart.school.devicemanagement.services.ISchoolInfoService;

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
        	model.addAttribute("contentModel", schoolService.listPage( pageNo, pageSize));
		}else {
			model.addAttribute("contentModel", schoolService.listPage( pageNo, pageSize ,Restrictions.like("strName", searchModel.getStrName())));
		}

        return "schoolInfo/list";
    }
}
