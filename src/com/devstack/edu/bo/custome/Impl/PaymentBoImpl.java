package com.devstack.edu.bo.custome.Impl;

import com.devstack.edu.bo.custome.PaymentBo;
import com.devstack.edu.dao.DaoFactory;
import com.devstack.edu.dao.custom.PaymentDao;
import com.devstack.edu.dto.PaymentDto;
import com.devstack.edu.entity.Payment;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBo {
    PaymentDao paymentDao = DaoFactory.getDao(DaoFactory.DataType.PAYMENT);
    @Override
    public boolean savePayment(PaymentDto paymentDto) throws SQLException, ClassNotFoundException, InvocationTargetException {
        return paymentDao.save(
                new Payment(paymentDto.getPayId(),paymentDto.getDate(),paymentDto.isVerified(),paymentDto.getAmount(),paymentDto.getRegistrationId())
        );
    }

    @Override
    public List<PaymentDto> getData() throws SQLException, ClassNotFoundException {
        List<Payment> datalist = paymentDao.getData();
        List<PaymentDto> paymentDtoslist = new ArrayList<>();
        for (Payment payment:datalist){
            paymentDtoslist.add(
                    new PaymentDto(payment.getDate(),payment.getAmount())
            );
        }
        return paymentDtoslist;
    }
}
