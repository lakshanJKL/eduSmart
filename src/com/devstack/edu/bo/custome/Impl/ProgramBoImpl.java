package com.devstack.edu.bo.custome.Impl;

import com.devstack.edu.bo.custome.ProgramBo;
import com.devstack.edu.dao.DaoFactory;
import com.devstack.edu.dao.custom.ProgramDao;
import com.devstack.edu.dto.ProgrameDto;
import com.devstack.edu.entity.Program;
import com.devstack.edu.util.GlobalVar;
import javafx.collections.ObservableList;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramBoImpl implements ProgramBo {
    ProgramDao programDao = DaoFactory.getDao(DaoFactory.DataType.PROGRAM);


    @Override
    public boolean save(ProgrameDto programeDto) throws SQLException, ClassNotFoundException, InvocationTargetException {
        return programDao.save(
              new Program(programeDto.getProId(),programeDto.getProHours(),programeDto.getProName(),programeDto.getProAmout(), GlobalVar.userEmail,programeDto.getTrainerId())
        );
    }

    @Override
    public List<ProgrameDto> findAll(String searchtetxt, ObservableList observableList) throws SQLException, ClassNotFoundException {
        List<Program> list = programDao.findAll(searchtetxt);
        List<ProgrameDto> programeDtoList = new ArrayList<>();
        for (Program program:list){
            programeDtoList.add(
                    new ProgrameDto(program.getProId(),program.getProHours(),program.getProName(),program.getProAmout(),program.getTrainerId(),observableList)
            );
        }

        return programeDtoList;
    }

    @Override
    public List<String> loadAllProgram() throws SQLException, ClassNotFoundException {
        return programDao.loadAllProgram();
    }

    @Override
    public int find(String text) throws SQLException, ClassNotFoundException {
        return programDao.find(text);
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        return programDao.delete(id);
    }

    @Override
    public double getProgramAmount(long id) throws SQLException, ClassNotFoundException {
        return programDao.getProgramAmount(id);
    }
}
