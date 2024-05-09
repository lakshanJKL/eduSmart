package com.devstack.edu.dao.custom;

import com.devstack.edu.dao.CrudDao;
import com.devstack.edu.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDao extends CrudDao<Payment,Long> {

    List<Payment> getData() throws SQLException, ClassNotFoundException;

}
