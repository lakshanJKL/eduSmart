package com.devstack.edu.bo.custome.Impl;

import com.devstack.edu.bo.custome.BoFactory;
import com.devstack.edu.bo.custome.TrainerBo;
import com.devstack.edu.dao.DaoFactory;
import com.devstack.edu.dao.custom.TrainerDao;
import com.devstack.edu.dto.StudentDto;
import com.devstack.edu.dto.TrainerDto;
import com.devstack.edu.entity.Trainer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerBoImpl implements TrainerBo {
    private TrainerDao trainerDao = DaoFactory.getDao(DaoFactory.DataType.TRAINER);
    @Override
    public boolean saveTriner(TrainerDto trainerDto) throws SQLException, ClassNotFoundException, InvocationTargetException {
        return trainerDao.save(
                new Trainer(trainerDto.getTrId(),trainerDto.getTrName(),trainerDto.getTrEmail(),trainerDto.getNic(),trainerDto.getTrAddress(),trainerDto.isTrState())
        );
    }

    @Override
    public List<TrainerDto> findAll(String searchtetxt) throws SQLException, ClassNotFoundException {
        List<Trainer> trainers = trainerDao.findAll(searchtetxt);
        List<TrainerDto> trainerDtoList = new ArrayList<>();
        for (Trainer trainer:trainers) {
            trainerDtoList.add(
                    new TrainerDto(trainer.getTrId(),trainer.getTrName(),trainer.getTrEmail(),trainer.getTrAddress(),trainer.getNic(),trainer.isTrState())
            );
        }

        return trainerDtoList;
    }

    @Override
    public List<String> loadAllTrainer() throws SQLException, ClassNotFoundException {
        return trainerDao.loadAllTrainer();
    }

    @Override
    public boolean update(TrainerDto trainerDto) throws SQLException, ClassNotFoundException {
        return trainerDao.update(
                new Trainer(trainerDto.getTrId(),trainerDto.getTrName(),trainerDto.getTrEmail(),trainerDto.getNic(),trainerDto.getTrAddress(),trainerDto.isTrState())
        );
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return trainerDao.delete(id);
    }
}
