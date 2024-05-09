package com.devstack.edu.controller;

import com.devstack.edu.bo.custome.BoFactory;
import com.devstack.edu.bo.custome.ProgramContentBo;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.dto.ProgramContentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.sql.*;

public class ProgramdatailsFormController {
    public ListView<String> pd_list;
    ProgramContentBo programContentBo = BoFactory.getBo(BoFactory.DataType.PROGRAMCONTENT);

    public void setId(Long id){
        try{
            ObservableList<String> obList = FXCollections.observableArrayList(programContentBo.getContents(id));
            pd_list.setItems(obList);

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
