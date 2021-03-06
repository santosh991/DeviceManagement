package com.smart.school.devicemanagement.genericDao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import com.smart.school.devicemanagement.common.ReflectionUtils;
import com.smart.school.devicemanagement.common.utilities.PageList;
import com.smart.school.devicemanagement.common.utilities.PageListUtil;

/**
 * 泛型Dao模板
 * 
 * @author xuhao
 * 
 * @param <T>
 * @param <PK>
 */
@Repository("SimpleH3GenericDaoImpl")
public class SimpleH3GenericDaoImpl<T, PK extends Serializable> implements
		IRecordCrud<T, PK> {

	private static Logger logger = LoggerFactory
			.getLogger(SimpleH3GenericDaoImpl.class);

	private Class<T> entityClass;

	public SimpleH3GenericDaoImpl() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public SimpleH3GenericDaoImpl(final SessionFactory sessionFactory,
			final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 采用@Autowired按类型注入SessionFactory, 当有多个SesionFactory的时候在子类重载本函数.
	 */
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Criteria createCriteria(final Order order,
			final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		if (order != null) {
			logger.debug("排序字段为:" + order);
			criteria.addOrder(order);
		}
		return criteria;
	}

	public Criteria createCriteria(final int firstResult, final int maxResults,
			final Order order, final Criterion... criterions) {
		Criteria criteria = createCriteria(order, criterions);

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria;
	}

	public String getPkName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	public T get(PK pk) {
		Assert.notNull(pk, "id is required");
		return (T) getSession().get(entityClass, pk);
	}

	public List<T> getList(Collection<PK> pkList) {
		return createCriteria(null, Restrictions.in(getPkName(), pkList))
				.list();
	}

	public List<T> getList(String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " where " + propertyName
				+ "= ?";

		return createQuery(hql, value).list();
	}

	public List<T> getAll() {
		return getSession().createCriteria(entityClass).list();
	}

	public List<T> getAll(String orderByProperty, boolean isAsc) {
		Criteria c = createCriteria(null);
		if (isAsc) {
			c.addOrder(Order.asc(orderByProperty));
		} else {
			c.addOrder(Order.desc(orderByProperty));
		}
		return c.list();
	}

	public void delete(PK pk) {
		Object entity = getSession().load(entityClass, pk);
		getSession().delete(entity);
	}

	public void saveOrUpdate(T t) {
		getSession().merge(t);
	}

	public void delete(T t) {
		getSession().delete(t);
	}

	public void batchDelete(Collection<T> collection) {
		if (collection != null) {
			for (T t : collection) {
				getSession().delete(t);
			}
		}
	}

	public Query createQuery(final String queryString,
			final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	public List<T> getListByHql(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	public int getCount(final String hql, final Object... values) {
		Query query = createQuery("select count(*) " + hql, values);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	public List<T> getPagedList(final String hql, final int pageSize,
			final int pageIndex, final Object... values) {
		Query query = createQuery(hql, values);
		query.setMaxResults(pageSize);
		query.setFirstResult(pageSize * (pageIndex - 1));
		return query.list();
	}

	public PageList<T> listPage(final int pageNo, final int pageSize,
			final Order order, final Criterion... expressdion) {
		Criteria countCriteria = createCriteria(order, expressdion);
		countCriteria.setProjection(Projections.rowCount());
		Integer count = Integer.parseInt(countCriteria.uniqueResult().toString());

		Criteria listCriteria = createCriteria((pageNo - 1) * pageSize, pageSize, order, expressdion);
		List<T> items = listCriteria.list();

		return (PageList<T>) PageListUtil.getPageList(count, pageNo, items, pageSize);
	}

}
