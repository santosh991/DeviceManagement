package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.IUserRoleDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.UserRole;

@Repository("UserRoleDao")
public class UserRoleDao extends SimpleH3GenericDaoImpl<UserRole,String> implements IUserRoleDao{

	private static final Logger log = LoggerFactory.getLogger(UserRoleDao.class);
	
	
}

