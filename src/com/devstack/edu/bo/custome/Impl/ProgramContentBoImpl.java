package com.devstack.edu.bo.custome.Impl;

import com.devstack.edu.bo.custome.ProgramContentBo;
import com.devstack.edu.dao.DaoFactory;
import com.devstack.edu.dao.custom.Program_contentDao;
import com.devstack.edu.dto.ProgramContentDto;
import com.devstack.edu.entity.Program_content;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ProgramContentBoImpl implements ProgramContentBo {
    Program_contentDao programContentDao = DaoFactory.getDao(DaoFactory.DataType.PROGRAMCONTENT);

    @Override
    public boolean saveContent(String value, int id) throws SQLException, ClassNotFoundException, InvocationTargetException {
        return programContentDao.save(value,id);
    }

    @Override
    public List<String> getContents(long id) throws SQLException, ClassNotFoundException {
        return programContentDao.getContents(id);
    }
}
