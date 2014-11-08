package com.smart.school.devicemanagement.genericDao;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.SimpleExpression;

import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.models.User;
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
	
	/**查询**/
	
	String getPkName();
	
	T get(PK pk);
	
	List<T> getList(Collection<PK> pkList);
	
	List<T> getList(String propertyName, final Object value);
	
	List<T> getListByHql(final String hql, final Object... values);
	
//	T getUnique(String propertyName, final Object value) ;
	
	List<T> getAll();
	
	List<T> getAll(String orderByProperty, boolean isAsc);
	
	Criteria createCriteria(final Criterion... criterions);
	
	Criteria createCriteria(final int firstResult,final int maxResults,final Criterion... criterions);
	//分页接口
	int getCount(final String hql , final Object... values);
	
	List<T> getPagedList(final String hql,final int pageSize,final int pageIndex,final Object... values);
	
	PageList<T> listPage(final int pageNo,final int pageSize,final SimpleExpression ... expressdion);
}
