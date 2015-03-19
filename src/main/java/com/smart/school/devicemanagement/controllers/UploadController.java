package com.smart.school.devicemanagement.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smart.school.devicemanagement.web.domain.UserLoginModel;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

	@RequestMapping(value="/upload", method = {RequestMethod.POST})
	public void upload(HttpServletRequest request,HttpServletResponse response){
		HttpServletRequest req = request;
	}
}
