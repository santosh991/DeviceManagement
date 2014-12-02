package com.smart.school.devicemanagement.services.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.INewsDetailDao;
import com.smart.school.devicemanagement.models.NewsDetail;
import com.smart.school.devicemanagement.services.INewsDetailService;

@Service("NewsDetailServiceImpl")
public class NewsDetailServiceImpl extends BaseServiceImpl<NewsDetail,String> implements INewsDetailService{

	@Override
	public PageList<NewsDetail> listPage(final int pageNo,final int pageSize,final Order order ,final Criterion ... expressdion) {
		INewsDetailDao newsDetailDao = ProjectContext.getBean(INewsDetailDao.class);	
		
		return newsDetailDao.listPage(pageNo, pageSize, order, expressdion);
	}
}
