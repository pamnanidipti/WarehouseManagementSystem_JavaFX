/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author Pamnani
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import models.DAOModel;
import models.DAOModel_Customer;


public class LoginMain extends Application{
 @Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			 Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			 primaryStage.setTitle("Login Page - Warehouse Management System");
			Scene scene = new Scene(root,650,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		launch(args);
		          DAOModel dao = new DAOModel();
                        //  dao.createAdminTable();
		          DAOModel_Customer doaCust = new DAOModel_Customer();
		         // doaCust.createCustomerTable();
	}
}
