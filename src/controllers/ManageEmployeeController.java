/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.AdminPOJOTable;
import models.EmployeePOJO;

/**
 *
 * @author Pamnani
 */
public class ManageEmployeeController {
    @FXML
    private TextField employeeFname;
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
    private Button backToManagerButton,logout,viewEmployeeButton,editEmployeeButton,deleteEmployeeButton,refreshEmployeeButton,
            employeeCancelButton,saveChangesButton,saveEmployeeButton;
    
    @FXML
    private TableView<EmployeePOJO> employeeDetailsTable;
    
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
}
