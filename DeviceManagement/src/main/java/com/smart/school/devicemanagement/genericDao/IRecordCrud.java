package com.smart.school.devicemanagement.genericDao;


import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.hibernate.Query;
/**
 * 对于数据库记录的CRUD操作的接口
 *
 */
public interface IRecordCrud <T, PK extends Serializable>{

	void saveOrUpdate(T t);
	
	void delete(T t);
	
	void delete(PK pk);
	
	void batchDelete(Collection<T> collection);
	
	int batchExecute(final String hql, final Object... values);
	
	Query createQuery(final String queryString, final Map<String, ?> values);
	
	Query createQuery(final String queryString, final Object... values);
}
