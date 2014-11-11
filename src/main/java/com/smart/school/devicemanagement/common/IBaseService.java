package com.smart.school.devicemanagement.common;

import java.io.Serializable;
import java.util.List;

public interface IBaseService <T, PK extends Serializable>{

	List<T> getAll();
	
	T saveOrUpdate(T model);
	
	void deleteList(List<T> models);

	void deleteByPk(PK pk);
	
	void deleteOne(T model);
	
	T getByPk(PK pk);
}
