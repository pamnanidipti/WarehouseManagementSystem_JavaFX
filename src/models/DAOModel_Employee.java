/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Pamnani
 */
public class DAOModel_Employee {
    Connector connector = new Connector();
    Connection conn = connector.connect();
    private Statement statement = null;
    
    public void createTable() {
        try {

            statement = conn.createStatement();
            
           String sqlEmployee = "CREATE TABLE employee (employeeId int NOT NULL AUTO_INCREMENT, employeeFirstName VARCHAR(255), employeeLastName VARCHAR(255),employeeUserName VARCHAR(255), employeePassword VARCHAR(255),employeeEmailId VARCHAR(255),employeeCity VARCHAR(255),employeeDepartment VARCHAR(255),employeeManager VARCHAR(255), PRIMARY KEY(employeeId))";
            
            statement.executeUpdate(sqlEmployee);
            System.out.println("Created employee table in given database...");
            conn.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
