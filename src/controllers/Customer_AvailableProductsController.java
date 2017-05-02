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
 * @author Aakash Tyagi
 */
public class Customer_AvailableProductsController implements Initializable{
   
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
    private TableView<Customer_AvailablePOJOTable> availableProductstableview;

    @FXML
    TableColumn<Customer_AvailablePOJOTable, Integer> ProductIdTableColumn;
    @FXML
    TableColumn<Customer_AvailablePOJOTable, Integer> AvailableQuantityTableColumn;
    
    @FXML
    TableColumn<Customer_AvailablePOJOTable, String> ProductNameTableColumn;
    @FXML
    TableColumn<Customer_AvailablePOJOTable, String> ProductStatusTableColumn;
    @FXML
    TableColumn<Customer_AvailablePOJOTable, String> ProductDescriptionTableColumn;
   
    
    
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
    private void backtoCustomerProfileOnClick(Event event) {
        try {
            FXMLLoader fxload = new FXMLLoader();
            fxload.setLocation(getClass().getResource("/views/Customer.fxml"));
            fxload.load();
            Parent parent = fxload.getRoot();
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("Customer Panel");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
		{
		ProductIdTableColumn.setCellValueFactory(new PropertyValueFactory<Customer_AvailablePOJOTable, Integer>("productID"));
		AvailableQuantityTableColumn.setCellValueFactory(new PropertyValueFactory<Customer_AvailablePOJOTable, Integer>("availQuant"));
		ProductNameTableColumn.setCellValueFactory(new PropertyValueFactory<Customer_AvailablePOJOTable, String>("proName"));
		ProductStatusTableColumn.setCellValueFactory(new PropertyValueFactory<Customer_AvailablePOJOTable, String>("ProStatus"));
		ProductDescriptionTableColumn.setCellValueFactory(new PropertyValueFactory<Customer_AvailablePOJOTable, String>("ProDesc"));
	        try {
	            buildData();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    
		
	}
    
   
	private ObservableList<Customer_AvailablePOJOTable> data;

    public void buildData() {
        try {
            data = FXCollections.observableArrayList();
            connection = conn.connect();
            statement = connection.createStatement();

            String SQL = "Select * from product where productStatus = 'Available';";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
            	Customer_AvailablePOJOTable cm = new Customer_AvailablePOJOTable();
                //System.out.println("rs id"+rs.getInt("adminId"));
                cm.productID.set(rs.getInt("productId"));
                cm.availQuant.set(rs.getInt("productQuantity"));
                cm.proName.set(rs.getString("productName"));
                cm.ProStatus.set(rs.getString("productStatus"));
                cm.ProDesc.set(rs.getString("productDescription"));
                data.add(cm);
            }

            availableProductstableview.setItems(data);
            connection.close();
            statement.close();

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
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

}
