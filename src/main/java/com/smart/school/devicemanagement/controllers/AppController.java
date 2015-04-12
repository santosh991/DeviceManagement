package com.smart.school.devicemanagement.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.school.devicemanagement.app.domain.App_CustomModel;
import com.smart.school.devicemanagement.common.BaseController;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.models.CustomInfo;
import com.smart.school.devicemanagement.rong.ApiHttpClient;
import com.smart.school.devicemanagement.rong.models.FormatType;
import com.smart.school.devicemanagement.rong.models.SdkHttpResult;
import com.smart.school.devicemanagement.services.ICustomInfoService;

@Controller
@RequestMapping(value = "/App")
public class AppController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(AppController.class);
	public static final String appKey = "3argexb6rn24e";
	public static final String appSecret = "ZaF3DOggLT1J";
	
	@ResponseBody
	@RequestMapping(value="/login", method = {RequestMethod.GET})
    public App_CustomModel login(HttpServletRequest request, HttpServletResponse response) throws Exception { 	
		
		ICustomInfoService customInfoService = ProjectContext.getBean(ICustomInfoService.class);
		
		String strName = request.getParameter("strName");
    	String psd = request.getParameter("psd");
    	
    	Criterion criterion = Restrictions.and(Restrictions.or(Restrictions.eq("strName", strName), Restrictions.eq("phone1", strName)),Restrictions.eq("psd", psd));
    	List<CustomInfo> customList = customInfoService.getCustomInfoList(criterion);
    	
    	if (customList.size() > 0) {
    		App_CustomModel app_customModel = new App_CustomModel();
    		CustomInfo customeInfo = customList.get(0);
    		BeanUtils.copyProperties(customeInfo, app_customModel);
    		SdkHttpResult sdkHttpResult = ApiHttpClient.getToken(appKey, appSecret, customeInfo.getPk().substring(0, 32), customeInfo.getStrName(), "http://aa.com/a.png", FormatType.json);
    		JSONObject jsonObj = new JSONObject(sdkHttpResult.getResult());
    		if (jsonObj.getInt("code")==200) {
    			app_customModel.setUserToken(jsonObj.getString("token"));
    			log.debug("gettoken=" + jsonObj.getString("token"));
			}
			return app_customModel;
		}
    	return null;
    }

}
