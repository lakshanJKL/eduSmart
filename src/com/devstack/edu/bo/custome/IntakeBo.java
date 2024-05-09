package com.devstack.edu.bo.custome;

import com.devstack.edu.dto.IntakeDto;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IntakeBo extends SuperBo{
    boolean saveIntake(IntakeDto intakeDto) throws SQLException, ClassNotFoundException, InvocationTargetException;
    boolean updateIntake(IntakeDto intakeDto) throws SQLException, ClassNotFoundException;
    List<IntakeDto> findAll(String text)throws SQLException, ClassNotFoundException;
    List<String> loadAllIntakes() throws SQLException, ClassNotFoundException;
    boolean delete(long id) throws SQLException, ClassNotFoundException;
    long find(String text)throws SQLException, ClassNotFoundException;
    String findProgramName(long id)throws SQLException, ClassNotFoundException;


}
