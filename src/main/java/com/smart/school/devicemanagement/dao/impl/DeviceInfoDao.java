package com.smart.school.devicemanagement.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.ICustomInfoDao;
import com.smart.school.devicemanagement.dao.IDeviceInfoDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.DeviceInfo;

@Repository("DeviceInfoDao")
public class DeviceInfoDao extends SimpleH3GenericDaoImpl<DeviceInfo,String> implements IDeviceInfoDao{

	private static final Logger log = LoggerFactory.getLogger(DeviceInfoDao.class);
	
	
}


