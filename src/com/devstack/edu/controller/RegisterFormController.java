package com.devstack.edu.controller;

import com.devstack.edu.dao.custom.Impl.UserDaoImpl;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.entity.User;
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
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFormController {
    public AnchorPane registerformcontext;
    public TextField txtFristName;
    public PasswordField txtPassword;
    public TextField txtLastname;
    public TextField txtEmail;

    public void SignupbtnOnAction(ActionEvent actionEvent) throws IOException {
       User user = new User(
               txtEmail.getText(), txtFristName.getText(), txtLastname.getText(), txtPassword.getText(), true
        );
        try{
            if(new UserDaoImpl().save(user)){
                new Alert(Alert.AlertType.INFORMATION, "User was Saved!").show();
                GlobalVar.userEmail = user.getEmail();
                setUi("LoginForm");
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }

        }catch (ClassNotFoundException | SQLException e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
    public void alreadyaccOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) registerformcontext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

}
