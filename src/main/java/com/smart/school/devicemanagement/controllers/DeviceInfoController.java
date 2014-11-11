package com.smart.school.devicemanagement.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
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
import com.smart.school.devicemanagement.models.DeviceInfo;
import com.smart.school.devicemanagement.models.User;
import com.smart.school.devicemanagement.services.IDeviceInfoService;
import com.smart.school.devicemanagement.services.IUserService;

@Controller
@RequestMapping(value = "/deviceInfo")
public class DeviceInfoController extends BaseController{

	@AuthPassport
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, DeviceInfo searchModel){ 	
		IDeviceInfoService deviceInfoService = ProjectContext.getBean(IDeviceInfoService.class);
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE); 
        if (StringUtils.isBlank(searchModel.getStrName())) {
        	model.addAttribute("contentModel", deviceInfoService.listPage( pageNo, pageSize ,Order.asc("strName")));
		}else {
			model.addAttribute("contentModel", deviceInfoService.listPage( pageNo, pageSize ,Order.asc("strName"),Restrictions.like("strName",searchModel.getStrName(),MatchMode.ANYWHERE)));
		}
        

        return "deviceInfo/list";
    }
}
