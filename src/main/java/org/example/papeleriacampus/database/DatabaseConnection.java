package org.example.papeleriacampus.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/papeleria_campus";
    private static final String USER = "postgres";
    private static final String PASSWORD = "@rianaG2808";

    private DatabaseConnection() {

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
