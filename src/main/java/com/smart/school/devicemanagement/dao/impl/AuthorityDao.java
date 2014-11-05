package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.IAuthorityDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.Authority;

@Repository("AuthorityDao")
public class AuthorityDao extends SimpleH3GenericDaoImpl<Authority,String> implements IAuthorityDao{

	private static final Logger log = LoggerFactory.getLogger(AuthorityDao.class);
	
	
}
