package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.INewsInfoDao;
import com.smart.school.devicemanagement.dao.INewsTypeDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.NewsInfo;
import com.smart.school.devicemanagement.models.NewsType;

@Repository("NewsTypeDao")
public class NewsTypeDao  extends SimpleH3GenericDaoImpl<NewsType,String> implements INewsTypeDao{

	private static final Logger log = LoggerFactory.getLogger(NewsTypeDao.class);
	
}
