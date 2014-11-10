package com.smart.school.devicemanagement.services;

import com.smart.school.devicemanagement.common.IBaseService;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.models.DeviceInfo;
import com.smart.school.devicemanagement.models.SchoolInfo;

public interface IDeviceInfoService  extends IBaseService<DeviceInfo,String>{

	PageList<DeviceInfo> listPage(int pageNo, int pageSize,String strName);
}
