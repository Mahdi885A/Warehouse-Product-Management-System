package util;

import exception.DatabaseConfigException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "ZqX.7391";

    public static Connection getConnection (){
        try (Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            System.out.println("Connected...");
            return connection;

        }
        catch (SQLException e){
            throw new DatabaseConfigException("Database can not connected! "+e.getMessage() );
        }
    }

}

