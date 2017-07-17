/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.AdminPOJOTable;
import models.Connector;
import models.Manager_POJOTable;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Pamnani
 */
public class Admin_ManageManagerController implements Initializable {

    @FXML
    private TextField ManagerFName;
    @FXML
    private TextField ManagerLName;
    @FXML
    private TextField ManagerId;
    @FXML
    private TextField ManagerEmailId;
    @FXML
    private TextField ManagerPassword;
    @FXML
    private TextField ManagerUserName;
    @FXML
    private TextField ManagerCity;
    @FXML
    private ChoiceBox ManagerDeptBox;
    
    @FXML
    private Label ManagerLabel;

    @FXML
    private Button backToAdminButton, logout, viewManagerButton, editManagerButton, deleteManagerButton, refreshManagerButton,
    managerCancelButton, saveChangesButton, savemanagerButton;

    @FXML
    private TableView<Manager_POJOTable> managerDetailsTable;
    
    @FXML
    TableColumn<Manager_POJOTable, Integer> mngidDetailtable;
    @FXML
    TableColumn<Manager_POJOTable, String> fnameDetailtable;
    @FXML
    TableColumn<Manager_POJOTable, String> lnameDetailtable;
    @FXML
    TableColumn<Manager_POJOTable, String> deptDetailtable;
        
    
    public static String sessionUser;
    private Connector conn = new Connector();
    private Connection connection;
    private Statement statement;
    private ResultSet rslt;

    @FXML
    private void setAllFieldDisableOnClick() {
    	ManagerFName.setDisable(true);
    	ManagerLName.setDisable(true);
    	ManagerId.setDisable(true);
    	ManagerEmailId.setDisable(true);
    	ManagerPassword.setDisable(true);
    	ManagerUserName.setDisable(true);
    	ManagerCity.setDisable(true);
    	ManagerDeptBox.setDisable(true);
        
    }

    @FXML
    private void setAllFieldEnableOnClick() {
    	ManagerFName.setDisable(false);
    	ManagerLName.setDisable(false);
        //ManagerId.setDisable(false);
    	ManagerEmailId.setDisable(false);
    	ManagerPassword.setDisable(false);
    	ManagerUserName.setDisable(false);
    	ManagerCity.setDisable(false);
    	ManagerDeptBox.setDisable(false);
        
    }

    @FXML
    private void setAllFieldClearOnClick() {
    	ManagerFName.clear();
    	ManagerLName.clear();
    	ManagerId.clear();
    	ManagerEmailId.clear();
    	ManagerPassword.clear();
    	ManagerUserName.clear();
    	ManagerCity.clear();

    }

    @FXML
    private void employeeCancelButtonOnClick(Event event) {
        //setAllFieldDisableOnClick();
        setAllFieldClearOnClick();
        ManagerLabel.setText("");

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
    
    public boolean checkFieldsEmpty() {
        if (ManagerFName.getText().equals("") || ManagerLName.getText().equals("") || ManagerEmailId.getText().equals("")
                || ManagerPassword.getText().equals("") || ManagerUserName.getText().equals("") || ManagerCity.getText().equals("") ||
                ManagerDeptBox.getValue().toString().trim().equals(null)) {
            //save.setDisable(true);
        	ManagerLabel.setTextFill(Color.web("red"));
        	ManagerLabel.setText("Please enter all values!");

            return true;
        } else {
            return false;
        }
    }
    
    private static int count;
    @FXML
    private void saveManagerButtonOnClick(Event event) {
        setAllFieldEnableOnClick();
        try {
//            System.out.println("dept:"+employeeDeptBox.getValue().toString().trim());
            connection = conn.connect();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from manager");
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            ManagerId.setText(String.valueOf(count + 1));
            if (checkFieldsEmpty()) {

                return;
            } else {
                //save.setDisable(false);
                String sqlQuery = "insert into manager values (" + ManagerId.getText() + " , '" + ManagerFName.getText() + "','" + ManagerLName.getText() + "','" + ManagerUserName.getText() + "','" + ManagerPassword.getText() + "','" + ManagerEmailId.getText() + "','" + ManagerCity.getText() + "','" + ManagerDeptBox.getValue().toString().trim() + "')";
                //System.out.println(sqlQuery);
                statement.executeUpdate(sqlQuery);
                ManagerLabel.setTextFill(Color.web("green"));
                ManagerLabel.setText("Manager Saved to Database");
                setAllFieldClearOnClick();
                setAllFieldDisableOnClick();
                refreshEmployeeButtonOnClick(event);
            }
            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
        	ManagerLabel.setText("Manager Not Saved to Database");
            System.out.println("Values are not inserted in manager table after save button");
            e.printStackTrace();
        }
    }
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

    public void set() 
         { try {
            //System.out.println("manage emp:"+sessionUser);
        	ManagerDeptBox.getItems().add("Inventory");
        	ManagerDeptBox.getItems().add("Production");
        	ManagerDeptBox.getItems().add("Supply");
        	ManagerDeptBox.getItems().add("Marketing");}

           /* connection = conn.connect();
            statement = connection.createStatement();
            String sql = "Select managerFirstName,managerLastName from manager;";
            rslt = statement.executeQuery(sql);
            while (rslt.next()) {
                String fullname = rslt.getString("managerFirstName") + " " + rslt.getString("managerLastName");
                employeeManagerBox.getItems().add(fullname);

            }
            connection.close();
            statement.close();
            rslt.close();*/
         catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Values not set");
        }
         
         }
         
         /*(SQLException ex) {
            Logger.getLogger(Admin_ManageManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    
    @FXML
    private void refreshEmployeeButtonOnClick(Event event) {
        try {
            buildData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean ifRowSelected() {
        if (managerDetailsTable.getSelectionModel().getSelectedItems().size() == 0) {
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
            TablePosition pos = managerDetailsTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Manager_POJOTable item = managerDetailsTable.getItems().get(row);
            int mngID = item.getManagerID();
            //System.out.println("admin id: " + adminID);
            connection = conn.connect();
            statement = connection.createStatement();

            String sql = "Select * from manager where managerId=" + mngID + ";";
            //String sql = "Select * from admin where adminId=1;";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                ManagerFName.setText(rs.getString("managerFirstName"));
                ManagerLName.setText(rs.getString("managerLastName"));
                ManagerId.setText(String.valueOf(rs.getInt("managerId")));
                //addAdmintfLastName.setText(rs.getString("adminLastName"));
                ManagerEmailId.setText(rs.getString("managerEmailId"));
                ManagerPassword.setText(rs.getString("managerPassword"));
                ManagerUserName.setText(rs.getString("managerUserName"));
                ManagerCity.setText(rs.getString("managerCity"));
                ManagerDeptBox.setValue(rs.getString("managerDepartment"));
                
            }
            connection.close();
            statement.close();

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editManagerButtonOnClick(Event event) {

        try {

            if (ifRowSelected()) {
                getRowDetails();
                setAllFieldEnableOnClick();
                savemanagerButton.setDisable(true);
                saveChangesButton.setDisable(false);
            }

        } catch (Exception ex) {
            Logger.getLogger(AddAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteManagerButtonOnClick(Event event) {
        if (ifRowSelected()) {
            try {
                TablePosition pos = managerDetailsTable.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Manager_POJOTable item = managerDetailsTable.getItems().get(row);
                int mngID = item.getManagerID();
                //System.out.println("admin id: " + adminID);
                connection = conn.connect();
                statement = connection.createStatement();
                
                String sql = "delete from manager where managerId=" + mngID + ";";
                statement.executeUpdate(sql);
                refreshEmployeeButtonOnClick(event);
                NotificationType notificationType = NotificationType.INFORMATION;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Attention!!!");
            tray.setMessage("Employee Deleted from System!");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }
    @FXML
    private void viewEmployeeButtonOnClick(Event event) {
        if (ifRowSelected()) {
            getRowDetails();
            setAllFieldDisableOnClick();
            ManagerLabel.setDisable(true);
        }

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	mngidDetailtable.setCellValueFactory(new PropertyValueFactory<Manager_POJOTable, Integer>("mngID"));
    	fnameDetailtable.setCellValueFactory(new PropertyValueFactory<Manager_POJOTable, String>("FName"));
    	lnameDetailtable.setCellValueFactory(new PropertyValueFactory<Manager_POJOTable, String>("LName"));
    	deptDetailtable.setCellValueFactory(new PropertyValueFactory<Manager_POJOTable, String>("department"));
        
        try {
            buildData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void saveChangesButtonOnClick(Event event) {
        if (!checkFieldsEmpty()) {
            try {
                connection = conn.connect();
                statement = connection.createStatement();

                statement.executeUpdate("update manager set managerFirstName ='" + ManagerFName.getText() + "',managerLastName='" + ManagerLName.getText() + "',managerUserName ='" + ManagerUserName.getText() + "',managerPassword ='" + ManagerPassword.getText() + "',managerEmailId ='" + ManagerEmailId.getText() + "',managerCity='" + ManagerCity.getText() +"',managerDepartment='" + ManagerDeptBox.getValue() +"' where managerId=" + ManagerId.getText() + ";");
                //System.out.println("Your Details have been updated successfully!");
                ManagerLabel.setTextFill(Color.web("green"));
                ManagerLabel.setText("Details updated successfully!");
                setAllFieldDisableOnClick();
                setAllFieldClearOnClick();
                savemanagerButton.setDisable(false);
                refreshEmployeeButtonOnClick(event);
                saveChangesButton.setDisable(true);
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    
    private ObservableList<Manager_POJOTable> data;

    public void buildData() {
        try {
            data = FXCollections.observableArrayList();
            connection = conn.connect();
            statement = connection.createStatement();

            String SQL = "Select * from manager;";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                Manager_POJOTable cm = new Manager_POJOTable();
                //System.out.println("rs id"+rs.getInt("adminId"));
                cm.mngID.set(rs.getInt("managerId"));
                cm.FName.set(rs.getString("managerFirstName"));
                cm.LName.set(rs.getString("managerLastName"));
                cm.department.set(rs.getString("managerDepartment"));
                data.add(cm);
            }

            managerDetailsTable.setItems(data);
            connection.close();
            statement.close();

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}
