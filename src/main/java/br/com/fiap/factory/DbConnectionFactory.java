package br.com.fiap.factory;

import java.sql.*;

public class DbConnectionFactory {

    public static Connection createConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/orcl",
                System.getenv("DB_USER"),
                System.getenv("DB_PASS")
        );

        return connection;

    }
}
