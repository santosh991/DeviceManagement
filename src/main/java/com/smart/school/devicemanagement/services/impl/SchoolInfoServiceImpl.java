/**
 * 
 */
package com.smart.school.devicemanagement.services.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import com.smart.school.devicemanagement.common.BaseServiceImpl;
import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.ISchoolInfoDao;
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
	public PageList<SchoolInfo> listPage(final int pageNo,final int pageSize,final Order order ,final Criterion ... expressdion) {
		ISchoolInfoDao schoolInfoDao = ProjectContext.getBean(ISchoolInfoDao.class);	
		
			return schoolInfoDao.listPage(pageNo, pageSize, order, expressdion);
	}

	
}
