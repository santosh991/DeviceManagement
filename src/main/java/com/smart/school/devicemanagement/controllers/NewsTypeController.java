package com.smart.school.devicemanagement.controllers;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
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
import com.smart.school.devicemanagement.auth.AuthPassport;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.common.utilities.PageListUtil;
import com.smart.school.devicemanagement.models.NewsType;
import com.smart.school.devicemanagement.services.INewsTypeService;
import com.smart.school.devicemanagement.web.domain.NewsTypeModel;

@Controller
@RequestMapping(value = "/newsType")
public class NewsTypeController {

private static final Logger log = LoggerFactory.getLogger(NewsTypeController.class);
	
	@AuthPassport
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, NewsType searchModel){ 	
		INewsTypeService newsTypeService = ProjectContext.getBean(INewsTypeService.class);
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);     
        
        PageList<NewsType> pageListNews = null;
        if (StringUtils.isBlank(searchModel.getStrName())) {
        	pageListNews = newsTypeService.listPage(pageNo, pageSize , Order.desc("strName"));
        	
		}else {
			pageListNews =  newsTypeService.listPage( pageNo, pageSize ,Order.desc("strName") ,Restrictions.like("strName", searchModel.getStrName(),MatchMode.ANYWHERE));
		}
        model.addAttribute("contentModel", pageListNews);
        return "newsType/list";
    }
	
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public String add(Model model){	
		
		if(!model.containsAttribute("contentModel")){
			NewsTypeModel newsTypeModel=new NewsTypeModel();
			newsTypeModel.setPk(UUID.randomUUID().toString());
			
			model.addAttribute("contentModel", newsTypeModel);
		}
		
        return "newsType/add";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	public String add(HttpServletRequest request, Model model,@Valid @ModelAttribute("contentModel") NewsTypeModel newsTypeModel, BindingResult result){	
		
		if(result.hasErrors())
            return add(model);
		INewsTypeService newsTypeService = ProjectContext.getBean(INewsTypeService.class);
		
		if(newsTypeModel != null ){
			NewsType newsType = new NewsType();
			newsTypeModel.setPk(UUID.randomUUID().toString());
			
			BeanUtils.copyProperties(newsTypeModel, newsType);
			
			
			newsTypeService.saveOrUpdate(newsType);
        }
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="newsType/list";
    	return "redirect:"+returnUrl;     	
	}
	
	@AuthPassport
	@RequestMapping(value = "/edit/{pk}", method = {RequestMethod.GET})
	public String edit(HttpServletRequest request, Model model, @PathVariable(value="pk") String pk) {	
		INewsTypeService newsTypeService = ProjectContext.getBean(INewsTypeService.class);
		if(!model.containsAttribute("contentModel")){
			NewsType newsType= newsTypeService.getByPk(pk);
			NewsTypeModel newsTypeModel = new NewsTypeModel();
			BeanUtils.copyProperties(newsType, newsTypeModel);
			model.addAttribute("contentModel", newsTypeModel);
		}
		
        return "newsType/edit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/edit/{pk}", method = {RequestMethod.POST})
    public String edit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") NewsTypeModel editModel, @PathVariable(value="pk") String pk, BindingResult result)  {
        if(result.hasErrors())
            return edit(request, model, pk);
        INewsTypeService newsTypeService = ProjectContext.getBean(INewsTypeService.class);
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        
        NewsType newsType = newsTypeService.getByPk(pk);
        if (newsType != null) {
        	BeanUtils.copyProperties(editModel, newsType);
        	
            newsTypeService.saveOrUpdate(newsType);
		}
        if(returnUrl==null)
        	returnUrl="newsType/list";
    	return "redirect:"+returnUrl;      	
    }
	
	
	@AuthPassport
	@RequestMapping(value = "/delete/{pk}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="pk") String pk) {
		INewsTypeService newsTypeService = ProjectContext.getBean(INewsTypeService.class);
		
		for (String strId : pk.split(",")) {
			newsTypeService.deleteByPk(strId);
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="newsType/list";
    	return "redirect:"+returnUrl;     	
	}
	
	
	

}
