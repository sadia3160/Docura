package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConSQL {

    public Connection con;

    public ConSQL() {

        String url = "jdbc:mysql://localhost:3306/docura_db";
        String user = "root", password = "MyLifeStore";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Driver Class registered
            con = DriverManager.getConnection(url, user, password); //Created Connection

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
5 steps to connect database
    1. Register the Driver class
    2. Create connection
    3. Create statement
    4. Execute queries
    5. Close connection
*/
