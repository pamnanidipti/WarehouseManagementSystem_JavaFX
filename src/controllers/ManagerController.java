/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import application.LoginController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class ManagerController implements Initializable{
    @FXML
    public Text contactAdminLabel;
    @FXML
    public Label managerFnameLabel;
    @FXML
    public Label managerLnameLabel;
    @FXML
    public Label managerIdLabel;
    @FXML
    public Label managerEmailLabel;
    @FXML
    public Label managerCityLabel;
    
    @FXML
    private Button logout;
    @FXML
    private Button manageEmployee;
    @FXML
    private Button manageProducts;
    @FXML
    private Button viewOrders;
    @FXML
    private Button sales;
    @FXML
    private Button viewSelfDetails;
    
    @FXML
    private GridPane managerDetailsGrid;
    
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
            managerDetailsGrid.setVisible(true);
           contactAdminLabel.setVisible(true);
           viewSelfDetails.setDisable(true);
            //System.out.println("manager: "+sessionUser);
            
            //String sqlQuery = "select * FROM manager where managerUserName ='"+sessionUser+"';";
            String sqlQuery = "select * FROM manager where managerUserName ='m';";
            connection = conn.connect();
            statement = connection.createStatement();
            rslt = statement.executeQuery(sqlQuery);
            while (rslt.next()) {
                managerFnameLabel.setText(rslt.getString("managerFirstName"));
                managerLnameLabel.setText(rslt.getString("managerLastName"));
                managerIdLabel.setText(String.valueOf(rslt.getInt("managerId")));
                managerEmailLabel.setText(rslt.getString("managerEmailId"));
                managerCityLabel.setText(rslt.getString("managerCity"));
            }
            connection.close();
            statement.close();
            rslt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    public void manageEmployeeOnClick(Event event) throws IOException{
        FXMLLoader fxload = new FXMLLoader();
        fxload.setLocation(getClass().getResource("/views/ManageEmployee.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Manage Employee");
        stage.show();
        
    }
    
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//         

    }

}
