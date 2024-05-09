package com.devstack.edu.dao.custom.Impl;

import com.devstack.edu.dao.CrudUtill;
import com.devstack.edu.dao.custom.ProgramDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.entity.Program;
import com.devstack.edu.entity.Student;
import com.devstack.edu.util.GlobalVar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDaoImpl implements ProgramDao {
    @Override
    public boolean save(Program program) throws SQLException, ClassNotFoundException, InvocationTargetException {
        String query1 = "INSERT INTO program(hours,program_name,amount,user_email,trainer_trainer_id) VALUES(?,?,?,?,?)";
        return CrudUtill.execute(query1,program.getProHours(),program.getProName().trim(),program.getProAmout(),GlobalVar.userEmail,program.getTrainerId());
    }

    @Override
    public boolean update(Program program) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws SQLException, ClassNotFoundException {
        String query1 = "DELETE FROM program WHERE program_id=?";
        return CrudUtill.execute(query1,id);
    }

    @Override
    public List<Program> findAll(String searchtetxt) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM program WHERE program_name LIKE ?";
        ResultSet rs = CrudUtill.execute(query,searchtetxt);
        ArrayList<Program> programs = new ArrayList<>();
        while (rs.next()){
         programs.add(
                 new Program(
                         rs.getInt(1),
                         rs.getInt(2),
                         rs.getString(3),
                         rs.getDouble(4),
                         rs.getString(5),
                         rs.getInt(6)
                 )
         );
        }
        return  programs;

    }

    @Override
    public List<String> loadAllProgram() throws SQLException, ClassNotFoundException {
        String query = "SELECT program_id,program_name FROM program";
        ResultSet resultSet = CrudUtill.execute(query);
        ArrayList<String> programslist = new ArrayList<>();
        while (resultSet.next()){
            programslist.add(resultSet.getLong(1)+"   "+resultSet.getString(2));
        }
        return programslist;
    }

    @Override
    public int find(String text) throws SQLException, ClassNotFoundException {
        String query = "SELECT program_id FROM program WHERE program_name= ?";
        ResultSet rs = CrudUtill.execute(query,text);
        while (rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public double getProgramAmount(long id) throws SQLException, ClassNotFoundException {
        String query = "SELECT amount FROM program WHERE program_id=?";
        ResultSet resultSet = CrudUtill.execute(query,id);
        while (resultSet.next()){
            return resultSet.getDouble(1);
        }
        return 0;
    }


}
