package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.IRoleRightInfoDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.RoleRightInfo;

@Repository("RoleRightInfoDao")
public class RoleRightInfoDao extends SimpleH3GenericDaoImpl<RoleRightInfo,String>  implements IRoleRightInfoDao{

	private static final Logger log = LoggerFactory.getLogger(RoleRightInfoDao.class);
	
	
}

