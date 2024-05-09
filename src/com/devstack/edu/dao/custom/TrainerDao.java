package com.devstack.edu.dao.custom;

import com.devstack.edu.dao.CrudDao;
import com.devstack.edu.entity.Trainer;

import java.sql.SQLException;
import java.util.List;

public interface TrainerDao extends CrudDao<Trainer,Integer> {

   List<Trainer> findAll(String searchtext) throws SQLException, ClassNotFoundException;
   List<String> loadAllTrainer() throws SQLException, ClassNotFoundException;

}
