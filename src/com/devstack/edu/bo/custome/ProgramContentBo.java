package com.devstack.edu.bo.custome;

import com.devstack.edu.dto.ProgramContentDto;
import javafx.collections.ObservableList;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ProgramContentBo extends SuperBo{
    boolean saveContent(String value,int id) throws SQLException, ClassNotFoundException, InvocationTargetException;
    List<String> getContents(long id) throws SQLException, ClassNotFoundException;

}
