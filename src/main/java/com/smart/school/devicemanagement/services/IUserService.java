package com.smart.school.devicemanagement.services;

import java.util.List;

import com.smart.school.devicemanagement.models.RightInfo;
import com.smart.school.devicemanagement.models.User;

public interface IUserService {

	User verify(String strName,String psd);
	
	List<RightInfo> getUserRightInfos(User user);
	
	User saveOrUpdate(User user);
	
	
}
