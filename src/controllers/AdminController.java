package controllers;


import models.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Aakash Tyagi
 * Date : 20 April 2017
 * @param args the command line arguments
 * @throws InterruptedException 
 */

public class AdminController {
	  public static String sessionUser = null;
	 @FXML
	    private TextField AdmintfFirstName;
	 @FXML
	    private TextField AdmintfLastName;
	@FXML
	    private TextField AdmintfID;
	 @FXML
	    private TextField AdmintfEmailID;    
    @FXML
    private PasswordField AdminpfPassword;
    @FXML
    private TextField AdmintfUserName;
    @FXML
    private TextField AdmintfCity;
    
    private void setAllFieldDisableOnClick(){
    	AdmintfFirstName.setDisable(true);
    	AdmintfLastName.setDisable(true);
    	AdmintfID.setDisable(true);
    	AdmintfEmailID.setDisable(true);
    	AdminpfPassword.setDisable(true);
    	AdmintfUserName.setDisable(true);
    	AdmintfCity.setDisable(true);}
    
    private void setAllFieldEnableOnClick(){
    	AdmintfFirstName.setDisable(false);
    	AdmintfLastName.setDisable(false);
    	AdmintfID.setDisable(false);
    	AdmintfEmailID.setDisable(false);
    	AdminpfPassword.setDisable(false);
    	AdmintfUserName.setDisable(false);
    	AdmintfCity.setDisable(false);
       
    }
    
    private void setAllFieldClearOnClick(){
    	AdmintfFirstName.clear();
    	AdmintfLastName.clear();
    	AdmintfID.clear();
    	AdmintfEmailID.clear();
    	AdminpfPassword.clear();
    	AdmintfUserName.clear();
    	AdmintfCity.clear();
        
    }
    static String ID;
    
    public void getAdminUserName(String ID){
    	ID= AdmintfUserName.getText();
    	
    	setAdminUserName(ID);
    }
    
    public void setAdminUserName(String ID){
        this.ID = ID;
    }
    
    private  Connector conn = new Connector();
    private Connection connection;
	private Statement statement;
	private ResultSet rslt;
    @FXML
    private void setAdminEditProfileClick(Event event){
    	setAllFieldEnableOnClick();
        String sqlQuery = "select * FROM admin where adminUserName ='"+sessionUser+"'; ";
        try {
            connection = conn.connect();
            statement = connection.createStatement();
            rslt = statement.executeQuery(sqlQuery);
            while(rslt.next()) {
            	AdmintfFirstName.setText(rslt.getString("adminFirstname"));
            	AdmintfLastName.setText(rslt.getString("adminLastname"));
            	AdmintfID.setText(rslt.getString("adminId"));
            	AdmintfEmailID.setText(rslt.getString("adminEmailId"));
            	AdminpfPassword.setText(rslt.getString("adminPassword"));
            	AdmintfUserName.setText(rslt.getString("adminUserName"));
            	AdmintfCity.setText(rslt.getString("adminCity"));
            }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void setAdminUpdateProfileClick(Event event){
        
    }

    @FXML
    private void setAdminCancelButtonClick(Event event){
    	setAllFieldDisableOnClick();
        setAllFieldClearOnClick();
    }

}