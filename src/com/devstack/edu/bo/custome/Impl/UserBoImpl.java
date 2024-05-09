package com.devstack.edu.bo.custome.Impl;

import com.devstack.edu.bo.custome.UserBo;
import com.devstack.edu.dao.DaoFactory;
import com.devstack.edu.dao.custom.UserDao;
import com.devstack.edu.dto.UserDto;
import com.devstack.edu.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao = DaoFactory.getDao(DaoFactory.DataType.USER);
    @Override
    public List<UserDto> loadData(String email) throws SQLException, ClassNotFoundException {
        List<User>userList = userDao.loadData(email);
        List<UserDto>userDtoList = new ArrayList<>();
        for (User user:userList){
            userDtoList.add(
                    new UserDto(user.getEmail(),user.getPassword(),user.getLastName())
            );
        }
        return userDtoList;
    }
}
