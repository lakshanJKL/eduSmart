package com.devstack.edu.bo.custome;

import com.devstack.edu.dao.custom.PaymentDao;
import com.devstack.edu.dto.PaymentDto;
import com.devstack.edu.entity.Payment;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface PaymentBo extends SuperBo{
    boolean savePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException, InvocationTargetException;
    List<PaymentDto> getData() throws SQLException, ClassNotFoundException;
}
