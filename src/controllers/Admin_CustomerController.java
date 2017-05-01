package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.AdminPOJOTable;
import models.Admin_CustomerPOJOTable;
import models.Connector;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * @author Aakash Tyagi Date : 20 April 2017
 * @param args the command line arguments
 * @throws InterruptedException
 */
public class Admin_CustomerController implements Initializable {

    public static String sessionUser = null;
    private static int count;

    @FXML
    private TextField NewCustomertfFirstName;
    @FXML
    private TextField NewCustomertfLastName;
    @FXML
    private TextField NewCustomertfId;
    @FXML
    private TextField NewCustomertfEmailId;
    @FXML
    private PasswordField NewCustomerpfPassword;
    @FXML
    private TextField NewCustomertfUserName;
    @FXML
    private TextField NewCustomertfAddress;

    @FXML
    private TableView<Admin_CustomerPOJOTable> NewCustomerDetailsTableView;

    @FXML
    TableColumn<Admin_CustomerPOJOTable, Integer> CustomerDetailsId;
    @FXML
    TableColumn<Admin_CustomerPOJOTable, String> CustomerDetailsFname;
    @FXML
    TableColumn<Admin_CustomerPOJOTable, String> CustomerDetailsLname;
    @FXML
    TableColumn<Admin_CustomerPOJOTable, String> CustomerDetailsAddress;

   @FXML
    private Button NewCustomersave;

    @FXML
    private Button refreshButtonNewCustomer;

    @FXML
    private Button NewCustomerbackToAdminPanel;
  
    @FXML
    private Button editNewCustomer;

    @FXML
    private Button deleteNewCustomer;
    @FXML
    private Button NewCustomerview;
    @FXML
    private Button NewCustomersaveChanges;

   private void setAllFieldDisableOnClick() {
	   NewCustomertfFirstName.setDisable(true);
	   NewCustomertfLastName.setDisable(true);
	   NewCustomertfId.setDisable(true);
	   NewCustomertfEmailId.setDisable(true);
	   NewCustomerpfPassword.setDisable(true);
	   NewCustomertfUserName.setDisable(true);
	   NewCustomertfAddress.setDisable(true);
    }

    private void setAllFieldEnableOnClick() {
    	NewCustomertfFirstName.setDisable(false);
    	NewCustomertfLastName.setDisable(false);
        //NewCustomertfId.setDisable(false);
    	NewCustomertfEmailId.setDisable(false);
    	NewCustomerpfPassword.setDisable(false);
    	NewCustomertfUserName.setDisable(false);
    	NewCustomertfAddress.setDisable(false);
    }

    private void setAllFieldClearOnClick() {
    	NewCustomertfFirstName.clear();
    	NewCustomertfLastName.clear();
    	NewCustomertfId.clear();
    	NewCustomertfEmailId.clear();
    	NewCustomerpfPassword.clear();
    	NewCustomertfUserName.clear();
    	NewCustomertfAddress.clear();

    }

    private Connector conn = new Connector();
    private Connection connection;
    private Statement statement;
    private ResultSet rslt;
    @FXML
    private Label addNewCustomerSaveLabel;

    public boolean checkFieldsEmpty() {
        if (NewCustomertfFirstName.getText().equals("") || NewCustomertfLastName.getText().equals("") || NewCustomertfId.getText().equals("")
                || NewCustomerpfPassword.getText().equals("") || NewCustomertfUserName.getText().equals("") || NewCustomertfAddress.getText().equals("")) {
            //save.setDisable(true);
        	addNewCustomerSaveLabel.setTextFill(Color.web("red"));
        	addNewCustomerSaveLabel.setText("Please enter all values!");

            return true;
        } else {
            return false;
        }
    }
    
    @FXML
    private void setNewCustomerSaveClick(Event event) {
        setAllFieldEnableOnClick();
        try {

            connection = conn.connect();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from customer");
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            NewCustomertfId.setText(String.valueOf(count + 1));
            if (checkFieldsEmpty()) {

                return;
            } else {
                //save.setDisable(false);
                String sqlQuery = "insert into customer (customerId,customerFirstName,customerLastName,customerUserName,customerPassword,customerEmailId,customerAddress )" + "values (" + NewCustomertfId.getText() + " , '" + NewCustomertfFirstName.getText() + "','" + NewCustomertfLastName.getText() + "','" + NewCustomertfUserName.getText() + "','" + NewCustomerpfPassword.getText() + "','" + NewCustomertfEmailId.getText() + "','" + NewCustomertfAddress.getText() + "')";
                //System.out.println(sqlQuery);
                statement.executeUpdate(sqlQuery);
                addNewCustomerSaveLabel.setTextFill(Color.web("green"));
                addNewCustomerSaveLabel.setText("customer Saved to Database");
                setAllFieldClearOnClick();
                setAllFieldDisableOnClick();
                refreshButtonOnClick(event);
            }
            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            addNewCustomerSaveLabel.setText("Customer Not Saved to Database");
            System.out.println("Values are not inserted in customer table after save button");
            e.printStackTrace();
        }
    }

    @FXML
    private void refreshButtonOnClick(Event event) {
        try {
            buildData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backToAdminPanelOnClick(Event event) {
        try {
            FXMLLoader fxload = new FXMLLoader();
            fxload.setLocation(getClass().getResource("/views/Admin.fxml"));
            fxload.load();
            Parent parent = fxload.getRoot();
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("Admin Panel");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Admin_CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean ifRowSelected() {
        if (NewCustomerDetailsTableView.getSelectionModel().getSelectedItems().size() == 0) {
            NotificationType notificationType = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Attention!!!");
            tray.setMessage("You need to select atleast one row!");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));
            return false;
        } else {
            return true;
        }
    }

    public void getRowDetails() {
        try {
            TablePosition pos = NewCustomerDetailsTableView.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Admin_CustomerPOJOTable item = NewCustomerDetailsTableView.getItems().get(row);
            int customerID = item.getCustomerID();
            
            connection = conn.connect();
            statement = connection.createStatement();

            String sql = "Select * from customer where customerId=" + customerID + ";";
            //String sql = "Select * from admin where adminId=1;";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
            	NewCustomertfFirstName.setText(rs.getString("customerFirstName"));
            	NewCustomertfLastName.setText(rs.getString("customerLastName"));
            	NewCustomertfId.setText(String.valueOf(rs.getInt("customerId")));
            	NewCustomertfLastName.setText(rs.getString("customerLastName"));
                NewCustomertfEmailId.setText(rs.getString("customerEmailId"));
                NewCustomerpfPassword.setText(rs.getString("customerPassword"));
                NewCustomertfUserName.setText(rs.getString("customerUserName"));
                NewCustomertfAddress.setText(rs.getString("customerAddress"));
            }
            connection.close();
            statement.close();

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @FXML
    private void saveChangesOnClick(Event event) {
        if (!checkFieldsEmpty()) {
            try {
                connection = conn.connect();
                statement = connection.createStatement();

                statement.executeUpdate("update customer set customerFirstName ='" + NewCustomertfFirstName.getText() + "',customerLastName='" + NewCustomertfLastName.getText() + "',customerUserName ='" + NewCustomertfUserName.getText() + "',customerPassword ='" + NewCustomerpfPassword.getText() + "',customerEmailId ='" + NewCustomertfEmailId.getText() + "',customerAddress='" + NewCustomertfAddress.getText() + "' where customerId=" + NewCustomertfId.getText() + ";");
                //System.out.println("Your Details have been updated successfully!");
                addNewCustomerSaveLabel.setTextFill(Color.web("green"));
                addNewCustomerSaveLabel.setText("Details updated successfully!");
                setAllFieldDisableOnClick();
                setAllFieldClearOnClick();
                NewCustomersave.setDisable(false);
                refreshButtonOnClick(event);
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Admin_CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    @FXML
    private void editcustomerOnClick(Event event) {

        try {

            if (ifRowSelected()) {
                getRowDetails();
                setAllFieldEnableOnClick();
                NewCustomersave.setDisable(true);
                NewCustomersaveChanges.setDisable(false);
            }

        } catch (Exception ex) {
            Logger.getLogger(Admin_CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void deletecustomerOnClick(Event event) {
        if (ifRowSelected()) {
            try {
                TablePosition pos = NewCustomerDetailsTableView.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Admin_CustomerPOJOTable item = NewCustomerDetailsTableView.getItems().get(row);
                int gCustomerID = item.getCustomerID();
                //System.out.println("admin id: " + adminID);
                connection = conn.connect();
                statement = connection.createStatement();
                
                String sql = "delete from customer where customerId=" + gCustomerID + ";";
                statement.executeUpdate(sql);
                refreshButtonOnClick(event);
                NotificationType notificationType = NotificationType.INFORMATION;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Attention!!!");
            tray.setMessage("Admin Deleted from System!");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));
            } catch (SQLException ex) {
                Logger.getLogger(Admin_CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void viewCustomerOnClick(Event event) {
        if (ifRowSelected()) {
            getRowDetails();
            setAllFieldDisableOnClick();
            NewCustomersave.setDisable(true);
        }

    }
    /* 
     @FXML
     private void setAddAdminSaveClick(Event event){
     //String sqlQuery = "select * FROM admin where adminUserName ='"+sessionUser+"'; ";
     try {
            
          
     } catch(Exception e){
     e.printStackTrace();
     }
     }*/

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
    private void setNewCustomerCancelButton(Event event) {
        setAllFieldDisableOnClick();
        setAllFieldClearOnClick();
        addNewCustomerSaveLabel.setText(" Data has been Reset");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	CustomerDetailsId.setCellValueFactory(new PropertyValueFactory<Admin_CustomerPOJOTable, Integer>("customerID"));
    	CustomerDetailsFname.setCellValueFactory(new PropertyValueFactory<Admin_CustomerPOJOTable, String>("customerFName"));
    	CustomerDetailsLname.setCellValueFactory(new PropertyValueFactory<Admin_CustomerPOJOTable, String>("customerLName"));
    	CustomerDetailsAddress.setCellValueFactory(new PropertyValueFactory<Admin_CustomerPOJOTable, String>("customerAddress"));
        try {
            buildData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private ObservableList<Admin_CustomerPOJOTable> data;

    public void buildData() {
        try {
            data = FXCollections.observableArrayList();
            connection = conn.connect();
            statement = connection.createStatement();

            String SQL = "Select * from customer;";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
            	Admin_CustomerPOJOTable cm = new Admin_CustomerPOJOTable();
                //System.out.println("rs id"+rs.getInt("adminId"));
            	cm.customerID.set(rs.getInt("customerId"));
                cm.customerFName.set(rs.getString("customerFirstName"));
                cm.customerLName.set(rs.getString("customerLastName"));
                cm.customerAddress.set(rs.getString("customerAddress"));
                data.add(cm);
            }

            NewCustomerDetailsTableView.setItems(data);
            connection.close();
            statement.close();

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}
