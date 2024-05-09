package com.devstack.edu.dao.custom;

import com.devstack.edu.dao.CrudDao;
import com.devstack.edu.entity.Intake;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IntakeDao extends CrudDao<Intake,Long> {
  List<Intake> findAll(String text)throws SQLException, ClassNotFoundException;
  List<String> loadAllIntakes()throws SQLException, ClassNotFoundException;
  int find(String text)throws SQLException, ClassNotFoundException;
  String findProgramName(long id)throws SQLException, ClassNotFoundException;


}
