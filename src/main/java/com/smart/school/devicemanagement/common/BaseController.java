package com.smart.school.devicemanagement.common;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceException;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class BaseController {

	@ExceptionHandler  
    public String exception(HttpServletRequest request, Exception e) {  
          
        request.setAttribute("exceptionMessage", e.getMessage());  
          
        // 根据不同错误转向不同页面  
        if(e instanceof SQLException) 
            return "testerror";   
        else
            return "error";  
    }  
	
	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) throws WebServiceException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		   dateFormat.setLenient(false);
		   binder.registerCustomEditor(Date.class, new CustomDateEditor(
		     dateFormat, false));
		//initDataBinder(request, binder);
	}
}
