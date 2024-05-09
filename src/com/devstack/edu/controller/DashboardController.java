package com.devstack.edu.controller;

import com.devstack.edu.util.GlobalVar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardController {

    public AnchorPane dashboardcontext;
    public Label lblDate;
    public Label lblTime;
    public Label emailfield;


    public void initialize(){
        loadTimeDate();
       this.emailfield.setText("Hello ! "+GlobalVar.userName);

    }

    private void loadTimeDate() {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),event -> {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                            String currenttime = simpleDateFormat.format(new Date());
                            lblTime.setText(currenttime);
                }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //========date ====

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentdate = simpleDateFormat.format(new Date());
        lblDate.setText(currentdate);
    }
    public void studentclickedOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("StudentForm");
    }
    public void programclickedOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("ProgramForm");
    }
    public void traineerclickedOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("TraineersForm");
    }
    public void intakeclickedOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("IntakeForm");
    }
    public void incomeclickedOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("IncomeForm");
    }
    public void registrationclickedOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("RegistrationsForm");
    }
    public void reportclickedOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("ReportForm");
    }
    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
    public void backupOnAction(ActionEvent actionEvent) {
        String userName="root";
        String password="JK#laka@JKL123";
        String database="edusmart";


        try{
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyyMMdd");
            String timeStamp = simpleDateFormat.format(new Date());
            String fileName = "backup_"+timeStamp+".sql";// backup_3234.sql

            String sqlCommand = "mysqldump --user="+userName+" --password="+password+" --host=localhost "+database+" --result-file="+fileName;
            Process process= Runtime.getRuntime().exec(sqlCommand);
            int exitCode = process.waitFor();

            if(exitCode==0){
                File backup = new File(fileName);

                if (backup.exists()){
                    FileInputStream fileInputStream = new FileInputStream(backup);
                    FileOutputStream fileOutputStream = new FileOutputStream("src/backups/"+fileName);

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead=fileInputStream.read(buffer))!=-1){
                        fileOutputStream.write(buffer,0,bytesRead);
                    }

                    new Alert(Alert.AlertType.INFORMATION,"Backup File was Created!").show();

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) dashboardcontext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }



}
