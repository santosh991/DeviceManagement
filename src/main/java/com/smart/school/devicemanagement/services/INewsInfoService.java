package com.smart.school.devicemanagement.services;

import com.smart.school.devicemanagement.common.IBaseService;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.models.NewsInfo;

public interface INewsInfoService  extends IBaseService<NewsInfo,String>{

	PageList<NewsInfo> listPage(int pageNo, int pageSize,String strName);
}
