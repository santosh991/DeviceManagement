package com.smart.school.devicemanagement.services.impl;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;
import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.IDeviceInfoDao;
import com.smart.school.devicemanagement.models.DeviceInfo;
import com.smart.school.devicemanagement.services.IDeviceInfoService;

@Service("DeviceInfoServiceImpl")
public class DeviceInfoServiceImpl extends BaseServiceImpl<DeviceInfo, String> implements IDeviceInfoService {

	public PageList<DeviceInfo> listPage(final int pageNo,final int pageSize,final Order order ,final SimpleExpression ... expressdion) {
		IDeviceInfoDao deviceInfoDao = ProjectContext.getBean(IDeviceInfoDao.class);

		return deviceInfoDao.listPage(pageNo, pageSize, order,expressdion);
	}

}
