package com.devstack.edu.dao.custom.Impl;

import com.devstack.edu.dao.CrudUtill;
import com.devstack.edu.dao.custom.LoginDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDaoImpl implements LoginDao {
    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException, InvocationTargetException {
        return false;
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<User> findAll(String email) throws SQLException, ClassNotFoundException {
        String query ="SELECT * FROM user WHERE email=?";
        ResultSet rs = CrudUtill.execute(query,email);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getBoolean(5)
        ));
        return users;
    }
}
