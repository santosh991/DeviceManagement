package com.smart.school.devicemanagement.services.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.IDeviceInfoDao;
import com.smart.school.devicemanagement.models.DeviceInfo;
import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.services.IDeviceInfoService;
import com.smart.school.devicemanagement.services.IUserService;

@Service("DeviceInfoServiceImpl")
public class DeviceInfoServiceImpl implements IDeviceInfoService{

	public PageList<DeviceInfo> listPage(int pageNo, int pageSize,String strName){
		IDeviceInfoDao deviceInfoDao = ProjectContext.getBean(IDeviceInfoDao.class);	
		
		if (StringUtils.isBlank(strName)) {
			return deviceInfoDao.listPage(pageNo, pageSize);
		}else {
			return deviceInfoDao.listPage(pageNo, pageSize, Restrictions.like("strName", strName));
		}
	}

	@Override
	public List<SchoolInfo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchoolInfo saveOrUpdate(SchoolInfo model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteList(List<SchoolInfo> models) {
		// TODO Auto-generated method stub
		
	}
}
