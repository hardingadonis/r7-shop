package io.hardingadonis.r7.services;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JDBC {

    private static String DB_URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = JDBC.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                DB_URL = "jdbc:mysql://localhost:3306/r7";
                USER = "root";
                PASSWORD = "";
            } else {
                prop.load(input);

                DB_URL = "jdbc:mysql://" + prop.getProperty("config.jdbc.url") + ":3306/" + prop.getProperty("config.jdbc.database_name");
                USER = prop.getProperty("config.jdbc.user");
                PASSWORD = prop.getProperty("config.jdbc.password");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            System.out.println("Connect MySQL successfully!");
        } catch (SQLException ex) {
            System.err.println("Connect MySQL failure!");
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();

                System.out.println("Close MySQL successfully!");
            }
        } catch (SQLException ex) {
            System.err.println("Close MySQL failure!");
        }
    }
}
