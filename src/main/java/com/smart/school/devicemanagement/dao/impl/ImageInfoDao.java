package com.smart.school.devicemanagement.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.dao.IImageInfoDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.DeviceInfo;
import com.smart.school.devicemanagement.models.ImageInfo;

@Repository("ImageInfoDao")
public class ImageInfoDao extends SimpleH3GenericDaoImpl<ImageInfo,String> implements IImageInfoDao {

	
}
