package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.ICustomInfoDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.CustomInfo;

@Repository("CustomInfoDao")
public class CustomInfoDao extends SimpleH3GenericDaoImpl<CustomInfo,String> implements ICustomInfoDao {

	private static final Logger log = LoggerFactory.getLogger(CustomInfoDao.class);
	
	
}
