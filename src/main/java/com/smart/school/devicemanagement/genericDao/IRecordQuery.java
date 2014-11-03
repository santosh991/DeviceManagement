package com.smart.school.devicemanagement.genericDao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

public interface IRecordQuery <T, PK extends Serializable>{

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
	
}
