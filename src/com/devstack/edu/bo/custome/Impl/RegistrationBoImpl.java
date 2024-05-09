package com.devstack.edu.bo.custome.Impl;

import com.devstack.edu.bo.custome.RegistrationBo;
import com.devstack.edu.dao.DaoFactory;
import com.devstack.edu.dao.custom.RegistrationDao;
import com.devstack.edu.dto.RegistrationDto;
import com.devstack.edu.entity.Registration;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationBoImpl implements RegistrationBo {
    RegistrationDao registrationDao = DaoFactory.getDao(DaoFactory.DataType.REGISTRATION);

    @Override
    public boolean saveRegistration(RegistrationDto registrationDto) throws SQLException, ClassNotFoundException, InvocationTargetException {
        return registrationDao.save(
                new Registration(registrationDto.getRegId(),registrationDto.getRegDate(),registrationDto.getProAmount(),registrationDto.getIntkId(),registrationDto.getStdntId())
        );
    }
    @Override
    public boolean deleteRegistration(Long id) throws SQLException, ClassNotFoundException {
        return registrationDao.delete(id);
    }

    @Override
    public long findRegId(RegistrationDto registrationDto) throws SQLException, ClassNotFoundException {
        return registrationDao.findRegId(
                new Registration(registrationDto.getRegId(),registrationDto.getRegDate(),registrationDto.getProAmount(),registrationDto.getIntkId(),registrationDto.getStdntId())
        );
    }

    @Override
    public List<RegistrationDto> findAll(String searchtetxt) throws SQLException, ClassNotFoundException {
        List<Registration> registrationList = registrationDao.findAll(searchtetxt);
        List<RegistrationDto> registrationDtoList = new ArrayList<>();
        for (Registration registration:registrationList){
            registrationDtoList.add(
                    new RegistrationDto(registration.getRegId(),registration.getRegDate(),registration.getProName(),registration.getStudentName())
            );
        }
        return registrationDtoList;
    }

    @Override
    public String findIntake(long id) throws SQLException, ClassNotFoundException {
        return registrationDao.findIntake(id);
    }

    @Override
    public String findProgram(long id) throws SQLException, ClassNotFoundException {
        return registrationDao.findProgram(id);
    }

    @Override
    public String findEmail(long id) throws SQLException, ClassNotFoundException {
        return registrationDao.findEmail(id);
    }

}
