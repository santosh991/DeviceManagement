package com.smart.school.devicemanagement.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.INewsInfoDao;
import com.smart.school.devicemanagement.models.NewsInfo;
import com.smart.school.devicemanagement.services.INewsInfoService;

@Service("NewsInfoServiceImpl")
public class NewsInfoServiceImpl implements INewsInfoService{

	@Override
	public List<NewsInfo> getAll() {
		return new ArrayList<NewsInfo>();
	}

	@Override
	public NewsInfo saveOrUpdate(NewsInfo model) {
		INewsInfoDao newsInfoDao = ProjectContext.getBean(INewsInfoDao.class);
		newsInfoDao.saveOrUpdate(model);
		return newsInfoDao.get(model.getPk());
	}

	@Override
	public void deleteList(List<NewsInfo> models) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageList<NewsInfo> listPage(int pageNo, int pageSize, String strName) {
		INewsInfoDao newsInfoDao = ProjectContext.getBean(INewsInfoDao.class);	
		
		if (StringUtils.isBlank(strName)) {
			return newsInfoDao.listPage(pageNo, pageSize);
		}else {
			return newsInfoDao.listPage(pageNo, pageSize, Restrictions.like("strName", strName));
		}
	}

	
}
