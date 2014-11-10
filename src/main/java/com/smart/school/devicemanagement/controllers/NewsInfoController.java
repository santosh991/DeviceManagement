package com.smart.school.devicemanagement.controllers;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smart.school.devicemanagement.auth.AuthHelper;
import com.smart.school.devicemanagement.auth.AuthPassport;
import com.smart.school.devicemanagement.common.BaseController;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.common.utilities.PageListUtil;
import com.smart.school.devicemanagement.models.NewsInfo;
import com.smart.school.devicemanagement.services.INewsInfoService;
import com.smart.school.devicemanagement.web.domain.NewsInfoModel;

@Controller
@RequestMapping(value = "/newsInfo")
public class NewsInfoController extends BaseController{

	@AuthPassport
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, NewsInfo searchModel){ 	
		INewsInfoService newsInfoService = ProjectContext.getBean(INewsInfoService.class);
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);     
        
        PageList<NewsInfo> pageListNews = null;
        if (StringUtils.isBlank(searchModel.getTitle())) {
        	pageListNews = newsInfoService.listPage(pageNo, pageSize);
        	
		}else {
			pageListNews =  newsInfoService.listPage( pageNo, pageSize ,Restrictions.like("title", searchModel.getTitle()));
		}
        model.addAttribute("contentModel", pageListNews);
        return "newsInfo/list";
    }
	
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public String add(Model model){	
		
		if(!model.containsAttribute("contentModel")){
			NewsInfoModel newsInfoModel=new NewsInfoModel();
			newsInfoModel.setPk(UUID.randomUUID().toString());
			
			model.addAttribute("contentModel", newsInfoModel);
		}
		
        return "newsInfo/add";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	public String add(HttpServletRequest request, Model model,@Valid @ModelAttribute("contentModel") NewsInfoModel newsInfoModel, BindingResult result){	
		
		if(result.hasErrors())
            return add(model);
		INewsInfoService newsInfoService = ProjectContext.getBean(INewsInfoService.class);
		
		if(newsInfoModel != null ){
			if (newsInfoModel.getPk() == null) {
				newsInfoModel.setPk(UUID.randomUUID().toString());
			}
			NewsInfo newsInfo = newsInfoService.getByPk(newsInfoModel.getPk());
			if (newsInfo == null ) {
				newsInfo = new NewsInfo();
				newsInfo.setPk(newsInfoModel.getPk());
			}
			newsInfo.setTitle(newsInfoModel.getTitle());
			newsInfo.setContent(newsInfoModel.getContent());
			newsInfo.setPublicTime(Calendar.getInstance());

			newsInfo.setUser(AuthHelper.getCurrUser(request));
			
			newsInfoService.saveOrUpdate(newsInfo);
        }
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="newsInfo/list";
    	return "redirect:"+returnUrl;     	
	}
}
