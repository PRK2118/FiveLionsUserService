package com.example.demo;
import java.sql.*;

public class FirstExample {

    static final String DB_URL = "jdbc:mysql://localhost:3306/e6156_project";
    static final String USER = "root";
    static final String PASS = "dbuserdbuser";
    static final String QUERY = "SELECT * FROM  e6156_project.users";

    public static void main(String[] args) {
        // Open a connection
        //DriverManager.registerDriver(driverObject);

           // Class.forName("com.mysql.jdbc.Driver");

        try(
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("ID: " + rs.getString("email"));
               // System.out.print(", Age: " + rs.getInt("Select_priv"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}