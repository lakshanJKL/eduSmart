package com.devstack.edu.bo.custome;

import com.devstack.edu.dto.UserDto;
import com.devstack.edu.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserBo extends SuperBo{
    List<UserDto> loadData(String email) throws SQLException, ClassNotFoundException;
}
