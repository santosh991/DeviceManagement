package com.smart.school.devicemanagement.services;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.smart.school.devicemanagement.common.IBaseService;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.models.NewsInfo;
import com.smart.school.devicemanagement.models.NewsType;

public interface INewsTypeService extends IBaseService<NewsType,String>{

	PageList<NewsType> listPage(final int pageNo,final int pageSize,final Order order ,final Criterion ... expressdion);
}
