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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Connector;


/**
 * @author Aakash Tyagi
 * Date : 20 April 2017
 * @param args the command line arguments
 * @throws InterruptedException 
 */

public class AddAdminController {
	  public static String sessionUser = null;
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
    private Button save;
    private void setAllFieldDisableOnClick(){
    	addAdmintfFirstName.setDisable(true);
    	addAdmintfLastName.setDisable(true);
    	addAdmintfID.setDisable(true);
    	addAdmintfEmailID.setDisable(true);
    	addAdminpfPassword.setDisable(true);
    	addAdmintfUserName.setDisable(true);
    	addAdmintfCity.setDisable(true);
    }
    
    private void setAllFieldEnableOnClick(){
    	addAdmintfFirstName.setDisable(false);
    	addAdmintfLastName.setDisable(false);
    	addAdmintfID.setDisable(false);
    	addAdmintfEmailID.setDisable(false);
    	addAdminpfPassword.setDisable(false);
    	addAdmintfUserName.setDisable(false);
    	addAdmintfCity.setDisable(false);
    }
    
    private void setAllFieldClearOnClick(){
    	addAdmintfFirstName.clear();
    	addAdmintfLastName.clear();
    	addAdmintfID.clear();
    	addAdmintfEmailID.clear();
    	addAdminpfPassword.clear();
    	addAdmintfUserName.clear();
    	addAdmintfCity.clear();
        
    }
   
    
    private  Connector conn = new Connector();
    private Connection connection;
	private Statement statement;
	//private ResultSet rslt;
	@FXML
	private Label addAdminSaveLabel;
    @FXML
    private void setAddAdminSaveClick(Event event){
    	setAllFieldEnableOnClick();
    	try {
    		  		
    		connection=conn.connect();
    		statement = connection.createStatement();
        String sqlQuery = "insert into admin (adminId,adminFirstName,adminLastName,adminUserName,adminPassword,adminEmailId,adminCity )"+"values ("+addAdmintfID.getText() +" , '"+addAdmintfFirstName.getText()+"','"+addAdmintfLastName.getText()+"','"+addAdmintfUserName.getText()+"','"+addAdminpfPassword.getText()+"','"+addAdmintfEmailID.getText()+"','"+addAdmintfCity.getText()+"')";
        System.out.println(sqlQuery);
        statement.executeUpdate(sqlQuery);
        addAdminSaveLabel.setText("Admin Saved to Database");
            
            }catch (SQLException e) {
            	addAdminSaveLabel.setText("Admin Not Saved to Database");
            	System.out.println("Values are not inserted in admin table after save button");
                e.printStackTrace();}
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
    private void setaddAdminCancelButton(Event event){
    	setAllFieldDisableOnClick();
        setAllFieldClearOnClick();
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
   
}