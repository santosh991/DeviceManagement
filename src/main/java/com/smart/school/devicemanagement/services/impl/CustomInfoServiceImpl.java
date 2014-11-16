package com.smart.school.devicemanagement.services.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.ICustomInfoDao;
import com.smart.school.devicemanagement.models.CustomInfo;
import com.smart.school.devicemanagement.services.ICustomInfoService;

@Service("CustomInfoServiceImpl")
public class CustomInfoServiceImpl extends BaseServiceImpl<CustomInfo,String> implements ICustomInfoService {

	@Override
	public PageList<CustomInfo> listPage(final int pageNo,final int pageSize,final Order order ,final Criterion ... expressdion) {
		ICustomInfoDao customInfoDao = ProjectContext.getBean(ICustomInfoDao.class);	
		
		return customInfoDao.listPage(pageNo, pageSize, order, expressdion);
	}

	@Override
	public List<CustomInfo> getCustomInfoList(final Criterion ... expressdion){
		ICustomInfoDao customInfoDao = ProjectContext.getBean(ICustomInfoDao.class);	
		Criteria criteria = customInfoDao.createCriteria(null, expressdion);
		return criteria.list();
	}
}
