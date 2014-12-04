package com.smart.school.devicemanagement.controllers;

import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.io.Resources;

@Controller
@RequestMapping(value = "/update")
public class UpdateController {

	private static final Logger log = LoggerFactory.getLogger(UpdateController.class);
	private static String apkVersion;
	private static String apkSize;
	private static String apkPath;
	
	static {
		Properties ppt = new Properties();
		try {
			
//			UpdateController.class.getResourceAsStream(arg0)
			String path = "/apks/apkVersion.properties";
			ppt.load(new FileInputStream(path));
			apkVersion = ppt.getProperty("apkVersion");
			apkSize = ppt.getProperty("apkSize");
			apkPath = ppt.getProperty("apkPath");
		} catch (Exception e) {
			log.debug("", e);
		}
	}
	
	@RequestMapping(value = "apkVersion")
	public @ResponseBody String getApkVersion() {
        
		
		
        return "1.0.0";
 }
}
