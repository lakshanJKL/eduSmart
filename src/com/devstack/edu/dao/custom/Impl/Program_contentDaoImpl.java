package com.devstack.edu.dao.custom.Impl;

import com.devstack.edu.dao.CrudDao;
import com.devstack.edu.dao.CrudUtill;
import com.devstack.edu.dao.custom.Program_contentDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.entity.Program;
import com.devstack.edu.entity.Program_content;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Program_contentDaoImpl implements Program_contentDao {

    @Override
    public boolean save(Program program) throws SQLException, ClassNotFoundException, InvocationTargetException {
        return false;
    }

    @Override
    public boolean update(Program program) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(String value, int id) throws SQLException, ClassNotFoundException, InvocationTargetException {
        String query3 = "INSERT INTO program_content(header,program_program_id) VALUES(?,?)";
        return CrudUtill.execute(query3,value,id);
    }

    @Override
    public List<String> getContents(long id) throws SQLException, ClassNotFoundException {
        String query = "SELECT header FROM program_content WHERE program_program_id=?";
        ResultSet rs = CrudUtill.execute(query,id);
        ArrayList<String> contentslist = new ArrayList<>();
        while (rs.next()){
            contentslist.add(rs.getString(1));
        }
        return contentslist;
    }
}
