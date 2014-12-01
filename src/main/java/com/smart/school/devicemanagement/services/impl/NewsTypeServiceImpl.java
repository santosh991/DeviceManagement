package com.smart.school.devicemanagement.services.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.INewsTypeDao;
import com.smart.school.devicemanagement.models.NewsType;
import com.smart.school.devicemanagement.services.INewsTypeService;

@Service
public class NewsTypeServiceImpl extends BaseServiceImpl<NewsType,String> implements INewsTypeService{

	@Override
	public PageList<NewsType> listPage(final int pageNo,final int pageSize,final Order order ,final Criterion ... expressdion) {
		INewsTypeDao newsTypeDao = ProjectContext.getBean(INewsTypeDao.class);	
		
		return newsTypeDao.listPage(pageNo, pageSize, order, expressdion);
	}
	
}
