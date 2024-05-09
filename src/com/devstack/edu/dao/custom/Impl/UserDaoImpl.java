package com.devstack.edu.dao.custom.Impl;

import com.devstack.edu.dao.CrudDao;
import com.devstack.edu.dao.CrudUtill;
import com.devstack.edu.dao.custom.UserDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.entity.User;
import com.devstack.edu.util.PasswordManger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException, InvocationTargetException {
        String query = "INSERT INTO user VALUES (?,?,?,?,?)";
        return CrudUtill.execute(query,user.getEmail(),user.getFristName(),user.getLastName(),PasswordManger.encryptpw(user.getPassword()),true);
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
    public List<User> loadData(String email) throws SQLException, ClassNotFoundException {
        String query ="SELECT email,password,last_name FROM user WHERE email=?";
        ResultSet resultSet = CrudUtill.execute(query,email);
        ArrayList<User> userArrayList = new ArrayList<>();
        while (resultSet.next()){
            userArrayList.add(
                    new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3))
            );
        }
        return userArrayList;
    }
}
