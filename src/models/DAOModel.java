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

public class DAOModel {

    Connector connector = new Connector();
    Connection conn = connector.connect();
    private Statement statement = null;

    public void createAdminTable() {
        try {

            Statement st = conn.createStatement();
            String sql = "CREATE TABLE admin (adminId Numeric not null, adminFirstName VARCHAR(255), adminLastName VARCHAR(255),adminUserName VARCHAR(255), adminPassword VARCHAR(255),adminEmailId VARCHAR(255),adminCity VARCHAR(255), PRIMARY KEY(adminId))";
            st.executeUpdate(sql);
            System.out.println("Created table in given database...");
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
