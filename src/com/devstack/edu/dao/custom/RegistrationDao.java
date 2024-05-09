package com.devstack.edu.dao.custom;

import com.devstack.edu.dao.CrudDao;
import com.devstack.edu.entity.Registration;
import com.devstack.edu.entity.Student;
import com.devstack.edu.view.tm.RegistrationTM;

import java.sql.SQLException;
import java.util.List;

public interface RegistrationDao extends CrudDao<Registration,Long> {

    long findRegId(Registration registration)throws SQLException, ClassNotFoundException;
    List<Registration> findAll(String searchtetxt)throws SQLException, ClassNotFoundException;
    String findIntake(long id)throws SQLException, ClassNotFoundException;
    String findProgram(long id)throws SQLException, ClassNotFoundException;
    String findEmail(long id)throws SQLException, ClassNotFoundException;

}
