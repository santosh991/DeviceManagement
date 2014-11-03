package com.smart.school.devicemanagement.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Criteria;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.smart.school.devicemanagement.common.ProjectContext;
import com.smart.school.devicemanagement.dao.impl.DeviceInfoDao;
import com.smart.school.devicemanagement.models.DeviceInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/appcontext.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
public class SpringDaoTest {

	@Autowired
    private ApplicationContext applicationContext;
	
	
	@Before
	public void initTest(){
		ProjectContext.setAc(applicationContext);
	}
	

	@Test
	public void testGetAll() {
		DeviceInfoDao dao = ProjectContext.getBean(DeviceInfoDao.class);
		Criteria criteria = dao.createCriteria();
		criteria.setMaxResults(50);
		List<DeviceInfo> list = criteria.list();
		assertNotNull(list);
		assertTrue(""+list.size()+">0",list.size() >= 0 && list.size() <= 50);
	}
	
//	@Test
	public void testFindByCondition() {
		fail("Not yet implemented");
	}

//	@Test
	public void testFindByConditionRequestPage() {
		fail("Not yet implemented");
	}

//	@Test
	public void testFindByConditionRequestPageSize() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testMerge() {
		String id = "12321333";
		DeviceInfo deviceInfo = ProjectContext.getBean(DeviceInfoDao.class).get(id);
		assertNull(deviceInfo);
		deviceInfo = new DeviceInfo();
		deviceInfo.setPk(id);
		deviceInfo.setStrName(id);
		ProjectContext.getBean(DeviceInfoDao.class).saveOrUpdate(deviceInfo);
		assertNotNull(ProjectContext.getBean(DeviceInfoDao.class).get(id));

	}

}
