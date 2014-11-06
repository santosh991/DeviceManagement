/**
 * 
 */
package com.smart.school.devicemanagement.services;

import java.util.List;

import com.smart.school.devicemanagement.common.IBaseService;
import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.models.User;

/**
 * @author hh
 *
 */
public interface ISchoolInfoService extends IBaseService<SchoolInfo,String>{

	public List<SchoolInfo> getByUser(User user);
}
