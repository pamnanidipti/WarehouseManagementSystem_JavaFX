/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Pamnani
 */
public class OrdersPOJO {
    public SimpleIntegerProperty orderId = new SimpleIntegerProperty();
    public SimpleIntegerProperty customerId = new SimpleIntegerProperty();
    public SimpleIntegerProperty productId = new SimpleIntegerProperty();
    public SimpleIntegerProperty orderQunatity = new SimpleIntegerProperty();
    public SimpleStringProperty orderStatus = new SimpleStringProperty();
    public SimpleStringProperty customerName = new SimpleStringProperty();
    public SimpleStringProperty productName = new SimpleStringProperty();
    //public SimpleStringProperty employeeName = new SimpleStringProperty();
    //public SimpleStringProperty transporterName = new SimpleStringProperty();

    public OrdersPOJO() {
    }

    public Integer getOrderId() {
        return orderId.getValue();
    }

    public Integer getCustomerId() {
        return customerId.getValue();
    }

    public Integer getProductId() {
        return productId.getValue();
    }

    public Integer getOrderQunatity() {

        return orderQunatity.getValue();
    }

    public String getOrderStatus() {
        return orderStatus.getValue();
    }

//    public String getEmployeeName() {
//        return employeeName.getValue();
//    }
//
//    public String getTransporterName() {
//        return transporterName.getValue();
//    }

    public String getCustomerName() {
        return customerName.getValue();
    }

    public String getProductName() {
        return productName.getValue();
    }

    
    
    
}
