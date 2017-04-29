
package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.scene.Node;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Connector;
import javafx.scene.Node;

/**
 * @author Aakash Tyagi Date : 20 April 2017
 * @param args the command line arguments
 * @throws InterruptedException
 */
public class AdminController {

    public static String sessionUser = null;
    public boolean isEdit;
    public static Node adminNode;
    //private Stage stage = new Stage();

    @FXML
    private TextField AdmintfFirstName;
    @FXML
    private TextField AdmintfLastName;
    @FXML
    private TextField AdmintfID;
    @FXML
    private TextField AdmintfEmailID;
    @FXML
    private Button updateProfileButton;
    @FXML
    private PasswordField AdminpfPassword;
    @FXML
    private TextField AdmintfUserName;
    @FXML
    private TextField AdmintfCity;
    @FXML
    private Label adminLabel;

    private void setAllFieldDisableOnClick() {
        AdmintfFirstName.setDisable(true);
        AdmintfLastName.setDisable(true);
        AdmintfID.setDisable(true);
        AdmintfEmailID.setDisable(true);
        AdminpfPassword.setDisable(true);
        AdmintfUserName.setDisable(true);
        AdmintfCity.setDisable(true);
        updateProfileButton.setDisable(true);
    }

    private void setAllFieldEnableOnClick() {
        AdmintfFirstName.setDisable(false);
        AdmintfLastName.setDisable(false);
        //AdmintfID.setDisable(false);
        AdmintfEmailID.setDisable(false);
        AdminpfPassword.setDisable(false);
        AdmintfUserName.setDisable(false);
        AdmintfCity.setDisable(false);
        updateProfileButton.setDisable(false);
    }

    private void setAllFieldClearOnClick() {
        AdmintfFirstName.clear();
        AdmintfLastName.clear();
        AdmintfID.clear();
        AdmintfEmailID.clear();
        AdminpfPassword.clear();
        AdmintfUserName.clear();
        AdmintfCity.clear();

    }
    static String ID;

    public void getAdminUserName(String ID) {
        ID = AdmintfUserName.getText();

        setAdminUserName(ID);
    }

    public void setAdminUserName(String ID) {
        this.ID = ID;
    }

    private Connector conn = new Connector();
    private Connection connection;
    private Statement statement;
    private ResultSet rslt;

    @FXML
    private void setAdminEditProfileClick(Event event) {
        setAllFieldEnableOnClick();
        isEdit = true;
        String sqlQuery = "select * FROM admin where adminUserName ='" + sessionUser + "'; ";
        try {

            connection = conn.connect();
            statement = connection.createStatement();
            rslt = statement.executeQuery(sqlQuery);
            while (rslt.next()) {
                AdmintfFirstName.setText(rslt.getString("adminFirstname"));
                AdmintfLastName.setText(rslt.getString("adminLastname"));
                AdmintfID.setText(rslt.getString("adminId"));
                AdmintfEmailID.setText(rslt.getString("adminEmailId"));
                AdminpfPassword.setText(rslt.getString("adminPassword"));
                AdmintfUserName.setText(rslt.getString("adminUserName"));
                AdmintfCity.setText(rslt.getString("adminCity"));
            }
            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void setAdminUpdateProfileClick(Event event) {

        try {
            if (isEdit) {
                connection = conn.connect();
                statement = connection.createStatement();
                
                if(AdmintfFirstName.getText().equals("") || AdmintfLastName.getText().equals("") || AdmintfEmailID.getText().equals("")
                        || AdminpfPassword.getText().equals("") || AdmintfUserName.getText().equals("") || AdmintfCity.getText().equals("")){
                    //save.setDisable(true);
                    adminLabel.setTextFill(Color.web("red"));
                    adminLabel.setText("Please enter all values!");
                    
                    return;
                }
               
                // System.out.println("Count: "+count);
                statement.executeUpdate("update admin set adminFirstName ='" + AdmintfFirstName.getText() + "',adminLastName='" + AdmintfLastName.getText() + "',adminUserName ='" + AdmintfUserName.getText() + "',adminPassword ='" + AdminpfPassword.getText() + "',adminEmailId ='" + AdmintfEmailID.getText() + "',adminCity='" + AdmintfCity.getText() + "' where adminId=" + AdmintfID.getText() + ";");
                //System.out.println("Your Details have been updated successfully!");
                adminLabel.setTextFill(Color.web("green"));
                    adminLabel.setText("Details updated successfully!");
                setAllFieldDisableOnClick();
                connection.close();
                statement.close();
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setAdminCancelButtonClick(Event event) {
        setAllFieldDisableOnClick();
        setAllFieldClearOnClick();
        adminLabel.setText("");
    }
    /*@FXML
     private void setAdminCoursePanelClick(Event event) throws IOException {

     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(getClass().getResource("/admin/Course.fxml"));
     loader.load();
     Parent p = loader.getRoot();
     Stage stage = new Stage();
     stage.setScene(new Scene(p));
     stage.setTitle("Course Panel");
     stage.show();
     }*/

    @FXML
    private void setLogoutButtonClick(Event event) throws IOException{
        
        FXMLLoader fxload = new FXMLLoader();
        fxload.setLocation(getClass().getResource("/application/Login.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Login Page - Warehouse Management System");
        stage.show();
        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Login.fxml"));
//                            Parent adminParent = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
//                            Scene adminScene = new Scene(adminParent);
//                            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                            //adminNode = (Node) event.getSource();
//                            
//                            adminStage.hide();
//                            adminStage.setScene(adminScene);
//                            adminStage.setTitle("Login Page - Warehouse Management System");
//                            adminStage.show();
    }
    
    @FXML
    private void setAdminAddAdminClick(Event event) throws IOException {
//        try{
//            replaceSceneContent("/views/AddAdmin.fxml");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        
        FXMLLoader fxload = new FXMLLoader();
        fxload.setLocation(getClass().getResource("/views/AddAdmin.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Add New Admin Screen");
        stage.show();
    }
    @FXML
    private void setAdminwarehouseEmployeepanel(ActionEvent event) throws IOException {
//        try{
//            replaceSceneContent("/views/Admin_warehouseEmployee.fxml");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        //((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxload = new FXMLLoader();
        fxload.setLocation(getClass().getResource("/views/Admin_warehouseEmployee.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        //System.out.println("employee parent: "+parent.toString());
        //adminNode.getScene().getWindow().hide();
         //((Stage)event.getSource()).close();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Add New Admin Screen");
        stage.show();
        
    }
    @FXML
    private void setAdminwarehouseManagerpanel(ActionEvent event) throws IOException {
//        try{
//            replaceSceneContent("/views/Admin_warehouseManager.fxml");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        //((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxload = new FXMLLoader();
        
        
        fxload.setLocation(getClass().getResource("/views/Admin_warehouseManager.fxml"));
        fxload.load();
        Parent parent = fxload.getRoot();
        //System.out.println("manager parent:"+parent.toString());
        //adminNode.getScene().getWindow().hide();
        //((Stage)event.getSource()).close();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        
        stage.setTitle("Add New Admin Screen");
        stage.show();
      //  ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
//    private Parent replaceSceneContent(String fxml) throws Exception {
//        Parent page = (Parent) FXMLLoader.load(getClass().getResource(fxml), null, new JavaFXBuilderFactory());
//        Scene scene = stage.getScene();
//        if (scene == null) {
//            scene = new Scene(page, 700, 550);
//            //scene.getStylesheets().add(LoginApp.class.getResource("demo.css").toExternalFo      
//                    stage.setScene(scene);
//        } else {
//            stage.getScene().setRoot(page);
//        }
//        stage.sizeToScene();
//        stage.show();
//        return page;
//    }
}
