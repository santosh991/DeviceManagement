package com.smart.school.devicemanagement.services.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.INewsInfoDao;
import com.smart.school.devicemanagement.models.NewsInfo;
import com.smart.school.devicemanagement.services.INewsInfoService;

@Service("NewsInfoServiceImpl")
public class NewsInfoServiceImpl extends BaseServiceImpl<NewsInfo,String> implements INewsInfoService{

	
	@Override
	public PageList<NewsInfo> listPage(final int pageNo,final int pageSize,final Order order ,final Criterion ... expressdion) {
		INewsInfoDao newsInfoDao = ProjectContext.getBean(INewsInfoDao.class);	
		
		return newsInfoDao.listPage(pageNo, pageSize, order, expressdion);
	}

	
}
