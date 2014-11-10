package com.smart.school.devicemanagement.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.IDeviceInfoDao;
import com.smart.school.devicemanagement.models.DeviceInfo;
import com.smart.school.devicemanagement.services.IDeviceInfoService;

@Service("DeviceInfoServiceImpl")
public class DeviceInfoServiceImpl extends BaseServiceImpl<DeviceInfo,String> implements IDeviceInfoService{

	public PageList<DeviceInfo> listPage(int pageNo, int pageSize,String strName){
		IDeviceInfoDao deviceInfoDao = ProjectContext.getBean(IDeviceInfoDao.class);	
		
		if (StringUtils.isBlank(strName)) {
			return deviceInfoDao.listPage(pageNo, pageSize);
		}else {
			return deviceInfoDao.listPage(pageNo, pageSize, Restrictions.like("strName", strName));
		}
	}

	
}
