/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Manager_POJOTable {
    public  SimpleIntegerProperty mngID = new SimpleIntegerProperty();
	   public  SimpleStringProperty FName= new SimpleStringProperty();
	   public  SimpleStringProperty LName= new SimpleStringProperty();
	   public  SimpleStringProperty department= new SimpleStringProperty();
           
    public Manager_POJOTable() {
    }

    public int getManagerID() {
        return mngID.getValue();
    }

    public String getFName() {
        return FName.getValue();
    }

    public String getLName() {
        return LName.getValue();
    }

    public String getDepartment() {
        return department.getValue();
    }

              
           
}
