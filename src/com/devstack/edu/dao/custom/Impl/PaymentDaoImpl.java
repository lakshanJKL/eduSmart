package com.devstack.edu.dao.custom.Impl;

import com.devstack.edu.dao.CrudUtill;
import com.devstack.edu.dao.custom.PaymentDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.entity.Payment;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public boolean save(Payment payment) throws SQLException, ClassNotFoundException, InvocationTargetException {
        String query = "INSERT INTO payment(date,is_verified,amount,registration_registration_id) VALUES (?,?,?,?)";
        return CrudUtill.execute(query,java.sql.Date.valueOf(payment.getDate()),payment.isVerified(),payment.getAmount(),payment.getRegistrationId());
    }

    @Override
    public boolean update(Payment payment) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Long aLong) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Payment> getData() throws SQLException, ClassNotFoundException {
        String query ="SELECT date,sum(amount) as income FROM payment WHERE is_verified=true GROUP BY date";
        ResultSet resultSet = CrudUtill.execute(query);
        ArrayList<Payment> datalist = new ArrayList<>();
        while (resultSet.next()){
            datalist.add(
                    new Payment(resultSet.getDate(1).toLocalDate(),resultSet.getDouble(2))
            );
        }
        return datalist;
    }
}
