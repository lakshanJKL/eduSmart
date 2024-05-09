package com.devstack.edu.controller;

import com.devstack.edu.bo.custome.BoFactory;
import com.devstack.edu.bo.custome.PaymentBo;
import com.devstack.edu.db.DBConnection;
import com.devstack.edu.dto.PaymentDto;
import com.devstack.edu.view.tm.IncomeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;

public class IncomeFormController {
    public AnchorPane incomecontext;
    public ToggleGroup income;
    public AnchorPane chart;
    public RadioButton rdbtnall;
    public RadioButton rdbtntoday;
    public RadioButton rdbtnlast_month;

    PaymentBo paymentBo = BoFactory.getBo(BoFactory.DataType.PAYMENT);

    public void initialize(){
        loadLineChart();
    }
    public void icomebackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard");
    }
    public void getday(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (rdbtnall.isSelected()) {
            chart.getChildren().clear();
            ObservableList<IncomeTM> obList = FXCollections.observableArrayList();

            for (PaymentDto paymentDto : paymentBo.getData()){
                obList.add(new IncomeTM(
                        paymentDto.getDate(),paymentDto.getAmount()
                ));
            }
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            LineChart<String,Number> lineChart=new LineChart<>(xAxis,yAxis);
            XYChart.Series<String,Number> series = new XYChart.Series<>();

            obList.forEach(e->{
                series.getData().add(new XYChart.Data<>(e.getDate().toString(),e.getAmount()));
            });
            lineChart.getData().add(series);

            AnchorPane.setTopAnchor(lineChart, 0.0);
            AnchorPane.setRightAnchor(lineChart, 0.0);
            AnchorPane.setLeftAnchor(lineChart, 0.0);
            AnchorPane.setBottomAnchor(lineChart, 0.0);

            chart.getChildren().add(lineChart);

        }
        else if (rdbtntoday.isSelected()) {
            chart.getChildren().clear();
            ObservableList<IncomeTM> obListtoday = FXCollections.observableArrayList();

            for (PaymentDto paymentDto : paymentBo.getData()){
                String[] datestr = String.valueOf(LocalDate.now()).split("-");
                int month = Integer.parseInt(datestr[1]);

                LocalDate date = paymentDto.getDate();
                if(date.getMonth()==Month.of(month)) {
                    obListtoday.add(new IncomeTM(
                            date,
                            paymentDto.getAmount()
                    ));
                }
            }
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            LineChart<String,Number> lineChart=new LineChart<>(xAxis,yAxis);
            XYChart.Series<String,Number> series = new XYChart.Series<>();

            obListtoday.forEach(e->{
                series.getData().add(new XYChart.Data<>(e.getDate().toString(),e.getAmount()));
            });
            lineChart.getData().add(series);

            AnchorPane.setTopAnchor(lineChart, 0.0);
            AnchorPane.setRightAnchor(lineChart, 0.0);
            AnchorPane.setLeftAnchor(lineChart, 0.0);
            AnchorPane.setBottomAnchor(lineChart, 0.0);

            chart.getChildren().add(lineChart);
        }
        else if (rdbtnlast_month.isSelected()) {
            chart.getChildren().clear();
            ObservableList<IncomeTM> obListlastmonth = FXCollections.observableArrayList();

            for (PaymentDto paymentDto : paymentBo.getData()){
                String[] datestr = String.valueOf(LocalDate.now()).split("-");
                int month = Integer.parseInt(datestr[1])-1;
                LocalDate date = paymentDto.getDate();
                if(date.getMonth()==Month.of(month)) {
                    obListlastmonth.add(new IncomeTM(
                            date,
                            paymentDto.getAmount()
                    ));
                }
            }
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();

            LineChart<String,Number> lineChart3=new LineChart<>(xAxis,yAxis);
            XYChart.Series<String,Number> series = new XYChart.Series<>();

            obListlastmonth.forEach(e->{
                series.getData().add(new XYChart.Data<>(e.getDate().toString(),e.getAmount()));
            });
            lineChart3.getData().add(series);

            AnchorPane.setTopAnchor(lineChart3, 0.0);
            AnchorPane.setRightAnchor(lineChart3, 0.0);
            AnchorPane.setLeftAnchor(lineChart3, 0.0);
            AnchorPane.setBottomAnchor(lineChart3, 0.0);

            chart.getChildren().add(lineChart3);
        }
    }
    private void loadLineChart() {
        chart.getChildren().clear();
        ObservableList<IncomeTM> obList = FXCollections.observableArrayList();
        try {
            for (PaymentDto paymentDto : paymentBo.getData()){
                obList.add(new IncomeTM(
                        paymentDto.getDate(),paymentDto.getAmount()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<String,Number> lineChart=new LineChart<>(xAxis,yAxis);
        XYChart.Series<String,Number> series = new XYChart.Series<>();

        obList.forEach(e->{
            series.getData().add(new XYChart.Data<>(e.getDate().toString(),e.getAmount()));
        });
        lineChart.getData().add(series);

        AnchorPane.setTopAnchor(lineChart, 0.0);
        AnchorPane.setRightAnchor(lineChart, 0.0);
        AnchorPane.setLeftAnchor(lineChart, 0.0);
        AnchorPane.setBottomAnchor(lineChart, 0.0);

        chart.getChildren().add(lineChart);
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) incomecontext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
