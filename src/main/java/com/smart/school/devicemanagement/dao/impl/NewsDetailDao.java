/**
 * 
 */
package com.smart.school.devicemanagement.dao.impl;

import org.springframework.stereotype.Repository;

import com.smart.school.devicemanagement.dao.INewsDetailDao;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;
import com.smart.school.devicemanagement.models.NewsDetail;

/**
 * @author hh
 *
 */
@Repository("NewsDetailDao")
public class NewsDetailDao  extends SimpleH3GenericDaoImpl<NewsDetail,String> implements INewsDetailDao {

	
}
