package com.devstack.edu.dao.custom.Impl;

import com.devstack.edu.dao.CrudUtill;
import com.devstack.edu.dao.custom.TrainerDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.entity.Trainer;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDaoImpl implements TrainerDao {

    public static int selectedtrainerId=0;

    @Override
    public boolean save(Trainer trainer) throws SQLException, ClassNotFoundException, InvocationTargetException{
        String query = "INSERT INTO trainer(trainer_name,trainer_email,nic,address,trainer_status) VALUES (?,?,?,?,?)";
        return CrudUtill.execute(query,trainer.getTrName(),trainer.getTrEmail(),trainer.getNic(),trainer.getTrAddress(),trainer.isTrState());
    }
    @Override
    public boolean update(Trainer trainer) throws SQLException, ClassNotFoundException {
        String query = "UPDATE trainer SET trainer_name=?, trainer_email=?, nic=?,address=? WHERE trainer_id=?";
        return CrudUtill.execute(query,trainer.getTrName(),trainer.getTrEmail(),trainer.getNic(),trainer.getTrAddress(),selectedtrainerId);

    }
    @Override
    public List<Trainer> findAll(String searchText) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM trainer WHERE trainer_name LIKE ? OR trainer_email LIKE ? OR address LIKE ?";
        ResultSet rs = CrudUtill.execute(query,searchText,searchText,searchText);
        ArrayList<Trainer> trainers = new ArrayList<>();
        while (rs.next()){
            trainers.add(
                    new Trainer(
                            rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6)
                    )
            );
        }
       return trainers;
    }

    @Override
    public List<String> loadAllTrainer() throws SQLException, ClassNotFoundException {
        String query = "SELECT trainer_id,trainer_name FROM trainer";
        ResultSet resultSet = CrudUtill.execute(query);
        ArrayList<String> trainerslist = new ArrayList<>();
        while (resultSet.next()){
            trainerslist.add(resultSet.getLong(1)+"   "+resultSet.getString(2));
        }
        return trainerslist;
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String query1 = "DELETE FROM trainer WHERE trainer_id=?";
        return CrudUtill.execute(query1,id);
    }
}
