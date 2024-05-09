package com.devstack.edu.controller;

import com.devstack.edu.bo.custome.BoFactory;
import com.devstack.edu.bo.custome.ProgramBo;
import com.devstack.edu.bo.custome.ProgramContentBo;
import com.devstack.edu.bo.custome.TrainerBo;
import com.devstack.edu.dao.custom.Impl.ProgramDaoImpl;
import com.devstack.edu.dao.custom.Impl.Program_contentDaoImpl;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.dto.ProgrameDto;
import com.devstack.edu.entity.Program;
import com.devstack.edu.model.Program_content;
import com.devstack.edu.model.Trainer;
import com.devstack.edu.util.GlobalVar;
import com.devstack.edu.validator.SimpleTextValidator;
import com.devstack.edu.view.tm.ProgramTM;
import com.devstack.edu.view.tm.TrainerTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class ProgramFormController {
    public AnchorPane programcontext;
    public ComboBox<String> cmbx_trainer;
    public TextField pr_amount;
    public TextField pr_content;
    public TableColumn col_props;
    public TableColumn col_amount;
    public TableColumn col_housrs;
    public TableColumn col_program;
    public TableColumn col_trid;
    public TableView<ProgramTM> pr_table;
    public TextField searchtext;
    public TextField pr_hours;
    public TextField pr_name;
    public ListView <String>pr_lst;
    public Button saveupdatebtn;

    ObservableList<String> olist = FXCollections.observableArrayList();
    private String searchTxt = "";

    ProgramBo programBo = BoFactory.getBo(BoFactory.DataType.PROGRAME);
    TrainerBo trainerBo = BoFactory.getBo(BoFactory.DataType.TRAINER);
    ProgramContentBo programContentBo= BoFactory.getBo(BoFactory.DataType.PROGRAMCONTENT);

    public void initialize(){
        combobxValue();

        searchtext.textProperty().addListener((observable, oldValue, newValue) -> {
            this.searchTxt = newValue;
            if(searchTxt!= null){
                loadProgram(searchTxt);
            }
        });
        col_trid.setCellValueFactory(new PropertyValueFactory<>("trainerId"));
        col_program.setCellValueFactory(new PropertyValueFactory<>("program"));
        col_housrs.setCellValueFactory(new PropertyValueFactory<>("hours"));
        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_props.setCellValueFactory(new PropertyValueFactory<>("operations"));

        loadProgram(searchTxt);
    }
    public void programbackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard");
    }
    public void saveonAction(ActionEvent actionEvent) {
        //validataion
        if(!SimpleTextValidator.validateName(pr_name.getText())){
            new Alert(Alert.AlertType.WARNING,"Wrong name !").show();
            return;
        }
       if(!SimpleTextValidator.validateHousr(String.valueOf(pr_hours.getText()))){
           new Alert(Alert.AlertType.WARNING,"Wrong hours !").show();
           return;
       }
        if(!SimpleTextValidator.validateTrainer(String.valueOf(cmbx_trainer.getValue()))){
            new Alert(Alert.AlertType.WARNING,"Wrong trainer !").show();
            return;
        }
        if(!SimpleTextValidator.validateAmount(String.valueOf(pr_amount.getText()))){
            new Alert(Alert.AlertType.WARNING,"Wrong amount !").show();
            return;
        }
        if (pr_lst.getItems().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Content is empty !").show();
            return;
        }else{
            for(String content:pr_lst.getItems()){
                if (!SimpleTextValidator.validateContent(content)){
                    new Alert(Alert.AlertType.WARNING,"Wrong content !").show();
                    return;
                }
            }
        }
        ProgrameDto programeDto = new ProgrameDto(
                0,
                Integer.parseInt(pr_hours.getText()),
                pr_name.getText(),
                Double.parseDouble(pr_amount.getText()),
                Long.parseLong(cmbx_trainer.getValue().split("   ")[0]),
                olist
        );
        try {
            if(programBo.save(programeDto)){
                for (String lvalue:olist){
                    programContentBo.saveContent(lvalue,programById(pr_name.getText()));
                }
                new Alert(Alert.AlertType.INFORMATION, "Program was Saved!").show();
                loadProgram(searchTxt);
                clearFields();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }

        } catch (ClassNotFoundException | SQLException |InvocationTargetException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        }

    }
    public void newintakeOnAction(ActionEvent actionEvent) {
        clearFields();
        saveupdatebtn.setText("Save Program");
    }
    public void contentOnAction(ActionEvent actionEvent) {
        String contextTxt = pr_content.getText();
        if (isExists(contextTxt)){
            new Alert(Alert.AlertType.WARNING,"Already Exists Value !").show();
            return;
        }
        olist.add(contextTxt);
        pr_lst.setItems(olist);
        pr_content.clear();
    }
    private void loadProgram(String text) {
        text="%"+text+"%";

        try{
            ObservableList<ProgramTM> oblist= FXCollections.observableArrayList();
            for (ProgrameDto program:programBo.findAll(text,olist)) {

                Button deleteButton = new Button("Delete");
                Button viewButton = new Button("view");
                ButtonBar btnbar = new ButtonBar();
                btnbar.getButtons().addAll(viewButton,deleteButton);

                ProgramTM tm = new ProgramTM(
                       program.getProId(), program.getTrainerId(), program.getProName(), program.getProHours(), program.getProAmout(), btnbar
                );
                oblist.add(tm);
                viewButton.setOnAction(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProgramdatailsForm.fxml"));
                        Parent parent = loader.load();
                        ProgramdatailsFormController controller = loader.getController();
                        controller.setId(tm.getProgramId());
                        Stage stage = new Stage();
                        Scene scene = new Scene(parent);
                        stage.setScene(scene);
                        stage.show();
                        stage.centerOnScreen();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
                viewButton.setStyle("-fx-background-color: #8576FF; -fx-text-fill: white;");
                deleteButton.setOnAction(e->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try{
                            if(programBo.delete(tm.getProgramId())){
                                new Alert(Alert.AlertType.INFORMATION, "Program was Deleted!").show();
                                loadProgram("");
                            }else{
                                new Alert(Alert.AlertType.WARNING, "Try Again").show();
                            }

                        }catch (SQLException | ClassNotFoundException ex){
                            ex.printStackTrace();
                        }
                    }
                });
                deleteButton.setStyle("-fx-background-color: #C40C0C; -fx-text-fill: white;");
            }
            pr_table.setItems(oblist);
        }catch (ClassNotFoundException | SQLException e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        }
    }
    private boolean isExists(String value){
        for (String temp:olist){
            if (temp.equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }
    private int programById(String proName) throws SQLException, ClassNotFoundException {
        return programBo.find(proName);
    }
    private void combobxValue() {
        try {
            ObservableList<String> obserlist = FXCollections.observableArrayList(trainerBo.loadAllTrainer());
            cmbx_trainer.setItems(obserlist);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void clearFields(){
        pr_name.clear();
        pr_content.clear();
        pr_amount.clear();
        pr_hours.clear();
        cmbx_trainer.setValue(null);
        pr_lst.setItems(null);

    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) programcontext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }



}
