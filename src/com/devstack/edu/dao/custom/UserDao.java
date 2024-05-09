package com.devstack.edu.dao.custom;

import com.devstack.edu.dao.CrudDao;
import com.devstack.edu.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends CrudDao<User,Integer> {

    List<User> loadData(String email) throws SQLException, ClassNotFoundException;

}
