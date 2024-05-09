package com.devstack.edu.dao;

import com.devstack.edu.entity.Student;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T,ID> extends SuperDao{

     boolean save(T t) throws SQLException, ClassNotFoundException, InvocationTargetException;
     boolean update(T t) throws SQLException, ClassNotFoundException;
     boolean delete(ID id) throws SQLException, ClassNotFoundException;



}
