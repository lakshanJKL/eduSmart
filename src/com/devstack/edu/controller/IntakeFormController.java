package com.devstack.edu.controller;

import com.devstack.edu.bo.custome.BoFactory;
import com.devstack.edu.bo.custome.IntakeBo;
import com.devstack.edu.bo.custome.ProgramBo;
import com.devstack.edu.dao.custom.Impl.IntakeDaoImpl;
import com.devstack.edu.dao.custom.IntakeDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.dto.IntakeDto;
import com.devstack.edu.entity.Intake;
import com.devstack.edu.model.Program;
import com.devstack.edu.util.GlobalVar;
import com.devstack.edu.validator.SimpleTextValidator;
import com.devstack.edu.view.tm.IntakeTM;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class IntakeFormController {
    public AnchorPane intakecontext;
    public Button saveupdatebtn;
    public TextField in_name;
    public TextField searchText;
    public TableView intake_tble;
    public TableColumn col_intakeid;
    public TableColumn col_program;
    public TableColumn co_intakeName;
    public TableColumn col_startdate;
    public TableColumn col_intakeops;
    public ComboBox <String>cmb_programtxt;
    public DatePicker in_startdate;
    public String searchTxt="";

    IntakeBo intakeBo = BoFactory.getBo(BoFactory.DataType.INTAKE);
    ProgramBo programBo = BoFactory.getBo(BoFactory.DataType.PROGRAME);

    public void initialize(){
        col_intakeid.setCellValueFactory(new PropertyValueFactory<>("intakId"));
        col_program.setCellValueFactory(new PropertyValueFactory<>("programName"));
        co_intakeName.setCellValueFactory(new PropertyValueFactory<>("intakName"));
        col_startdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        col_intakeops.setCellValueFactory(new PropertyValueFactory<>("intakOps"));

        cmbPrograms();
        loadProgram(searchTxt);

        searchText.textProperty().addListener((observable, oldValue, newValue) -> {
            this.searchTxt = newValue;
            if (newValue != null) {
                loadProgram(searchTxt);
            }
        });
    }
    public void intakebackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard");
    }
    public void savebtnOnAction(ActionEvent actionEvent) {

        if(!SimpleTextValidator.validateName(in_name.getText())){
            new Alert(Alert.AlertType.WARNING,"Wrong name !").show();
            return;
        }
        if(!SimpleTextValidator.validateProgram(String.valueOf(cmb_programtxt.getValue()))){
            new Alert(Alert.AlertType.WARNING,"Wrong programs !").show();
            return;
        }
        if(!SimpleTextValidator.validateDob(String.valueOf(in_startdate.getValue()))){
            new Alert(Alert.AlertType.WARNING,"Wrong dob !").show();
            return;
        }
        IntakeDto intake= new IntakeDto(
                0,
                in_name.getText(),
                in_startdate.getValue(),
                Integer.parseInt(cmb_programtxt.getValue().split("   ")[0])
        );
        if(saveupdatebtn.getText().equalsIgnoreCase("Save Intake")){
            try {
                if(intakeBo.saveIntake(intake)){
                    new Alert(Alert.AlertType.INFORMATION, "Intake was Saved!").show();
                    loadProgram(searchTxt);
                    clearFields();
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again").show();
                }
            } catch (SQLException |ClassNotFoundException |InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
            if (IntakeDaoImpl.slectedIntakeId==0){
                new Alert(Alert.AlertType.ERROR, "Please verify the intake id").show();
                return;
            }
            try {
                if(intakeBo.updateIntake(intake)){
                    new Alert(Alert.AlertType.INFORMATION,"Intake Was Updated !").show();
                    loadProgram(searchTxt);
                }else {
                    new Alert(Alert.AlertType.WARNING,"Try Again !").show();
                }
            }catch (ClassNotFoundException | SQLException e){
                new Alert(Alert.AlertType.WARNING,"Something went wrong your update query").show();
                e.printStackTrace();
            }
        }
    }
    public void newintakeOnAction(ActionEvent actionEvent) {
        clearFields();
        IntakeDaoImpl.slectedIntakeId=0;
        saveupdatebtn.setText("Save Intake");
    }
    private void loadProgram(String text) {
        text = "%"+text+"%";
      try{
          ObservableList<IntakeTM> obslist = FXCollections.observableArrayList();

          for (IntakeDto intakeDto:intakeBo.findAll(text)) {
              Button updatebtn = new Button("Update");
              Button deletebtn = new Button("Delete");
              ButtonBar buttonBar = new ButtonBar();
              buttonBar.getButtons().addAll(updatebtn,deletebtn);

              IntakeTM intakeTM = new IntakeTM(
                      intakeDto.getIntkId(), programName(intakeDto.getProgramId()), intakeDto.getIntkName(), Date.valueOf(intakeDto.getIntkStdate()), buttonBar
              );

              obslist.add(intakeTM);
              deletebtn.setOnAction(event -> {
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES,ButtonType.NO);
                  Optional<ButtonType> buttonType = alert.showAndWait();
                  if (buttonType.get()==ButtonType.YES){
                      try{
                          if(intakeBo.delete(intakeTM.getIntakId())){
                              new Alert(Alert.AlertType.CONFIRMATION,"Inatke Was Deleted !").show();
                              loadProgram(searchTxt);
                          }else {
                              new Alert(Alert.AlertType.WARNING,"Try Again !").show();
                          }
                      }catch (SQLException e){
                          new Alert(Alert.AlertType.WARNING,"Something went wrong your delete query").show();
                          e.printStackTrace();
                      } catch (ClassNotFoundException e) {
                          e.printStackTrace();
                      }
                  }

              });
              deletebtn.setStyle("-fx-background-color: #C40C0C; -fx-text-fill: white;");
              updatebtn.setOnAction(event -> {
                  long programID = programsValue(intakeTM.getProgramName());
                  in_name.setText(intakeTM.getIntakName());
                  cmb_programtxt.setValue(programID+"   "+intakeTM.getProgramName());
                  in_startdate.setValue(intakeTM.getStartDate().toLocalDate());
                  saveupdatebtn.setText("Update Intake");
                  IntakeDaoImpl.slectedIntakeId =intakeTM.getIntakId();

              });
              updatebtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
          }
         intake_tble.setItems(obslist);
      }catch (ClassNotFoundException | SQLException e){
          e.printStackTrace();
      }
    }
    private long programsValue(String value){
        try {
            return intakeBo.find(value);
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0l;
    }
    private String programName(long id){
        try {
            return intakeBo.findProgramName(id);
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void clearFields() {
        in_name.clear();
        cmb_programtxt.setValue(null);
        in_startdate.setValue(null);
    }
    private void cmbPrograms() {
        try {
            ObservableList<String> oblist = FXCollections.observableArrayList(programBo.loadAllProgram());
            cmb_programtxt.setItems(oblist);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) intakecontext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

}
