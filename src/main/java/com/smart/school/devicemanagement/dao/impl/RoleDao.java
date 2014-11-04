package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.IRoleDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.Role;

@Repository("RoleDao")
public class RoleDao extends SimpleH3GenericDaoImpl<Role,String> implements IRoleDao{

	private static final Logger log = LoggerFactory.getLogger(RoleDao.class);
	
	
}

