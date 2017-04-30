package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import models.AdminPOJOTable;
import models.Connector;

/**
 * @author Aakash Tyagi Date : 20 April 2017
 * @param args the command line arguments
 * @throws InterruptedException
 */
public class AddAdminController implements Initializable {

    public static String sessionUser = null;
    private static int count;

    @FXML
    private TextField addAdmintfFirstName;
    @FXML
    private TextField addAdmintfLastName;
    @FXML
    private TextField addAdmintfID;
    @FXML
    private TextField addAdmintfEmailID;
    @FXML
    private PasswordField addAdminpfPassword;
    @FXML
    private TextField addAdmintfUserName;
    @FXML
    private TextField addAdmintfCity;

    @FXML
    private TableView<AdminPOJOTable> adminDetailsTableView;

    @FXML
    TableColumn<AdminPOJOTable, Integer> admindetailsId;
    @FXML
    TableColumn<AdminPOJOTable, String> admindetailsFname;
    @FXML
    TableColumn<AdminPOJOTable, String> admindetailsLname;
    @FXML
    TableColumn<AdminPOJOTable, String> admindetailscity;

    @FXML
    private Button save;

    private void setAllFieldDisableOnClick() {
        addAdmintfFirstName.setDisable(true);
        addAdmintfLastName.setDisable(true);
        addAdmintfID.setDisable(true);
        addAdmintfEmailID.setDisable(true);
        addAdminpfPassword.setDisable(true);
        addAdmintfUserName.setDisable(true);
        addAdmintfCity.setDisable(true);
    }

    private void setAllFieldEnableOnClick() {
        addAdmintfFirstName.setDisable(false);
        addAdmintfLastName.setDisable(false);
        //addAdmintfID.setDisable(false);
        addAdmintfEmailID.setDisable(false);
        addAdminpfPassword.setDisable(false);
        addAdmintfUserName.setDisable(false);
        addAdmintfCity.setDisable(false);
    }

    private void setAllFieldClearOnClick() {
        addAdmintfFirstName.clear();
        addAdmintfLastName.clear();
        addAdmintfID.clear();
        addAdmintfEmailID.clear();
        addAdminpfPassword.clear();
        addAdmintfUserName.clear();
        addAdmintfCity.clear();

    }

    private Connector conn = new Connector();
    private Connection connection;
    private Statement statement;
    //private ResultSet rslt;
    @FXML
    private Label addAdminSaveLabel;

    @FXML
    private void setAddAdminSaveClick(Event event) {
        setAllFieldEnableOnClick();
        try {

            connection = conn.connect();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from admin");
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            addAdmintfID.setText(String.valueOf(count + 1));
            if (addAdmintfFirstName.getText().equals("") || addAdmintfLastName.getText().equals("") || addAdmintfEmailID.getText().equals("")
                    || addAdminpfPassword.getText().equals("") || addAdmintfUserName.getText().equals("") || addAdmintfCity.getText().equals("")) {
                //save.setDisable(true);
                addAdminSaveLabel.setTextFill(Color.web("red"));
                addAdminSaveLabel.setText("Please enter all values!");

                return;
            } else {
                //save.setDisable(false);
                String sqlQuery = "insert into admin (adminId,adminFirstName,adminLastName,adminUserName,adminPassword,adminEmailId,adminCity )" + "values (" + addAdmintfID.getText() + " , '" + addAdmintfFirstName.getText() + "','" + addAdmintfLastName.getText() + "','" + addAdmintfUserName.getText() + "','" + addAdminpfPassword.getText() + "','" + addAdmintfEmailID.getText() + "','" + addAdmintfCity.getText() + "')";
                //System.out.println(sqlQuery);
                statement.executeUpdate(sqlQuery);
                addAdminSaveLabel.setTextFill(Color.web("green"));
                addAdminSaveLabel.setText("Admin Saved to Database");
                setAllFieldClearOnClick();
                setAllFieldDisableOnClick();
            }
            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            addAdminSaveLabel.setText("Admin Not Saved to Database");
            System.out.println("Values are not inserted in admin table after save button");
            e.printStackTrace();
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
    private void setaddAdminCancelButton(Event event) {
        setAllFieldDisableOnClick();
        setAllFieldClearOnClick();
        addAdminSaveLabel.setText("");
    }
    /*@FXML
     private void setbuttonClick(Event event) throws IOException {

     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("/admin/Course.fxml"));
     loader.load();
     Parent p = loader.getRoot();
     Stage stage = new Stage();
     stage.setScene(new Scene(p));
     stage.setTitle("Course Panel");
     stage.show();
     }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //assert adminDetailsTableView != null : "fx:id=\"tableview\" was not injected: check your FXML file 'UserMaster.fxml'.";
        admindetailsId.setCellValueFactory(new PropertyValueFactory<AdminPOJOTable, Integer>("adminID"));
        admindetailsFname.setCellValueFactory(new PropertyValueFactory<AdminPOJOTable, String>("FName"));
        admindetailsLname.setCellValueFactory(new PropertyValueFactory<AdminPOJOTable, String>("LName"));
        admindetailscity.setCellValueFactory(new PropertyValueFactory<AdminPOJOTable, String>("city"));
         //Connector connector = new Connector();

        try {
            buildData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // private ObservableList<AdminPOJOTable> data = FXCollections.observableArrayList();
    private ObservableList<AdminPOJOTable> data;

    //private ArrayList<AdminPOJOTable> data = new ArrayList<AdminPOJOTable>();

    public void buildData() {
        //data = FXCollections.observableArrayList();

        try {
            data = FXCollections.observableArrayList();
            connection = conn.connect();
            statement = connection.createStatement();
                        
            String SQL = "Select * from admin;";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                AdminPOJOTable cm = new AdminPOJOTable();
                //System.out.println("rs id"+rs.getInt("adminId"));
                cm.adminID.set(rs.getInt("adminId"));
                cm.FName.set(rs.getString("adminFirstName"));
                cm.LName.set(rs.getString("adminLastName"));
                cm.city.set(rs.getString("adminCity"));
                data.add(cm);
            }

            adminDetailsTableView.setItems(data);
            connection.close();
            statement.close();
            
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}
