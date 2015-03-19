package com.smart.school.devicemanagement.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.school.devicemanagement.common.Uploader;
import com.smart.school.devicemanagement.common.umeditor.MultiState;

@Controller
@RequestMapping(value = "/umeditor/upload")
public class UMEditorController {

	private static final Logger log = LoggerFactory.getLogger(UMEditorController.class);
	
	@RequestMapping(value="/upload", method = {RequestMethod.POST})
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		Uploader uploader = null;
		try {
			uploader = new Uploader(request);
			uploader.upload();

			if (uploader != null) {
				MultiState multiState = new MultiState(true);
				multiState.putInfo("url", uploader.getUrl());
				multiState.putInfo("type", uploader.getType());

				PrintWriter pw = null;
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Cache-Control", "no-cache");

				pw = response.getWriter();

				pw.println(multiState.toJSONString());
				pw.flush();
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
}
