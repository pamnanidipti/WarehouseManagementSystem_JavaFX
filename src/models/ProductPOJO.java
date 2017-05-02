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
public class ProductPOJO {

    public SimpleIntegerProperty productID = new SimpleIntegerProperty();
    public SimpleIntegerProperty productQuantity = new SimpleIntegerProperty();
    public SimpleStringProperty productName = new SimpleStringProperty();
    public SimpleStringProperty productStatus = new SimpleStringProperty();
    

    public ProductPOJO() {
    }

    public Integer getProductID() {
        return productID.getValue();
    }

    public Integer getProductQuantity() {
        return productQuantity.getValue();
    }

    public String getProductStatus() {
        return productStatus.getValue();
    }

    public String getProductName() {
        return productName.getValue();
    }

   

}
