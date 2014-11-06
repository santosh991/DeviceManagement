/**
 * 
 */
package com.smart.school.devicemanagement.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.dao.IAuthorityDao;
import com.smart.school.devicemanagement.dao.IParamInfoDao;
import com.smart.school.devicemanagement.dao.ISchoolInfoDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.ParamInfo;
import com.smart.school.devicemanagement.models.SchoolInfo;
import com.smart.school.devicemanagement.models.User;
import com.smart.school.devicemanagement.services.ISchoolInfoService;

/**
 * @author hh
 *
 */
@Service("SchoolInfoServiceImpl")
public class SchoolInfoServiceImpl extends BaseServiceImpl<SchoolInfo,String> implements ISchoolInfoService {


	@Override
	public List<SchoolInfo> getByUser(User user) {
		// TODO Auto-generated method stub
		return new ArrayList<SchoolInfo>();
	}


}
