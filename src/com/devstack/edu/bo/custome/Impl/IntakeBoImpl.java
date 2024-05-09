package com.devstack.edu.bo.custome.Impl;

import com.devstack.edu.bo.custome.IntakeBo;
import com.devstack.edu.dao.DaoFactory;
import com.devstack.edu.dao.custom.IntakeDao;
import com.devstack.edu.dto.IntakeDto;
import com.devstack.edu.entity.Intake;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IntakeBoImpl implements IntakeBo {
    IntakeDao intakeDao = DaoFactory.getDao(DaoFactory.DataType.INTAKE);
    @Override
    public boolean saveIntake(IntakeDto intakeDto) throws SQLException, ClassNotFoundException, InvocationTargetException {
        return intakeDao.save(
                new Intake(intakeDto.getIntkId(),intakeDto.getIntkName(),intakeDto.getIntkStdate(),intakeDto.getProgramId())
        );
    }

    @Override
    public boolean updateIntake(IntakeDto intakeDto) throws SQLException, ClassNotFoundException {
        return intakeDao.update(
                new Intake(intakeDto.getIntkId(),intakeDto.getIntkName(),intakeDto.getIntkStdate(),intakeDto.getProgramId())
        );
    }

    @Override
    public List<IntakeDto> findAll(String text) throws SQLException, ClassNotFoundException {
        List<Intake> intakeList = intakeDao.findAll(text);
        List<IntakeDto> intakeDtoList = new ArrayList<>();
        for(Intake intake:intakeList){
            intakeDtoList.add(
                    new IntakeDto(intake.getIntkID(),intake.getIntkName(),intake.getIntkStdate(), (int) intake.getprogramId())
            );
        }
        return intakeDtoList;
    }

    @Override
    public List<String> loadAllIntakes() throws SQLException, ClassNotFoundException {
        return intakeDao.loadAllIntakes();
    }

    @Override
    public boolean delete(long id) throws SQLException, ClassNotFoundException {
        return intakeDao.delete(id);
    }

    @Override
    public long find(String text) throws SQLException, ClassNotFoundException {
        return intakeDao.find(text);
    }

    @Override
    public String findProgramName(long id) throws SQLException, ClassNotFoundException {
        return intakeDao.findProgramName(id);
    }


}
