package com.smart.school.devicemanagement.common;

import java.io.Serializable;
import java.util.List;

import com.smart.school.devicemanagement.genericDao.IRecordCrud;
import com.smart.school.devicemanagement.genericDao.SimpleH3GenericDaoImpl;

public abstract class BaseServiceImpl <T, PK extends Serializable> implements IBaseService<T, PK>{

	@Override
	public List<T> getAll() {
		IRecordCrud crud = ProjectContext.getBean(SimpleH3GenericDaoImpl.class);
		return crud.getAll();
	}

	@Override
	public T saveOrUpdate(T model) {
		IRecordCrud crud = ProjectContext.getBean(SimpleH3GenericDaoImpl.class);
		crud.saveOrUpdate(model);
		return model;
	}

	@Override
	public void deleteList(List<T> models) {
		IRecordCrud crud = ProjectContext.getBean(SimpleH3GenericDaoImpl.class);
		for (T t : models) {
			crud.delete(t);
		}
	}

}
