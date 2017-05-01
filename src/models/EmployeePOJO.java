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
public class EmployeePOJO {
    public  SimpleIntegerProperty employeeID = new SimpleIntegerProperty();
	   public  SimpleStringProperty FName= new SimpleStringProperty();
	   public  SimpleStringProperty LName= new SimpleStringProperty();
	   public  SimpleStringProperty department= new SimpleStringProperty();
           public  SimpleStringProperty manager= new SimpleStringProperty();

    public EmployeePOJO() {
    }

    public Integer getEmployeeID() {
        return employeeID.getValue();
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

    public String getManager() {
        return manager.getValue();
    }
           
           
}
