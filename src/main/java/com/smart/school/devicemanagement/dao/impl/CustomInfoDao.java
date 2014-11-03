package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.CustomInfo;

@Repository
public class CustomInfoDao extends SimpleH3GenericDaoImpl<CustomInfo,String>{

	private static final Logger log = LoggerFactory.getLogger(CustomInfoDao.class);
	
	
}
