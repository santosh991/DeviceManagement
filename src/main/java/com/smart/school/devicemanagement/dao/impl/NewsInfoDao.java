package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.IDeviceInfoDao;
import com.smart.school.devicemanagement.dao.INewsInfoDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.NewsInfo;

@Repository("NewsInfoDao")
public class NewsInfoDao extends SimpleH3GenericDaoImpl<NewsInfo,String> implements INewsInfoDao{

	private static final Logger log = LoggerFactory.getLogger(NewsInfoDao.class);
	
	
}

