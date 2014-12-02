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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
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

import com.smart.school.devicemanagement.app.domain.App_CustomModel;
import com.smart.school.devicemanagement.auth.AuthPassport;
import com.smart.school.devicemanagement.common.BaseController;
import com.smart.school.devicemanagement.common.JSONHelper;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.common.utilities.PageListUtil;
import com.smart.school.devicemanagement.models.CustomInfo;
import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.models.User;
import com.smart.school.devicemanagement.services.ICustomInfoService;
import com.smart.school.devicemanagement.services.ISchoolInfoService;
import com.smart.school.devicemanagement.services.IUserService;
import com.smart.school.devicemanagement.web.domain.CustomInfoModel;
import com.smart.school.devicemanagement.web.domain.KeyValueModel;

@Controller
@RequestMapping(value = "/customInfo")
public class CustomInfoController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(CustomInfoController.class);
	
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
			LogicalExpression expression = Restrictions.or(Restrictions.like("strName", searchModel.getStrName(),MatchMode.ANYWHERE),Restrictions.like("childrenName", searchModel.getStrName(),MatchMode.ANYWHERE));
			pageListNews =  customInfoService.listPage( pageNo, pageSize ,Order.desc("strName") ,expression);
		}
        model.addAttribute("contentModel", pageListNews);
        return "customInfo/list";
    }
	
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public String add(Model model){	
		ISchoolInfoService schoolInfoService = ProjectContext.getBean(ISchoolInfoService.class);
		IUserService userService = ProjectContext.getBean(IUserService.class);
		if(!model.containsAttribute("contentModel")){
			CustomInfoModel customInfoModel= new CustomInfoModel();
			customInfoModel.setPk(UUID.randomUUID().toString());
			
			model.addAttribute("contentModel", customInfoModel);
		}
		if(!model.containsAttribute("keyValueModels")){
			List<SchoolInfo> schoolInfos = schoolInfoService.getAll();
			List<User> users = new ArrayList<User>();
			for (SchoolInfo schoolInfo : schoolInfos) {
				User user = new User();
				BeanUtils.copyProperties(schoolInfo, user);
				users.add(user);
			}
			model.addAttribute("users", users);
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

			customInfo.setSchoolInfo(new SchoolInfo(customInfoModel.getUser().getPk()));
            customInfoService.saveOrUpdate(customInfo);
            
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
		ISchoolInfoService schoolInfoService = ProjectContext.getBean(ISchoolInfoService.class);
		ICustomInfoService customInfoService = ProjectContext.getBean(ICustomInfoService.class);
		IUserService userService = ProjectContext.getBean(IUserService.class);
		if(!model.containsAttribute("contentModel")){
			CustomInfo customInfo= customInfoService.getByPk(pk);
			CustomInfoModel customInfoModel = new CustomInfoModel();
			BeanUtils.copyProperties(customInfo, customInfoModel);
			model.addAttribute("contentModel", customInfoModel);
		}
		if(!model.containsAttribute("users")){
			List<SchoolInfo> schoolInfos = schoolInfoService.getAll();
			List<User> users = new ArrayList<User>();
			for (SchoolInfo schoolInfo : schoolInfos) {
				User user = new User();
				BeanUtils.copyProperties(schoolInfo, user);
				users.add(user);
			}
			model.addAttribute("users", users);
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
        	BeanUtils.copyProperties(editModel, customInfo,"expirationTime");
        	customInfo.setSchoolInfo(new SchoolInfo(editModel.getUser().getPk()));
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
		
		for (String strId : pk.split(",")) {
			customInfoService.deleteByPk(strId);
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="customInfo/list";
    	return "redirect:"+returnUrl;     	
	}
	
	@RequestMapping(value="/appLogin", method = {RequestMethod.GET})
	public void appLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ICustomInfoService customInfoService = ProjectContext.getBean(ICustomInfoService.class);
		 PrintWriter pw = null;  
	        try {  
	        	String strName = request.getParameter("strName");
	        	String psd = request.getParameter("psd");
	        	
	        	Criterion criterion = Restrictions.and(Restrictions.eq("strName", strName),Restrictions.eq("psd", psd));
	        	List<CustomInfo> customList = customInfoService.getCustomInfoList(criterion);
	        	
				response.setContentType("text/xml;charset=utf-8");   
	            response.setCharacterEncoding("UTF-8");  
	            response.setHeader("Cache-Control", "no-cache");  
	            
	        	if (customList.size() > 0) {
					App_CustomModel appCustomModel = new App_CustomModel();
					BeanUtils.copyProperties(customList.get(0), appCustomModel);

		            pw = response.getWriter();  
		            String xmlContent = JSONHelper.toJSON(customList.get(0));
		            pw.print(xmlContent);  
		            pw.flush();  
				}
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
