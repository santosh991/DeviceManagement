package com.smart.school.devicemanagement.dao;

import com.smart.school.devicemanagement.genericDao.IRecordCrud;
import com.smart.school.devicemanagement.genericDao.IRecordQuery;
import com.smart.school.devicemanagement.models.User;

public interface IUserDao extends IRecordCrud<User, String>,IRecordQuery<User, String>{

}
