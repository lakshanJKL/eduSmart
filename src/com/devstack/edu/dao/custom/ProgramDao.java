package com.devstack.edu.dao.custom;

import com.devstack.edu.dao.CrudDao;
import com.devstack.edu.entity.Program;
import com.devstack.edu.entity.Student;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ProgramDao extends CrudDao<Program,Long> {
    List<Program> findAll(String searchtetxt)throws SQLException, ClassNotFoundException;
    List<String> loadAllProgram()throws SQLException, ClassNotFoundException;
    int find(String text)throws SQLException, ClassNotFoundException;
    double getProgramAmount(long id)throws SQLException, ClassNotFoundException;

}
