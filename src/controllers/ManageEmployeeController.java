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
import models.EmployeePOJO;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Pamnani
 */
public class ManageEmployeeController implements Initializable {

    @FXML
    private TextField employeeFName;
    @FXML
    private TextField employeeLName;
    @FXML
    private TextField employeeId;
    @FXML
    private TextField employeeEmailId;
    @FXML
    private TextField employeePassword;
    @FXML
    private TextField employeeUserName;
    @FXML
    private TextField employeeCity;
    @FXML
    private ChoiceBox employeeDeptBox;
    @FXML
    private ChoiceBox employeeManagerBox;
    @FXML
    private Label employeeLabel;

    @FXML
    private Button backToManagerButton, logout, viewEmployeeButton, editEmployeeButton, deleteEmployeeButton, refreshEmployeeButton,
            employeeCancelButton, saveChangesButton, saveEmployeeButton;

    @FXML
    private TableView<EmployeePOJO> employeeDetailsTable;
    
    @FXML
    TableColumn<EmployeePOJO, Integer> empidDetail;
    @FXML
    TableColumn<EmployeePOJO, String> fnameDetail;
    @FXML
    TableColumn<EmployeePOJO, String> lnameDetail;
    @FXML
    TableColumn<EmployeePOJO, String> deptDetail;
    @FXML
    TableColumn<EmployeePOJO, String> managerDetail;
    
    public static String sessionUser;
    private Connector conn = new Connector();
    private Connection connection;
    private Statement statement;
    private ResultSet rslt;

    @FXML
    private void setAllFieldDisableOnClick() {
        employeeFName.setDisable(true);
        employeeLName.setDisable(true);
        employeeId.setDisable(true);
        employeeEmailId.setDisable(true);
        employeePassword.setDisable(true);
        employeeUserName.setDisable(true);
        employeeCity.setDisable(true);
        employeeDeptBox.setDisable(true);
        employeeManagerBox.setDisable(true);
    }

    @FXML
    private void setAllFieldEnableOnClick() {
        employeeFName.setDisable(false);
        employeeLName.setDisable(false);
        //employeeId.setDisable(false);
        employeeEmailId.setDisable(false);
        employeePassword.setDisable(false);
        employeeUserName.setDisable(false);
        employeeCity.setDisable(false);
        employeeDeptBox.setDisable(false);
        employeeManagerBox.setDisable(false);
    }

    @FXML
    private void setAllFieldClearOnClick() {
        employeeFName.clear();
        employeeLName.clear();
        employeeId.clear();
        employeeEmailId.clear();
        employeePassword.clear();
        employeeUserName.clear();
        employeeCity.clear();

    }

    @FXML
    private void employeeCancelButtonOnClick(Event event) {
        //setAllFieldDisableOnClick();
        setAllFieldClearOnClick();
        employeeLabel.setText("");

    }

    @FXML
    private void backToManagerButtonOnClick(Event event) {
        try {
            FXMLLoader fxload = new FXMLLoader();
            fxload.setLocation(getClass().getResource("/views/ManagerLogin.fxml"));
            fxload.load();
            Parent parent = fxload.getRoot();
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("Manager Panel");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean checkFieldsEmpty() {
        if (employeeFName.getText().equals("") || employeeLName.getText().equals("") || employeeEmailId.getText().equals("")
                || employeePassword.getText().equals("") || employeeUserName.getText().equals("") || employeeCity.getText().equals("") ||
                employeeDeptBox.getValue().toString().trim().equals(null)|| employeeManagerBox.getValue().toString().trim().equals(null)) {
            //save.setDisable(true);
            employeeLabel.setTextFill(Color.web("red"));
            employeeLabel.setText("Please enter all values!");

            return true;
        } else {
            return false;
        }
    }
    
    private static int count;
    @FXML
    private void saveEmployeeButtonOnClick(Event event) {
        setAllFieldEnableOnClick();
        try {
//            System.out.println("dept:"+employeeDeptBox.getValue().toString().trim());
            connection = conn.connect();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from manager");
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            employeeId.setText(String.valueOf(count + 1));
            if (checkFieldsEmpty()) {

                return;
            } else {
                //save.setDisable(false);
                String sqlQuery = "insert into employee values (" + employeeId.getText() + " , '" + employeeFName.getText() + "','" + employeeLName.getText() + "','" + employeeUserName.getText() + "','" + employeePassword.getText() + "','" + employeeEmailId.getText() + "','" + employeeCity.getText() + "','" + employeeDeptBox.getValue().toString().trim() + "','" +employeeManagerBox.getValue().toString().trim() + "')";
                //System.out.println(sqlQuery);
                statement.executeUpdate(sqlQuery);
                employeeLabel.setTextFill(Color.web("green"));
                employeeLabel.setText("Employee Saved to Database");
                setAllFieldClearOnClick();
                setAllFieldDisableOnClick();
                refreshEmployeeButtonOnClick(event);
            }
            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            employeeLabel.setText("Employee Not Saved to Database");
            System.out.println("Values are not inserted in employee table after save button");
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

    public void set() {
        try {
            //System.out.println("manage emp:"+sessionUser);
            employeeDeptBox.getItems().add("Inventory");
            employeeDeptBox.getItems().add("Production");
            employeeDeptBox.getItems().add("Supply");
            employeeDeptBox.getItems().add("Marketing");

            connection = conn.connect();
            statement = connection.createStatement();
            String sql = "Select managerFirstName,managerLastName from manager;";
            rslt = statement.executeQuery(sql);
            while (rslt.next()) {
                String fullname = rslt.getString("managerFirstName") + " " + rslt.getString("managerLastName");
                employeeManagerBox.getItems().add(fullname);

            }
            connection.close();
            statement.close();
            rslt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ManageEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void refreshEmployeeButtonOnClick(Event event) {
        try {
            buildData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean ifRowSelected() {
        if (employeeDetailsTable.getSelectionModel().getSelectedItems().size() == 0) {
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
            TablePosition pos = employeeDetailsTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            EmployeePOJO item = employeeDetailsTable.getItems().get(row);
            int employeeID = item.getEmployeeID();
            //System.out.println("admin id: " + adminID);
            connection = conn.connect();
            statement = connection.createStatement();

            String sql = "Select * from employee where employeeId=" + employeeID + ";";
            //String sql = "Select * from admin where adminId=1;";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                employeeFName.setText(rs.getString("employeeFirstName"));
                employeeLName.setText(rs.getString("employeeLastName"));
                employeeId.setText(String.valueOf(rs.getInt("employeeId")));
                //addAdmintfLastName.setText(rs.getString("adminLastName"));
                employeeEmailId.setText(rs.getString("employeeEmailId"));
                employeePassword.setText(rs.getString("employeePassword"));
                employeeUserName.setText(rs.getString("employeeUserName"));
                employeeCity.setText(rs.getString("employeeCity"));
                employeeDeptBox.setValue(rs.getString("employeeDEpartment"));
                employeeManagerBox.setValue(rs.getString("employeeManager"));
            }
            connection.close();
            statement.close();

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editEmployeeButtonOnClick(Event event) {

        try {

            if (ifRowSelected()) {
                getRowDetails();
                setAllFieldEnableOnClick();
                saveEmployeeButton.setDisable(true);
                saveChangesButton.setDisable(false);
            }

        } catch (Exception ex) {
            Logger.getLogger(AddAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteEmployeeButtonOnClick(Event event) {
        if (ifRowSelected()) {
            try {
                TablePosition pos = employeeDetailsTable.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                EmployeePOJO item = employeeDetailsTable.getItems().get(row);
                int employeeID = item.getEmployeeID();
                //System.out.println("admin id: " + adminID);
                connection = conn.connect();
                statement = connection.createStatement();
                
                String sql = "delete from employee where employeeId=" + employeeID + ";";
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
            saveEmployeeButton.setDisable(true);
        }

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        empidDetail.setCellValueFactory(new PropertyValueFactory<EmployeePOJO, Integer>("employeeID"));
        fnameDetail.setCellValueFactory(new PropertyValueFactory<EmployeePOJO, String>("FName"));
        lnameDetail.setCellValueFactory(new PropertyValueFactory<EmployeePOJO, String>("LName"));
        deptDetail.setCellValueFactory(new PropertyValueFactory<EmployeePOJO, String>("department"));
        managerDetail.setCellValueFactory(new PropertyValueFactory<EmployeePOJO, String>("manager"));
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

                statement.executeUpdate("update employee set employeeFirstName ='" + employeeFName.getText() + "',employeeLastName='" + employeeLName.getText() + "',employeeUserName ='" + employeeUserName.getText() + "',employeePassword ='" + employeePassword.getText() + "',employeeEmailId ='" + employeeEmailId.getText() + "',employeeCity='" + employeeCity.getText() +"',employeeDepartment='" + employeeDeptBox.getValue() +"',employeeManager='" + employeeManagerBox.getValue() + "' where employeeId=" + employeeId.getText() + ";");
                //System.out.println("Your Details have been updated successfully!");
                employeeLabel.setTextFill(Color.web("green"));
                employeeLabel.setText("Details updated successfully!");
                setAllFieldDisableOnClick();
                setAllFieldClearOnClick();
                saveEmployeeButton.setDisable(false);
                refreshEmployeeButtonOnClick(event);
                saveChangesButton.setDisable(true);
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    
    private ObservableList<EmployeePOJO> data;

    public void buildData() {
        try {
            data = FXCollections.observableArrayList();
            connection = conn.connect();
            statement = connection.createStatement();

            String SQL = "Select * from employee;";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                EmployeePOJO cm = new EmployeePOJO();
                //System.out.println("rs id"+rs.getInt("adminId"));
                cm.employeeID.set(rs.getInt("employeeId"));
                cm.FName.set(rs.getString("employeeFirstName"));
                cm.LName.set(rs.getString("employeeLastName"));
                cm.department.set(rs.getString("employeeDepartment"));
                cm.manager.set(rs.getString("employeeManager"));
                data.add(cm);
            }

            employeeDetailsTable.setItems(data);
            connection.close();
            statement.close();

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}
