package com.devstack.edu.bo.custome;

import com.devstack.edu.dto.RegistrationDto;
import com.devstack.edu.entity.Registration;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface RegistrationBo extends SuperBo{
   boolean saveRegistration(RegistrationDto registrationDto) throws SQLException, ClassNotFoundException, InvocationTargetException;
   boolean deleteRegistration(Long id) throws SQLException, ClassNotFoundException;
   long findRegId(RegistrationDto registrationDto) throws SQLException, ClassNotFoundException;
   List<RegistrationDto> findAll(String searchtetxt) throws SQLException, ClassNotFoundException;
   String findIntake(long id) throws SQLException, ClassNotFoundException;
   String findProgram(long id) throws SQLException, ClassNotFoundException;
   String findEmail(long id) throws SQLException, ClassNotFoundException;

}
