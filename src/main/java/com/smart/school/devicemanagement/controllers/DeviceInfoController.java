package com.smart.school.devicemanagement.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.smart.school.devicemanagement.app.domain.App_DeviceInfoModel;
import com.smart.school.devicemanagement.auth.AuthPassport;
import com.smart.school.devicemanagement.common.BaseController;
import com.smart.school.devicemanagement.common.JSONHelper;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageListUtil;
import com.smart.school.devicemanagement.models.DeviceInfo;
import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.services.IDeviceInfoService;
import com.smart.school.devicemanagement.web.domain.DeviceInfoModel;

@Controller
@RequestMapping(value = "/deviceInfo")
public class DeviceInfoController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(DeviceInfoController.class);
	
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
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public String add(Model model){	
		
		if(!model.containsAttribute("contentModel")){
			DeviceInfoModel deviceInfoModel= new DeviceInfoModel();
			deviceInfoModel.setPk(UUID.randomUUID().toString());
			
			model.addAttribute("contentModel", deviceInfoModel);
		}
		
        return "deviceInfo/add";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	public String add(HttpServletRequest request, Model model,@Valid @ModelAttribute("contentModel") DeviceInfoModel deviceInfoModel, BindingResult result){	
		
		if(result.hasErrors())
            return add(model);
		IDeviceInfoService deviceInfoService = ProjectContext.getBean(IDeviceInfoService.class);
		
		if(deviceInfoModel != null ){
			DeviceInfo deviceInfo = new DeviceInfo();
			deviceInfoModel.setPk(UUID.randomUUID().toString());
			
			BeanUtils.copyProperties(deviceInfoModel, deviceInfo);
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(calendar.MONTH, 1);
			SchoolInfo schoolInfo = new SchoolInfo();
			schoolInfo.setPk("1");
			deviceInfo.setSchoolInfo(schoolInfo);
			deviceInfo.setPublicLevel(1);
//			deviceInfo.setUser(AuthHelper.getCurrUser(request));
			deviceInfoService.saveOrUpdate(deviceInfo);
        }
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="deviceInfo/list";
    	return "redirect:"+returnUrl;     	
	}
	
	@AuthPassport
	@RequestMapping(value = "/edit/{pk}", method = {RequestMethod.GET})
	public String edit(HttpServletRequest request, Model model, @PathVariable(value="pk") String pk) {	
		IDeviceInfoService deviceInfoService = ProjectContext.getBean(IDeviceInfoService.class);
		if(!model.containsAttribute("contentModel")){
			DeviceInfo deviceInfo= deviceInfoService.getByPk(pk);
			DeviceInfoModel deviceInfoModel = new DeviceInfoModel();
			BeanUtils.copyProperties(deviceInfo, deviceInfoModel);
			model.addAttribute("contentModel", deviceInfoModel);
		}
		
        return "deviceInfo/edit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/edit/{pk}", method = {RequestMethod.POST})
    public String edit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") DeviceInfoModel editModel, @PathVariable(value="pk") String pk, BindingResult result)  {
        if(result.hasErrors())
            return edit(request, model, pk);
        IDeviceInfoService deviceInfoService = ProjectContext.getBean(IDeviceInfoService.class);
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        
        DeviceInfo deviceInfo = deviceInfoService.getByPk(pk);
        if (deviceInfo != null) {
        	BeanUtils.copyProperties(editModel, deviceInfo);
        	deviceInfo.setPublicLevel(1);
            deviceInfoService.saveOrUpdate(deviceInfo);
		}
        if(returnUrl==null)
        	returnUrl="deviceInfo/list";
    	return "redirect:"+returnUrl;      	
    }
	
	
	
	@RequestMapping(value="/appDeviceInfo", method = {RequestMethod.GET})
	public void appLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		IDeviceInfoService deviceInfoService = ProjectContext.getBean(IDeviceInfoService.class);

		 PrintWriter pw = null;  
	        try {  
	        	String strName = request.getParameter("pk");
	        	
	        	List<DeviceInfo> deviceInfos = deviceInfoService.getAll();
	        	List<App_DeviceInfoModel> app_DeviceInfoModels = new ArrayList<App_DeviceInfoModel>();
	        	for (DeviceInfo deviceInfo : deviceInfos) {
	        		App_DeviceInfoModel app_DeviceInfoModel = new App_DeviceInfoModel();
	        		BeanUtils.copyProperties(deviceInfo, app_DeviceInfoModel);
	        		
	        		app_DeviceInfoModels.add(app_DeviceInfoModel);
				}
	        	
				response.setContentType("text/xml;charset=utf-8");   
	            response.setCharacterEncoding("UTF-8");  
	            response.setHeader("Cache-Control", "no-cache");  

		            pw = response.getWriter();  
		            String xmlContent = JSONHelper.toJSON(app_DeviceInfoModels);
		            pw.print(xmlContent);  
		            pw.flush();  
	        }  
	        catch (Exception e) {  
	            log.error("",e);
	        }  
	        finally {  
	            if (pw != null)  
	                pw.close();  
	        }  
		
	}

}
