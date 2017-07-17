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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Connector;
import models.Customer_AvailablePOJOTable;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Pamnani
 */
public class Employee_WarehouseInventoryController implements Initializable {

    @FXML
    private Button refreshButton;

    @FXML
    private Button viewSelect;

    @FXML
    private Label inventoryLabel;
    @FXML
    private Button logout;

    @FXML
    private Button BacktoProfile;

    @FXML
    private TextField productId;
    @FXML
    private TextField productName;
    @FXML
    private TextField productStatus;
    @FXML
    private TextField productDescription;
    @FXML
    private Spinner<Integer> productQuantity;

    @FXML
    private Button cancelButton, saveChangesButton;

    @FXML
    private TableView<Customer_AvailablePOJOTable> availableProductstableview;

    @FXML
    TableColumn<Customer_AvailablePOJOTable, Integer> ProductIdTableColumn;
    @FXML
    TableColumn<Customer_AvailablePOJOTable, Integer> AvailableQuantityTableColumn;

    @FXML
    TableColumn<Customer_AvailablePOJOTable, String> ProductNameTableColumn;
    @FXML
    TableColumn<Customer_AvailablePOJOTable, String> ProductStatusTableColumn;
    @FXML
    TableColumn<Customer_AvailablePOJOTable, String> ProductDescriptionTableColumn;

    private Connector conn = new Connector();
    private Connection connection;
    private Statement statement;
    private ResultSet rslt;

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
    private void BacktoProfileOnClick(Event event) {
        try {
            FXMLLoader fxload = new FXMLLoader();
            fxload.setLocation(getClass().getResource("/views/EmployeeLogin.fxml"));
            fxload.load();
            Parent parent = fxload.getRoot();
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.setTitle("Employee Panel");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean ifRowSelected() {
        if (availableProductstableview.getSelectionModel().getSelectedItems().size() == 0) {
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

    @FXML
    private void viewSelectOnClick(Event event) {
        if (ifRowSelected()) {
            getRowDetails();

            inventoryLabel.setText("");
            productQuantity.setDisable(false);
            saveChangesButton.setDisable(false);
            cancelButton.setDisable(false);
        }

    }

    public void getRowDetails() {
        try {
            TablePosition pos = availableProductstableview.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            Customer_AvailablePOJOTable item = availableProductstableview.getItems().get(row);
            int productID = item.getProductID();
            //System.out.println("admin id: " + adminID);
            connection = conn.connect();
            statement = connection.createStatement();
            String sql = "Select * from product where productId=" + productID + ";";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                productId.setText(String.valueOf(rs.getInt("productId")));
                productDescription.setText(rs.getString("productDescription"));
                productName.setText(rs.getString("productName"));
                productStatus.setText(rs.getString("productStatus"));
                //productQuantity = rs.getInt("productQuantity");
            }
            connection.close();
            statement.close();

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AddAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setAllFieldClearOnClick() {
        productId.clear();
        productName.clear();
        productStatus.clear();
        productDescription.clear();

    }

    @FXML
    private void cancelButtonOnClick(Event event) {
        //setAllFieldDisableOnClick();
        setAllFieldClearOnClick();
        inventoryLabel.setText("");
        cancelButton.setDisable(true);
        saveChangesButton.setDisable(true);
    }

    @FXML
    private void saveChangesButtonOnClick(Event event) {
        try {
            connection = conn.connect();
            statement = connection.createStatement();
            if (productQuantity.getValue() > 0) {
                productStatus.setText("Available");
            } else {
                productStatus.setText("Out of Stock");
            }

            statement.executeUpdate("update product set productQuantity =" + productQuantity.getValue() + " where productId=" + productId.getText() + ";");

            inventoryLabel.setTextFill(Color.web("green"));
            inventoryLabel.setText("Inventory updated successfully!");
            setAllFieldClearOnClick();
            cancelButton.setDisable(true);
            saveChangesButton.setDisable(true);
            productQuantity.setDisable(true);
            refreshButtonOnClick(event);
            connection.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_WarehouseInventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ProductIdTableColumn.setCellValueFactory(new PropertyValueFactory<Customer_AvailablePOJOTable, Integer>("productID"));
        AvailableQuantityTableColumn.setCellValueFactory(new PropertyValueFactory<Customer_AvailablePOJOTable, Integer>("availQuant"));
        ProductNameTableColumn.setCellValueFactory(new PropertyValueFactory<Customer_AvailablePOJOTable, String>("proName"));
        ProductStatusTableColumn.setCellValueFactory(new PropertyValueFactory<Customer_AvailablePOJOTable, String>("ProStatus"));
        ProductDescriptionTableColumn.setCellValueFactory(new PropertyValueFactory<Customer_AvailablePOJOTable, String>("ProDesc"));
        try {
            buildData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Customer_AvailablePOJOTable> data;

    public void buildData() {
        try {
            data = FXCollections.observableArrayList();
            connection = conn.connect();
            statement = connection.createStatement();

            String SQL = "Select * from product";

            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                Customer_AvailablePOJOTable cm = new Customer_AvailablePOJOTable();
                //System.out.println("rs id"+rs.getInt("adminId"));
                cm.productID.set(rs.getInt("productId"));
                cm.availQuant.set(rs.getInt("productQuantity"));
                cm.proName.set(rs.getString("productName"));
                cm.ProStatus.set(rs.getString("productStatus"));
                cm.ProDesc.set(rs.getString("productDescription"));
                data.add(cm);
            }

            availableProductstableview.setItems(data);
            connection.close();
            statement.close();

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
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

}
