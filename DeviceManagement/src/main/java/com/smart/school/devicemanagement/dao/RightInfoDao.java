package com.smart.school.devicemanagement.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.DeviceInfo;

@Repository
public class RightInfoDao extends SimpleH3GenericDaoImpl<DeviceInfo,String>{

	private static final Log log = LogFactory.getLog(DeviceInfoDao.class);
	
	
}
