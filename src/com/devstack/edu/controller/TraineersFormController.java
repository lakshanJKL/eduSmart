package com.devstack.edu.controller;

import com.devstack.edu.bo.custome.BoFactory;
import com.devstack.edu.bo.custome.TrainerBo;
import com.devstack.edu.dao.DaoFactory;
import com.devstack.edu.dao.custom.Impl.TrainerDaoImpl;
import com.devstack.edu.dao.custom.TrainerDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.dto.TrainerDto;
import com.devstack.edu.entity.Trainer;
import com.devstack.edu.util.GlobalVar;
import com.devstack.edu.validator.SimpleTextValidator;
import com.devstack.edu.view.tm.StudentTM;
import com.devstack.edu.view.tm.TrainerTM;
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
import java.util.Optional;

import static com.devstack.edu.dao.custom.Impl.TrainerDaoImpl.selectedtrainerId;

public class TraineersFormController {
    public AnchorPane traineercontext;
    public TextField tr_name;
    public TextField tr_email;
    public TextField tr_address;
    public Button saveupdatebtn;
    public TextField searchtxt;
    public TableView<TrainerTM>tr_table;
    public TableColumn col_trid;
    public TableColumn col_trname;
    public TableColumn col_tremail;
    public TableColumn col_traddress;
    public TableColumn col_trsatate;
    public TableColumn col_trops;
    public TableColumn col_trnic;
    public TextField tr_nic;
    private String searchText="";

    private TrainerBo trainerBo = BoFactory.getBo(BoFactory.DataType.TRAINER);

    public void initialize(){

        col_trid.setCellValueFactory(new PropertyValueFactory<>("trId"));
        col_trname.setCellValueFactory(new PropertyValueFactory<>("trName"));
        col_trnic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        col_traddress.setCellValueFactory(new PropertyValueFactory<>("trAddress"));
        col_trsatate.setCellValueFactory(new PropertyValueFactory<>("trStatus"));
        col_trops.setCellValueFactory(new PropertyValueFactory<>("btnBars"));
        col_tremail.setCellValueFactory(new PropertyValueFactory<>("trEmail"));

        loadTrainer(searchText);
        searchtxt.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            if (newValue != null) {
                loadTrainer(searchText);
            }

        });
    }
    public void traineebackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard");
    }
    public void saveOnAction(ActionEvent actionEvent) {
        if(!SimpleTextValidator.validateName( tr_name.getText())){
            new Alert(Alert.AlertType.WARNING,"Wrong name !").show();
            return;
        }
        if(!SimpleTextValidator.validateEmail(tr_email.getText())){
            new Alert(Alert.AlertType.WARNING,"Wrong email !").show();
            return;
        }
        if(!SimpleTextValidator.validateNic(tr_nic.getText())){
            new Alert(Alert.AlertType.WARNING,"Wrong nic !").show();
            return;
        }
        if(!SimpleTextValidator.validateAddress( tr_address.getText())){
            new Alert(Alert.AlertType.WARNING,"Wrong address !").show();
            return;
        }
        TrainerDto trainer= new TrainerDto(
                0,
                tr_name.getText(),
                tr_email.getText(),
                tr_nic.getText(),
                tr_address.getText(),
                true
        );

        if(saveupdatebtn.getText().equalsIgnoreCase("Save trainer")){
                try {
                    if (trainerBo.saveTriner(trainer)) {
                        new Alert(Alert.AlertType.INFORMATION, "Trainer was Saved!").show();
                        clearFields();
                        loadTrainer(searchText);
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again").show();
                    }
                }catch (SQLIntegrityConstraintViolationException e) {
                    new Alert(Alert.AlertType.WARNING, "Already have a trainer !!").show();
                }catch (ClassNotFoundException | SQLException |InvocationTargetException e){
                    new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                    e.printStackTrace();
                }
        }else{
            if (selectedtrainerId==0){
                new Alert(Alert.AlertType.ERROR, "Please verify the trainer id").show();
                return;
            }
            try{
                if(trainerBo.update(trainer)){
                    new Alert(Alert.AlertType.INFORMATION, "Trainer was Updated!").show();
                    clearFields();
                    loadTrainer(searchText);
                    saveupdatebtn.setText("Save trainer");
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again").show();
                }
            }catch (ClassNotFoundException | SQLException e){
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                e.printStackTrace();
            }
        }
    }
    public void newtrainerOnAction(ActionEvent actionEvent) {
        clearFields();
        selectedtrainerId =0;
        saveupdatebtn.setText("Save Trainer");
    }
    private void loadTrainer(String searchText) {
        searchText="%"+searchText+"%";

        try{
            ObservableList<TrainerTM> oblist= FXCollections.observableArrayList();

            for(TrainerDto trainer:trainerBo.findAll(searchText)){
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");
                ButtonBar bar = new ButtonBar();
                bar.getButtons().addAll(updateButton,deleteButton);

                TrainerTM tm = new TrainerTM(
                        trainer.getTrId(), trainer.getTrName(), trainer.getTrEmail(),trainer.getNic(), trainer.getTrAddress(), trainer.isTrState(), bar
                );
                oblist.add(tm);

                updateButton.setOnAction(e->{
                    tr_name.setText(tm.getTrName());
                    tr_email.setText(tm.getTrEmail());
                    tr_nic.setText(tm.getNic());
                    tr_address.setText(tm.getTrAddress());
                    selectedtrainerId=tm.getTrId();
                    saveupdatebtn.setText("Update Trainer");
                });
                updateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                deleteButton.setOnAction(e->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try{
                            if(trainerBo.delete(tm.getTrId())){
                                new Alert(Alert.AlertType.INFORMATION, "Trainer was Deleted!").show();
                                loadTrainer("");
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
            tr_table.setItems(oblist);
        }catch (ClassNotFoundException | SQLException e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        }
    }
    private void clearFields(){
        tr_address.clear();
        tr_name.clear();
        tr_email.clear();
        tr_nic.clear();
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) traineercontext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }


}
