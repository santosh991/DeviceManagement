package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.IRoleInfoDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.RoleInfo;

@Repository("RoleDao")
public class RoleInfoDao extends SimpleH3GenericDaoImpl<RoleInfo,String> implements IRoleInfoDao{

	private static final Logger log = LoggerFactory.getLogger(RoleInfoDao.class);
	
	
}

