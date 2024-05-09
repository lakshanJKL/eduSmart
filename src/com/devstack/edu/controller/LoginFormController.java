package com.devstack.edu.controller;

import com.devstack.edu.bo.custome.BoFactory;
import com.devstack.edu.bo.custome.UserBo;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.dto.UserDto;
import com.devstack.edu.util.GlobalVar;
import com.devstack.edu.util.PasswordManger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    public AnchorPane loginformcontexrt;
    public TextField txtrootemail;
    public PasswordField txtpassword;

    UserBo userBo = BoFactory.getBo(BoFactory.DataType.USER);
    public void signinOnAction(ActionEvent actionEvent) throws IOException {
        try {
            if(userBo==null){
                new Alert(Alert.AlertType.WARNING, "user not found").show();
            }

            for (UserDto userDto:userBo.loadData(txtrootemail.getText())){
                if (PasswordManger.checkpw(txtpassword.getText(), userDto.getPassword())){
                    GlobalVar.userEmail=userDto.getEmail();
                    GlobalVar.userName = userDto.getLastName();
                    setUi("Dashboard");
                }else{
                    new Alert(Alert.AlertType.WARNING, "password is wrong").show();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public void createaccOnAction(ActionEvent actionEvent) throws IOException {
      setUi("RegisterForm");
    }
    private void setUi(String location) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/"+location+".fxml"));
        Stage stage = (Stage) loginformcontexrt.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

}
