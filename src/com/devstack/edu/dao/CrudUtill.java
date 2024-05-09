package com.devstack.edu.dao;

import com.devstack.edu.db.DBConnection;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtill {
    public static <T>T execute(String sql,Object...pharam) throws SQLException, ClassNotFoundException{

        PreparedStatement statment = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < pharam.length; i++) {
            statment.setObject((i + 1), pharam[i]);
        }
        if (sql.startsWith("SELECT")) {
            return (T) statment.executeQuery();
        } else {
            return (T)(Boolean) (statment.executeUpdate()> 0);
        }
    }
}
