package com.devstack.edu.controller;
import com.devstack.edu.bo.custome.BoFactory;
import com.devstack.edu.bo.custome.StudentBo;
import com.devstack.edu.dao.CrudUtill;
import com.devstack.edu.dao.DaoFactory;
import com.devstack.edu.dao.SuperDao;
import com.devstack.edu.dao.custom.Impl.StudentDaoImpl;
import com.devstack.edu.dao.custom.StudentDao;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.dto.StudentDto;
import com.devstack.edu.entity.Student;
import com.devstack.edu.util.GlobalVar;
import com.devstack.edu.validator.SimpleTextValidator;
import com.devstack.edu.view.tm.StudentTM;
import com.mysql.cj.xdevapi.Schema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.devstack.edu.dao.custom.Impl.StudentDaoImpl.selectedStudentId;

public class StudentFormController {
    public AnchorPane studentcontext;
    public TextField stName;
    public TextField stEmail;
    public TextField stAddress;
    public DatePicker stDob;
    public TableView<StudentTM> St_table;
    public TableColumn col_stid;
    public TableColumn col_stname;
    public TableColumn col_stemail;
    public TableColumn col_stdob;
    public TableColumn col_staddress;
    public TableColumn col_ststate;
    public TextField txtsearch;
    public TableColumn st_ops;
    public Button saveupdatebtn;
    private String searchText="";

    private StudentBo studentBo = BoFactory.getBo(BoFactory.DataType.STUDENT);

    public void initialize(){

        col_stid.setCellValueFactory(new PropertyValueFactory<>("stId"));
        col_stname.setCellValueFactory(new PropertyValueFactory<>("stName"));
        col_stdob.setCellValueFactory(new PropertyValueFactory<>("stDob"));
        col_staddress.setCellValueFactory(new PropertyValueFactory<>("stAddress"));
        col_ststate.setCellValueFactory(new PropertyValueFactory<>("stStatus"));
        st_ops.setCellValueFactory(new PropertyValueFactory<>("btnBar"));
        col_stemail.setCellValueFactory(new PropertyValueFactory<>("stEmail"));

        loadStudents(searchText);
        txtsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            if (newValue != null) {
                loadStudents(searchText);
            }

        });
    }
    public void stbackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard");
    }
    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        clearFields();
        selectedStudentId=0;
        saveupdatebtn.setText("Save Student");
    }
    public void SavebtnOnAction(ActionEvent actionEvent) {

        if(!SimpleTextValidator.validateName(stName.getText())){
            new Alert(Alert.AlertType.WARNING,"Wrong name !").show();
            return;
        }
        if(!SimpleTextValidator.validateEmail(stEmail.getText())){
            new Alert(Alert.AlertType.WARNING,"Wrong email !").show();
            return;
        }
        if(!SimpleTextValidator.validateDob(String.valueOf(stDob.getValue()))){
            new Alert(Alert.AlertType.WARNING,"Wrong dob !").show();
            return;
        }
        if(!SimpleTextValidator.validateAddress(stAddress.getText())){
            new Alert(Alert.AlertType.WARNING,"Wrong address !").show();
            return;
        }
        StudentDto student = new StudentDto(
                0,
                stName.getText(),
                stEmail.getText(),
                stDob.getValue(),
                stAddress.getText(),
                true
        );

        if(saveupdatebtn.getText().equalsIgnoreCase("Save Student")){
            try {
                if(studentBo.saveStudent(student)) {
                    new Alert(Alert.AlertType.INFORMATION, "Student was Saved!").show();
                    clearFields();
                    loadStudents(searchText);
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again").show();
                }
            } catch (SQLIntegrityConstraintViolationException e){
                new Alert(Alert.AlertType.WARNING, "Already have a student !!").show();

            } catch (SQLException | ClassNotFoundException e ) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }else{
            if (selectedStudentId==0){
                new Alert(Alert.AlertType.ERROR, "Please verify the student id").show();
                return;
            }
            try {
                if (studentBo.update(student)) {
                    new Alert(Alert.AlertType.INFORMATION, "Student was Updated!").show();
                    clearFields();
                    loadStudents(searchText);
                    saveupdatebtn.setText("Save Student");
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again").show();
                }

            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                e.printStackTrace();
            }
        }
    }
    private void loadStudents(String searchText){
        searchText="%"+searchText+"%";
        try{
            ObservableList<StudentTM>oblist= FXCollections.observableArrayList();
            for (StudentDto student:studentBo.findAll(searchText)) {

                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");
                ButtonBar bar = new ButtonBar();
                bar.getButtons().addAll(updateButton,deleteButton);

                StudentTM tm = new StudentTM(
                        student.getStid(), student.getStName(), student.getStEmail(), Date.valueOf(student.getStDob()), student.getStAddress(), student.isStatus(), bar
                );
                oblist.add(tm);

                updateButton.setOnAction(e->{
                    stName.setText(tm.getStName());
                    stEmail.setText(tm.getStEmail());
                    stDob.setValue(tm.getStDob().toLocalDate());
                    stAddress.setText(tm.getStAddress());
                    selectedStudentId=tm.getStId();
                    saveupdatebtn.setText("Update Student");
                });
                updateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                deleteButton.setOnAction(e->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try{
                            if(studentBo.delete(student.getStid())){
                                new Alert(Alert.AlertType.INFORMATION, "Student was Deleted!").show();
                                loadStudents("");
                            }else{
                                new Alert(Alert.AlertType.WARNING, "Try Again").show();
                            }
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });
                deleteButton.setStyle("-fx-background-color: #C40C0C; -fx-text-fill: white;");
            }
            St_table.setItems(oblist);
        }catch (ClassNotFoundException | SQLException e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        }
    }
    private void clearFields(){
        stAddress.clear();
        stName.clear();
        stEmail.clear();
        stDob.setValue(null);
    }
    private void setUi(String location) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml")));
        Stage stage = (Stage) studentcontext.getScene().getWindow();
        stage.setTitle("EduSmart");
        stage.setScene(scene);
        stage.centerOnScreen();
    }

}
