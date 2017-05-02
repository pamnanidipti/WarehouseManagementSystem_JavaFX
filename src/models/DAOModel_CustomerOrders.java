/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.Statement;

public class DAOModel_CustomerOrders {
    Connector connector = new Connector();
    Connection conn = connector.connect();
    private Statement statement = null;
    public void createOrdersTable() {
        try {

            Statement st = conn.createStatement();
            String sql = "CREATE TABLE orders (orderId int NOT NULL AUTO_INCREMENT, customerId int ,productId int,orderQuantity int NOT NULL,orderStatus VARCHAR(255),employeeName VARCHAR(255),transporterName VARCHAR(255),constraint fk_customer foreign key(customerId) REFERENCES customer(customerId), constraint fk_product FOREIGN KEY(productId) REFERENCES product(productId),PRIMARY KEY(orderId));";
           // String sqlManager = "CREATE TABLE manager (managerId int NOT NULL AUTO_INCREMENT, managerFirstName VARCHAR(255), managerLastName VARCHAR(255),managerUserName VARCHAR(255), managerPassword VARCHAR(255),managerEmailId VARCHAR(255),managerCity VARCHAR(255),managerDepartment VARCHAR(255), PRIMARY KEY(managerId))";
           // String sqlEmployee = "CREATE TABLE employee (employeeId int NOT NULL AUTO_INCREMENT, employeeFirstName VARCHAR(255), employeeLastName VARCHAR(255),employeeUserName VARCHAR(255), employeePassword VARCHAR(255),employeeEmailId VARCHAR(255),employeeCity VARCHAR(255),employeeDepartment VARCHAR(255),employeeManager VARCHAR(255), PRIMARY KEY(employeeId))";
            st.executeUpdate(sql);
            //st.executeUpdate(sqlManager);
            //st.executeUpdate(sqlEmployee);
            System.out.println("Created order table in given database...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
