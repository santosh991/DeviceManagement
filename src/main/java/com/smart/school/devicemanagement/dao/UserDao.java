package com.smart.school.devicemanagement.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.DeviceInfo;
import com.smart.school.devicemanagement.models.User;

@Repository
public class UserDao extends SimpleH3GenericDaoImpl<User,String>{

	private static final Log log = LogFactory.getLog(UserDao.class);
	
	public User verify(String strName,String psd){
		Criteria criteria = createCriteria(Restrictions.eq("strName", strName),Restrictions.eq("psd", psd));
		List<User> users = criteria.list();
		return users.size() > 0 ? users.get(0):null;
	}
}

