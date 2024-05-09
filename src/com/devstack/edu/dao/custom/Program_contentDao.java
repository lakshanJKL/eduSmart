package com.devstack.edu.dao.custom;

import com.devstack.edu.dao.CrudDao;
import com.devstack.edu.entity.Program;
import com.devstack.edu.entity.Program_content;
import javafx.collections.ObservableList;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface Program_contentDao extends CrudDao<Program,Integer> {
    boolean save(String value,int id) throws SQLException, ClassNotFoundException, InvocationTargetException;
    List<String> getContents(long id)throws SQLException, ClassNotFoundException;
}
