/**
 * @author Aakash Tyagi
 * Date : 20 March 2017
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

    /*String url = "jdbc:mysql://www.papademas.net:3306/510labs?autoReconnect=true&useSSL=false";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "db510";
    String password = "510";*/

    String url = "jdbc:mysql://localhost:3306/warehouse";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "root";

    public Connection connect() {
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected database successfully...");
            return conn;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        }
        return null;
    }
}