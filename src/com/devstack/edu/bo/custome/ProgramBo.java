package com.devstack.edu.bo.custome;

import com.devstack.edu.dto.ProgrameDto;
import com.devstack.edu.entity.Program;
import javafx.collections.ObservableList;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ProgramBo extends SuperBo {
    boolean save(ProgrameDto programeDto) throws SQLException, ClassNotFoundException, InvocationTargetException;
    List<ProgrameDto> findAll(String searchtetxt, ObservableList observableList)throws SQLException, ClassNotFoundException;
    List<String> loadAllProgram() throws SQLException, ClassNotFoundException;
    int find(String text)throws SQLException, ClassNotFoundException;
    boolean delete(long id) throws SQLException, ClassNotFoundException;
    double getProgramAmount(long id) throws SQLException, ClassNotFoundException;
}
