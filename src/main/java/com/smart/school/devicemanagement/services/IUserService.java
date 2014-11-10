package com.smart.school.devicemanagement.services;

import java.util.List;

import com.smart.school.devicemanagement.common.IBaseService;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.models.Authority;
import com.smart.school.devicemanagement.models.RoleInfo;
import com.smart.school.devicemanagement.models.User;

public interface IUserService extends IBaseService<User,String>{

	User verify(String strName,String psd);
	
	List<Authority> getUserRightInfos(User user);
	
	List<RoleInfo> getRoles(User user);
	
	User saveOrUpdate(User user);
	
	PageList<User> listPage(int pageNo, int pageSize,String strName);
}
