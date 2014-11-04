package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.IRightInfoDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.RightInfo;

@Repository("RightInfoDao")
public class RightInfoDao extends SimpleH3GenericDaoImpl<RightInfo,String> implements IRightInfoDao{

	private static final Logger log = LoggerFactory.getLogger(RightInfoDao.class);
	
	
}
