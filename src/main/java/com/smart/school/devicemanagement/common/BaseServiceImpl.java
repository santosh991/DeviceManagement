package com.smart.school.devicemanagement.common;

import java.io.Serializable;
import java.util.List;

import com.smart.school.devicemanagement.genericDao.IRecordCrud;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;

public abstract class BaseServiceImpl <T, PK extends Serializable> implements IBaseService<T, PK>{

	private Class<T> entityClass;
	
	public IRecordCrud getDao(){
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
		return (IRecordCrud)ProjectContext.getBean(entityClass.getSimpleName()+"Dao");
	}
	@Override
	public List<T> getAll() {
		return getDao().getAll();
	}

	@Override
	public T saveOrUpdate(T model) {
		getDao().saveOrUpdate(model);
		return model;
	}

	@Override
	public void deleteList(List<T> models) {
		for (T t : models) {
			getDao().delete(t);
		}
	}

	@Override
	public T getByPk(PK pk){
		return (T)getDao().get(pk);
	}
}
