package application;

import controllers.AdminController;
import controllers.ManagerController;
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
//import student.Student;
//import teacher.TeacherController;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Button;

/**
 * @author Aakash Tyagi Date : 20 April 2017
 * @param args the command line arguments
 * @throws InterruptedException
 */
public class LoginController {

    @FXML
    private TextField tfEmailID;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private ChoiceBox cbUser;
    @FXML
    private Button loginButton;

    private static String sessionUser = null;
    private static Node adminNode;
<<<<<<< HEAD
=======
    
>>>>>>> 8fe45dc172a02fb17d534fe103ca63a227f3f85e

    @FXML
    private void loginButtonClick(Event event) throws SQLException {
        if (isAllFieldFillup()) {
            String userName = tfEmailID.getText().trim();
            String password = pfPassword.getText();
            String userType = cbUser.getValue().toString().trim();
            //System.out.println(userType);
            switch (userType) {
                case "Admin":
                    if (isValidCredentials(userType, userName, password, "Email")) {
                        try {
                            sessionUser = userName;
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Admin.fxml"));
                            Parent adminParent = FXMLLoader.load(getClass().getResource("/views/Admin.fxml"));
                            Scene adminScene = new Scene(adminParent);
                            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            //adminNode = (Node) event.getSource();

                            adminStage.hide();
                            adminStage.setScene(adminScene);
                            adminStage.setTitle("Admin Panel");
                            adminStage.show();
                            AdminController controller = loader.<AdminController>getController();
                            //controller.adminNode = this.adminNode;
                            controller.sessionUser = this.sessionUser;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                break;
                case "Warehouse Employee":
                    userType="employee";
                    if (isValidCredentials(userType, userName, password, "Email")) {
                        try {
                            sessionUser = userName;
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Employee.fxml"));
                            Parent adminParent = FXMLLoader.load(getClass().getResource("/views/Employee.fxml"));
                            Scene adminScene = new Scene(adminParent);
                            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            //adminNode = (Node) event.getSource();

                            adminStage.hide();
                            adminStage.setScene(adminScene);
                            adminStage.setTitle("Employee Panel");
                            adminStage.show();
                            AdminController controller = loader.<AdminController>getController();
                            //controller.adminNode = this.adminNode;
                            controller.sessionUser = this.sessionUser;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                break;
                case "Warehouse Manager":
                    userType="manager";
                    if (isValidCredentials(userType, userName, password, "Email")) {
                        try {
                            sessionUser = userName;
<<<<<<< HEAD
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Manager.fxml"));
                            Parent adminParent = FXMLLoader.load(getClass().getResource("/views/Manager.fxml"));
=======
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ManagerLogin.fxml"));
                            Parent adminParent = FXMLLoader.load(getClass().getResource("/views/ManagerLogin.fxml"));
>>>>>>> 8fe45dc172a02fb17d534fe103ca63a227f3f85e
                            Scene adminScene = new Scene(adminParent);
                            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            //adminNode = (Node) event.getSource();

                            adminStage.hide();
                            adminStage.setScene(adminScene);
                            adminStage.setTitle("Manager Panel");
                            adminStage.show();
<<<<<<< HEAD
                            AdminController controller = loader.<AdminController>getController();
                            //controller.adminNode = this.adminNode;
                            controller.sessionUser = this.sessionUser;

=======
                            ManagerController controller = loader.<ManagerController>getController();
                            //controller.adminNode = this.adminNode;
                            System.out.println("LoginC"+sessionUser);
                            
                            controller.sessionUser = this.sessionUser;
                            //System.out.println("manager C:"+controller.sessionUser);
                            
>>>>>>> 8fe45dc172a02fb17d534fe103ca63a227f3f85e
                            
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                break;
<<<<<<< HEAD
                case "Customer":
                    userType="Customer";
                    if (isValidCredentials(userType, userName, password, "Email")) {
                        try {
                            sessionUser = userName;
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Customer.fxml"));
                            Parent adminParent = FXMLLoader.load(getClass().getResource("/views/Customer.fxml"));
                            Scene adminScene = new Scene(adminParent);
                            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            //adminNode = (Node) event.getSource();

                            adminStage.hide();
                            adminStage.setScene(adminScene);
                            adminStage.setTitle("Customer Panel");
                            adminStage.show();
                            AdminController controller = loader.<AdminController>getController();
                            //controller.adminNode = this.adminNode;
                            controller.sessionUser = this.sessionUser;

                            
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } 
            }
        }
    }
                   // break
                    /*  case "Guardian":
     System.out.println("I am now on Guardian");
     break;
     /*case "Student":
     if(isValidCredentials(userType,userName,password,"ID")){
     try {
     Student student = new Student();
     student.setStudentId(userName);
     Parent studentParent = FXMLLoader.load(getClass().getResource("/student/Student.fxml"));
     Scene studentScene = new Scene(studentParent);
     Stage studentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     studentStage.hide();
     studentStage.setScene(studentScene);
     studentStage.setTitle("Student Panel");
     studentStage.show();

     }
     catch (IOException e){
     e.printStackTrace();
     }
     }
     break;
     case "Teacher":
     if (isValidCredentials(userType,userName,password,"Email")){
     try {
     TeacherController teacherController = new TeacherController();
     teacherController.setEmail(userName);
     Parent teacherParent = FXMLLoader.load(getClass().getResource("/teacher/Teacher.fxml"));
     Scene teacherScene = new Scene(teacherParent);
     Stage teacherStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     teacherStage.hide();
     teacherStage.setScene(teacherScene);
     teacherStage.setTitle("Teacher Panel");
     teacherStage.show();

     } catch (IOException e) {
     e.printStackTrace();
     }

     }
     break;

     }

     }

     }
     */
=======
            }
        }
    }
      
>>>>>>> 8fe45dc172a02fb17d534fe103ca63a227f3f85e

    private boolean isValidCredentials(String userType, String userName, String password, String loginType) throws SQLException {
        boolean userPassOk = false;

        Connector conn = new Connector();
        Connection connection = conn.connect();
        Statement statement = connection.createStatement();
        /*ResultSet resultSet = statement.executeQuery("select * from "+userType.toLowerCase()+" where db"+userType
         +loginType+" = '"+userName+"' and db"+userType+"Password = '"+password+"';");*/
        ResultSet resultSet = statement.executeQuery("select * from " + userType.toLowerCase() + " where " + userType + "UserName = '" + userName + "' and " + userType + "Password = '" + password + "';");
        //ResultSet resultSet = statement.executeQuery("select * from admin where adminUserName = '"+userName+"' and adminPassword = '"+password+"';");

        while (resultSet.next()) {

            if (resultSet.getString(userType + "UserName") != null && resultSet.getString(userType + "Password") != null) {
                userPassOk = true;
            }

        }

        if (!userPassOk) {

            NotificationType notificationType = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Wrong Information");
            tray.setMessage("Incorrect email or password");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));

            tfEmailID.clear();
            pfPassword.clear();

            userPassOk = false;

        }

        return userPassOk;
    }

    private boolean isAllFieldFillup() {
        boolean fillup;
        if (tfEmailID.getText().trim().isEmpty() || pfPassword.getText().isEmpty()) {

            NotificationType notificationType = NotificationType.INFORMATION;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Attention!!!");
            tray.setMessage("Email or Password should not Empty.");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));

            fillup = false;
        } else {
            fillup = true;
        }
        return fillup;
    }
}
