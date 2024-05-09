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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportController {
    public AnchorPane reportcontext;
    public AnchorPane repo_chart;
    PaymentBo paymentBo = BoFactory.getBo(BoFactory.DataType.PAYMENT);

    public void initialize() {
        loadRepoLineChart();
    }
    public void reposrtbackOnAction(ActionEvent actionEvent) throws IOException {
        setUi("Dashboard");
    }
    private void loadRepoLineChart() {
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

        AnchorPane.setTopAnchor(lineChart, 50.0);
        AnchorPane.setRightAnchor(lineChart, 0.0);
        AnchorPane.setLeftAnchor(lineChart, 0.0);
        AnchorPane.setBottomAnchor(lineChart, 0.0);

        repo_chart.getChildren().add(lineChart);
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) reportcontext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
