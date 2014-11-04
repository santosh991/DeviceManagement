package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.ISchoolInfoDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.SchoolInfo;

@Repository("SchoolInfoDao")
public class SchoolInfoDao extends SimpleH3GenericDaoImpl<SchoolInfo,String> implements ISchoolInfoDao{

	private static final Logger log = LoggerFactory.getLogger(SchoolInfoDao.class);
	
	
}

