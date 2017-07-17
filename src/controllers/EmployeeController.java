/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.ManagerController.sessionUser;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Connector;

/**
 *
 * @author Pamnani
 */
public class EmployeeController {
 
    @FXML
    public Text contactAdminLabel;
    @FXML
    public Label empFnameLabel,empLNameLabel,empIDLabel,empEmailLabel,empCityLabel,empDeptLabel,empManagerLabel;
    
    @FXML
    private Button logout,viewSelfDetails,processCustomerOrders,warehouseInventory;
    
    @FXML
    private GridPane employeeDetailsGrid;
    
    public static String sessionUser;
    
    private Connector conn = new Connector();
    private Connection connection;
    private Statement statement;
    private ResultSet rslt;
    
    @FXML
    private void setLogoutButtonClick(Event event) throws IOException {

        FXMLLoader fxload = new FXMLLoader();
        fxload.setLocation(getClass().getResource("/application/Login.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Login Page - Warehouse Management System");
        stage.show();
    }
    
    @FXML
    public void viewSelfDetailsOnClick(Event event) throws IOException {
        try {
            employeeDetailsGrid.setVisible(true);
           contactAdminLabel.setVisible(true);
           viewSelfDetails.setDisable(true);
            //System.out.println("manager: "+sessionUser);
            
            //String sqlQuery = "select * FROM manager where managerUserName ='"+sessionUser+"';";
            String sqlQuery = "select * FROM employee where employeeUserName ='"+sessionUser+"';";
            connection = conn.connect();
            statement = connection.createStatement();
            rslt = statement.executeQuery(sqlQuery);
            while (rslt.next()) {
                empFnameLabel.setText(rslt.getString("employeeFirstName"));
                empLNameLabel.setText(rslt.getString("employeeLastName"));
                empIDLabel.setText(String.valueOf(rslt.getInt("employeeId")));
                empEmailLabel.setText(rslt.getString("employeeEmailId"));
                empCityLabel.setText(rslt.getString("employeeCity"));
                empDeptLabel.setText(rslt.getString("employeeDepartment"));
                empManagerLabel.setText(rslt.getString("employeeManager"));
            }
            connection.close();
            statement.close();
            rslt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    public void processCustomerOrdersOnClick(Event event) throws IOException{
        
        FXMLLoader fxload = new FXMLLoader();
        fxload.setLocation(getClass().getResource("/views/Employee_ProcessCustomerOrders.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Process Customer Orders");
        stage.show();
        Employee_ProcessCustomerOrdersController controller = fxload.<Employee_ProcessCustomerOrdersController>getController();
        controller.sessionUser=this.sessionUser;
        //controller.set();
    }
    @FXML
    public void warehouseInventoryOnClick(Event event) throws IOException{
        
        FXMLLoader fxload = new FXMLLoader();
        fxload.setLocation(getClass().getResource("/views/Employee_WarehouseInventory.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Manage Warehouse Inventory");
        stage.show();
        //Employee_ProcessCustomerOrdersController controller = fxload.<Employee_ProcessCustomerOrdersController>getController();
        //controller.sessionUser=this.sessionUser;
        //controller.set();
    }
    
}
