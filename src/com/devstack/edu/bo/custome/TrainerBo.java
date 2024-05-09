package com.devstack.edu.bo.custome;

import com.devstack.edu.dto.StudentDto;
import com.devstack.edu.dto.TrainerDto;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface TrainerBo extends SuperBo{
    boolean saveTriner(TrainerDto trainerDto) throws SQLException, ClassNotFoundException, InvocationTargetException;
    List<TrainerDto> findAll(String searchtetxt)throws SQLException, ClassNotFoundException;
    List<String> loadAllTrainer() throws SQLException, ClassNotFoundException;
    boolean update(TrainerDto trainerDto) throws SQLException, ClassNotFoundException;
    boolean delete(int id) throws SQLException, ClassNotFoundException;
}
