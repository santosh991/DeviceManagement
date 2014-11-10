/**
 * 
 */
package com.smart.school.devicemanagement.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.IAuthorityDao;
import com.smart.school.devicemanagement.dao.IDeviceInfoDao;
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
		ISchoolInfoDao schoolInfoDao = ProjectContext.getBean(ISchoolInfoDao.class);	
		return schoolInfoDao.getList("user", user);
	}

	@Override
	public PageList<SchoolInfo> listPage(int pageNo, int pageSize,
			String strName) {
		ISchoolInfoDao schoolInfoDao = ProjectContext.getBean(ISchoolInfoDao.class);	
		
		if (StringUtils.isBlank(strName)) {
			return schoolInfoDao.listPage(pageNo, pageSize);
		}else {
			return schoolInfoDao.listPage(pageNo, pageSize, Restrictions.like("strName", strName));
		}
	}

	
}
