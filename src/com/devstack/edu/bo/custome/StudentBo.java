package com.devstack.edu.bo.custome;

import com.devstack.edu.dto.StudentDto;
import com.devstack.edu.entity.Student;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface StudentBo extends SuperBo{
    boolean saveStudent(StudentDto studentDto) throws SQLException, ClassNotFoundException, InvocationTargetException;
    List<StudentDto> findAll(String searchtetxt)throws SQLException, ClassNotFoundException;
    List<String> autoComplteStudents() throws SQLException, ClassNotFoundException;
    boolean update(StudentDto studentDto) throws SQLException, ClassNotFoundException;
    boolean delete(int id) throws SQLException, ClassNotFoundException;
    long getstId(String email) throws SQLException, ClassNotFoundException;
}
