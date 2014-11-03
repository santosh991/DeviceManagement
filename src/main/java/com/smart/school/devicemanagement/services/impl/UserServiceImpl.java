package com.smart.school.devicemanagement.services.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.dao.IUserDao;
import com.smart.school.devicemanagement.models.RightInfo;
import com.smart.school.devicemanagement.models.User;
import com.smart.school.devicemanagement.services.IUserService;

public class UserServiceImpl implements IUserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	public User verify(String strName,String psd){
		Criteria criteria = ProjectContext.getBean(IUserDao.class).createCriteria(Restrictions.eq("strName", strName),Restrictions.eq("psd", psd));
		List<User> users = criteria.list();
		return users.size() > 0 ? users.get(0):null;
	}


	public List<RightInfo> getUserRightInfos(User user) {
		// TODO Auto-generated method stub
		return null;
	}


	public User saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
