package com.smart.school.devicemanagement.services;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.smart.school.devicemanagement.common.IBaseService;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.models.NewsDetail;
import com.smart.school.devicemanagement.models.NewsInfo;

public interface INewsDetailService  extends IBaseService<NewsDetail,String>{

	PageList<NewsDetail> listPage(final int pageNo,final int pageSize,final Order order ,final Criterion ... expressdion);
}
