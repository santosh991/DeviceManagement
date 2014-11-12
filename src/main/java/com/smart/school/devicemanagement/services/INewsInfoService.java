package com.smart.school.devicemanagement.services;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.smart.school.devicemanagement.common.IBaseService;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.models.NewsInfo;

public interface INewsInfoService  extends IBaseService<NewsInfo,String>{

	PageList<NewsInfo> listPage(final int pageNo,final int pageSize,final Order order ,final Criterion ... expressdion);
}
