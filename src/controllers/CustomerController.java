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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.AdminPOJOTable;
import models.Admin_CustomerPOJOTable;
import models.Connector;
import models.Customer_AvailablePOJOTable;

/**
 *
 * @author Pamnani
 */
public class CustomerController implements Initializable{
    @FXML
    public Label contactAdminLabel;
    @FXML
    public Label cutomerFNLabel;
    @FXML
    public Label cutomerLNLabel;
    @FXML
    public Label cutomerIdLabel;
    @FXML
    public Label cutomerEmailLabel;
    @FXML
    public Label customerAddressLabel;
    @FXML
    public Label cutomerUserNameLabel;
    @FXML
    private Button logout;
    @FXML
    private Button placeorders;
    @FXML
    private Button trackOrder;
    @FXML
    private Button AvailableProducts;
    @FXML
    private Button viewSelfDetails;
    
    @FXML
    private GridPane CustomerInformationGrid;
    
    public static String sessionUser;
    
    public static String getSessionUser() {
		return sessionUser;
	}

	public static void setSessionUser(String sessionUser) {
		CustomerController.sessionUser = sessionUser;
	}

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
        	CustomerInformationGrid.setVisible(true);
          contactAdminLabel.setVisible(true);
          viewSelfDetails.setDisable(true);
           // System.out.println("customer: "+sessionUser);
            
            String sqlQuery = "select * FROM customer where customerUserName ='"+sessionUser+"';";
            //String sqlQuery = "select * FROM customer where customerUserName ='c';";
            connection = conn.connect();
            statement = connection.createStatement();
            rslt = statement.executeQuery(sqlQuery);
            while (rslt.next()) {
            	cutomerFNLabel.setText(rslt.getString("customerFirstName"));
            	cutomerLNLabel.setText(rslt.getString("customerLastName"));
            	cutomerIdLabel.setText(String.valueOf(rslt.getInt("customerId")));
            	cutomerEmailLabel.setText(rslt.getString("customerEmailId"));
            	cutomerUserNameLabel.setText(rslt.getString("customerUserName"));
            	customerAddressLabel.setText(rslt.getString("customerAddress"));
            }
            connection.close();
            statement.close();
            rslt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    
    
    @FXML
    private void ViewAvailableProductsclick(Event event) throws IOException {

        FXMLLoader fxload = new FXMLLoader();
        fxload.setLocation(getClass().getResource("/views/AvailableProducts.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Available Products");
        stage.show();
    }
    
    @FXML
    private void placeOrdersOnclick(Event event) throws IOException {

        FXMLLoader fxload = new FXMLLoader();
        fxload.setLocation(getClass().getResource("/views/Customer_PlaceOrders.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Place Orders");
        stage.show();
    }
    
    @FXML
    private void trackOrdersOnclick(Event event) throws IOException {

        FXMLLoader fxload = new FXMLLoader();
        fxload.setLocation(getClass().getResource("/views/Customer_TrackOrders.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Track Orders");
        stage.show();
        Customer_TrackOrdersController controller = fxload.<Customer_TrackOrdersController>getController();
        
        controller.sessionUser=this.sessionUser;
        controller.buildData();
        //controller.initialize(sessionUser, null);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
		{
	    
		
	}
    
   

 

}
