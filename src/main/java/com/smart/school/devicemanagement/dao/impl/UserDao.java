package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.IUserDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.User;

@Repository("UserDao")
public class UserDao extends SimpleH3GenericDaoImpl<User,String> implements IUserDao{

	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	
	
}

