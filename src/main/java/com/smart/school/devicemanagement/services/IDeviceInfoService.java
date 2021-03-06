package com.smart.school.devicemanagement.services;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.smart.school.devicemanagement.common.IBaseService;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.models.DeviceInfo;

public interface IDeviceInfoService  extends IBaseService<DeviceInfo,String>{

	PageList<DeviceInfo> listPage(final int pageNo,final int pageSize,final Order order ,final Criterion ... expressdion);
}
