package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import java.sql.*;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/testuser")
    public String basic(@RequestParam(value = "myName", defaultValue = "World") String name) {

        return String.format(mysql_query("jdbc:mysql://users-e6156.cexqeqvqreq2.us-east-1.rds.amazonaws.com:3306/UserData?autoReconnect=true&useSSL=false","root","dbuserdbuser","SELECT * FROM  UserData.users"), name);
    }

    @RequestMapping("/testuser/{someID}")
    public @ResponseBody String getID(@PathVariable(value="someID") String id, String someAttr) {

        return String.format(mysql_query("jdbc:mysql://users-e6156.cexqeqvqreq2.us-east-1.rds.amazonaws.com:3306/UserData?autoReconnect=true&useSSL=false","root","dbuserdbuser","SELECT * FROM UserData.users WHERE first_name ='"+id+"'" ), id);
    }





    public String mysql_query(String  DB_URL, String USER, String PASS,String QUERY) {

        System.out.println(QUERY);
        String finalresult  ="";
        String result = null;
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);


        ) {
            // Extract data from result set

            while (rs.next()) {
                // Retrieve by column name

                result = ("email: " + rs.getString("email"));
                result = result + " " + ("User_Name: " + rs.getString("User_Name"));
                result = result + " " + ("First_Name: " + rs.getString("first_name"));
                result = result + " " + ("Last_Name: " + rs.getString("last_name"));
                System.out.println(result);
                finalresult=finalresult+result;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finalresult;
    }

    }


