package com.smart.school.devicemanagement.services.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.common.utilities.PageListUtil;
import com.smart.school.devicemanagement.dao.IAuthorityDao;
import com.smart.school.devicemanagement.dao.IRoleInfoDao;
import com.smart.school.devicemanagement.dao.IUserDao;
import com.smart.school.devicemanagement.dao.IUserRoleDao;
import com.smart.school.devicemanagement.models.Authority;
import com.smart.school.devicemanagement.models.RoleInfo;
import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.models.User;
import com.smart.school.devicemanagement.models.UserRole;
import com.smart.school.devicemanagement.services.IUserService;

@Service("UserServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User,String> implements IUserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	public User verify(String strName,String psd){
		Criteria criteria = ProjectContext.getBean(IUserDao.class).createCriteria(Restrictions.eq("strName", strName),Restrictions.eq("psd", psd));
		List<User> users = criteria.list();
		return users.size() > 0 ? users.get(0):null;
	}


	public List<Authority> getUserRightInfos(User user) {
		IAuthorityDao authorityDao = ProjectContext.getBean(IAuthorityDao.class);
		List<Authority> authorities = authorityDao.getListByHql("select auth from Authority auth,RoleRightInfo rri,UserRole rr where auth = rri.authority and rri.roleInfo = rr.roleInfo and rr.user = ? ", user);
		
		return authorities;
	}
	
	public List<RoleInfo> getRoles(User user){
		IRoleInfoDao roleDao = ProjectContext.getBean(IRoleInfoDao.class);	
		List<RoleInfo> roleInfos = roleDao.getListByHql("select r from RoleInfo r ,UserRole ur where r = ur.roleInfo and ur.user = ?", user);
		return roleInfos;
	}


	public User saveOrUpdate(User user) {
		IUserDao userDao = ProjectContext.getBean(IUserDao.class);	
		userDao.saveOrUpdate(user);
		return userDao.get(user.getPk());
	}
	
	public PageList<User> listPage(int pageNo, int pageSize,String strName){
		IUserDao userDao = ProjectContext.getBean(IUserDao.class);	
		
		if (StringUtils.isBlank(strName)) {
			return userDao.listPage(pageNo, pageSize);
		}else {
			return userDao.listPage(pageNo, pageSize, Restrictions.like("strName", strName));
		}
	}

}
