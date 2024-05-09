package com.devstack.edu.controller;

import com.devstack.edu.bo.custome.*;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.dto.PaymentDto;
import com.devstack.edu.dto.RegistrationDto;
import com.devstack.edu.model.Payment;
import com.devstack.edu.model.Registration;
import com.devstack.edu.validator.SimpleTextValidator;
import com.devstack.edu.view.tm.RegistrationTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
//import org.controlsfx.control.textfield.AutoCompletionBinding;
//import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class RegistrationsFormController {
    public AnchorPane Stregistrationcontext;
    public TextField searchstudenttxt;
    public ComboBox<String> cmb_regprogram;
    public ComboBox<String> cmb_regintake;
    public Button saveupdatebtn;
    public TableView<RegistrationTM>reg_table;
    public TableColumn col_regprogram;
    public TableColumn col_regstname;
    public TableColumn col_regdate;
    public TableColumn col_regops;
    public TextField searchText;
    public TableColumn col_regid;
    private String searchtxt ="";
    private long slectedid;

    RegistrationBo registrationBo = BoFactory.getBo(BoFactory.DataType.REGISTRATION);
    StudentBo studentBo = BoFactory.getBo(BoFactory.DataType.STUDENT);
    PaymentBo paymentBo = BoFactory.getBo(BoFactory.DataType.PAYMENT);
    ProgramBo programBo = BoFactory.getBo(BoFactory.DataType.PROGRAME);
    IntakeBo intakeBo = BoFactory.getBo(BoFactory.DataType.INTAKE);

    public void initialize() throws SQLException, ClassNotFoundException {

        col_regid.setCellValueFactory(new PropertyValueFactory<>("regId"));
        col_regprogram.setCellValueFactory(new PropertyValueFactory<>("programName"));
        col_regstname.setCellValueFactory(new PropertyValueFactory<>("stdntName"));
        col_regdate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        col_regops.setCellValueFactory(new PropertyValueFactory<>("regOps"));

        //auto complete student
        TextFields.bindAutoCompletion(searchstudenttxt,studentBo.autoComplteStudents());

        cmbPrograms();
        loadRegistration(searchtxt);

        cmb_regprogram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                cmbIntakes();
            }
        });
        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            String text= newValue;
            if (searchtxt!=null){
              loadRegistration(text);
            }
        });
    }
    public void newregbtnOnAction(ActionEvent actionEvent) {
        clearFields();
        saveupdatebtn.setText("Register");
    }
    public void stregbackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard");
    }
    ObservableList<RegistrationTM> obslist = FXCollections.observableArrayList();
    public void registerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InvocationTargetException {
        //validation
        if(!SimpleTextValidator.validateEmail(searchstudenttxt.getText())){
            new Alert(Alert.AlertType.WARNING,"Wrong email !").show();
            return;
        }
        if(!SimpleTextValidator.validateProgram(String.valueOf(cmb_regprogram.getValue()))){
            new Alert(Alert.AlertType.WARNING,"Wrong programs !").show();
            return;
        }
        if(!SimpleTextValidator.validateIntake(String.valueOf(cmb_regintake.getValue()))){
            new Alert(Alert.AlertType.WARNING,"Wrong intake !").show();
            return;
        }

        double amount = programBo.getProgramAmount(Long.parseLong(cmb_regprogram.getValue().split("   ")[0]));
        Long intak_id = Long.parseLong(cmb_regintake.getValue().split("   ")[0]);
        Long st_id = studentBo.getstId(searchstudenttxt.getText());

        RegistrationDto registration = new RegistrationDto(
                0, LocalDate.now(), amount, intak_id, st_id
        );

        if (saveupdatebtn.getText().equalsIgnoreCase("Register")) {

            boolean issave = registrationBo.saveRegistration(registration);
            if (issave) {
                long registerid = registrationBo.findRegId(registration);
                if (registerid == 0) {
                    new Alert(Alert.AlertType.WARNING, "Registration is not found!").show();
                }
                PaymentDto payment = new PaymentDto(
                        0, LocalDate.now(), true, amount, registerid
                );
                boolean ispaymentsave = paymentBo.savePayment(payment) ;
                if (ispaymentsave) {
                    new Alert(Alert.AlertType.INFORMATION, "Successfully Registed !").show();
                    loadRegistration("");
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Agian !!").show();
                }
            }

        } else {
            //update -not complete
            System.out.println(slectedid);
            if (slectedid == 0) {
                new Alert(Alert.AlertType.ERROR, "Please verify the intake id").show();
            }

        }
    }
    private void loadRegistration(String searchtxt) {
        searchtxt = "%"+searchtxt+"%";
        obslist.clear();
        try {
            for (RegistrationDto registrationDto:registrationBo.findAll(searchtxt)){
                Button updatebtn = new Button("Update");
                Button deletebtn = new Button("Delete");
                ButtonBar btnbar = new ButtonBar("buttonbar");
                btnbar.getButtons().addAll(updatebtn,deletebtn);

                RegistrationTM registrationTM = new RegistrationTM(
                       registrationDto.getRegId(),registrationDto.getProName(),registrationDto.getStudentName(),java.sql.Date.valueOf(registrationDto.getRegDate()),btnbar
                );

                obslist.add(registrationTM);

                updatebtn.setOnAction(event -> {
                    try {
                        searchstudenttxt.setText(registrationBo.findEmail(registrationTM.getRegId()));
                        cmb_regprogram.setValue(registrationBo.findProgram(registrationTM.getRegId()));
                        cmb_regintake.setValue(registrationBo.findIntake(registrationTM.getRegId()));
                        saveupdatebtn.setText("Update");
                        this.slectedid = registrationTM.getRegId();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                updatebtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                deletebtn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try{
                            if(registrationBo.deleteRegistration(registrationTM.getRegId())){
                                new Alert(Alert.AlertType.CONFIRMATION,"Registration Was Deleted !").show();
                                loadRegistration("");
                            }else {
                                new Alert(Alert.AlertType.WARNING,"Try Again !").show();
                            }
                        }catch (SQLException | ClassNotFoundException e){
                            new Alert(Alert.AlertType.WARNING,"Something went wrong your delete query").show();
                            e.printStackTrace();
                        }
                    }
                });
                deletebtn.setStyle("-fx-background-color: #C40C0C; -fx-text-fill: white;");
            }
            reg_table.setItems(obslist);
        } catch (SQLException |ClassNotFoundException e) {
           e.printStackTrace();
        }
    }
    private void cmbPrograms() {
        try {
            ObservableList<String> oblist = FXCollections.observableArrayList(programBo.loadAllProgram());
            cmb_regprogram.setItems(oblist);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void cmbIntakes() {
        try {
            ObservableList<String> oblist = FXCollections.observableArrayList(intakeBo.loadAllIntakes());
            cmb_regintake.setItems(oblist);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void clearFields() {
        searchstudenttxt.clear();
        cmb_regprogram.setValue(null);
        cmb_regintake.setValue(null);
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) Stregistrationcontext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}