package com.smart.school.devicemanagement.services.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.dao.IAuthorityDao;
import com.smart.school.devicemanagement.dao.IUserDao;
import com.smart.school.devicemanagement.dao.IUserRoleDao;
import com.smart.school.devicemanagement.models.Authority;
import com.smart.school.devicemanagement.models.User;
import com.smart.school.devicemanagement.models.UserRole;
import com.smart.school.devicemanagement.services.IUserService;

@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	public User verify(String strName,String psd){
		Criteria criteria = ProjectContext.getBean(IUserDao.class).createCriteria(Restrictions.eq("strName", strName),Restrictions.eq("psd", psd));
		List<User> users = criteria.list();
		return users.size() > 0 ? users.get(0):null;
	}


	public List<Authority> getUserRightInfos(User user) {
		IAuthorityDao authorityDao = ProjectContext.getBean(IAuthorityDao.class);
		List<Authority> authorities = authorityDao.getListByHql("select ri from Authority auth,RoleRightInfo rri,UserRole rr where auth = rri.authority and rri.role = rr.role and rr.user = ? ", user);
		
		return authorities;
	}


	public User saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
