package com.devstack.edu.dao.custom;

import com.devstack.edu.dao.CrudDao;
import com.devstack.edu.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface StudentDao extends CrudDao<Student,Integer>{

    List<Student> findAll(String searchtetxt)throws SQLException, ClassNotFoundException;
    List<String> autoComplteStudents()throws SQLException, ClassNotFoundException;
    long getstId(String email)throws SQLException, ClassNotFoundException;
}
