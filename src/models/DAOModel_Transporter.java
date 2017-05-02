/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
/**
 * @author Aakash Tyagi
 * Date : 28 April 2017
 */
import models.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAOModel_Transporter {

    Connector connector = new Connector();
    Connection conn = connector.connect();
    private Statement statement = null;

    public void createTransporterTable() {
        try {

            Statement st = conn.createStatement();
            String sql = "CREATE TABLE transporter (transpoterId int NOT NULL AUTO_INCREMENT, transporterFirstName VARCHAR(255), transporterLastName VARCHAR(255),transporterUserName VARCHAR(255), transporterPassword VARCHAR(255),transporterEmailId VARCHAR(255),transporterTruckNumber VARCHAR(255),transporterLocation VARCHAR(255), PRIMARY KEY(transpoterId))";
           // String sqlManager = "CREATE TABLE manager (managerId int NOT NULL AUTO_INCREMENT, managerFirstName VARCHAR(255), managerLastName VARCHAR(255),managerUserName VARCHAR(255), managerPassword VARCHAR(255),managerEmailId VARCHAR(255),managerCity VARCHAR(255),managerDepartment VARCHAR(255), PRIMARY KEY(managerId))";
           // String sqlEmployee = "CREATE TABLE employee (employeeId int NOT NULL AUTO_INCREMENT, employeeFirstName VARCHAR(255), employeeLastName VARCHAR(255),employeeUserName VARCHAR(255), employeePassword VARCHAR(255),employeeEmailId VARCHAR(255),employeeCity VARCHAR(255),employeeDepartment VARCHAR(255),employeeManager VARCHAR(255), PRIMARY KEY(employeeId))";
            st.executeUpdate(sql);
            //st.executeUpdate(sqlManager);
            //st.executeUpdate(sqlEmployee);
            System.out.println("Created transporter table in given database...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//		System.out.println("Table Creation in Progress");
//		DAOModel dm= new DAOModel();
//		dm.createAdminTable();
//	}

  /*  public void inserts(BankRecords[] records) {

        try {
            String sql2 = null;
            statement = conn.createStatement();
            for (int i = 0; i < records.length; i++) {
                sql2 = "INSERT INTO a_tyag_tab (id,income,pep)" + "VALUES('" + records[i].getID() + "','" + records[i].getIncome() + "','" + records[i].getPep() + "')";
//       statement.setString(1, records[i].getID().toString());
//       statement.setFloat(2, (float) records[i].getIncome());
//       statement.setString(3, (String) records[i].getPep().toString());
                statement.executeUpdate(sql2);
            }
            System.out.println("Inserted records into the table...");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from a_tyag_tab order by pep DESC");

            return rs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
