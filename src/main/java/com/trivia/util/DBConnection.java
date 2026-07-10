package com.trivia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Maneja la conexión JDBC a MySQL.
 * Lee host/puerto/usuario/clave/nombre de BD desde variables de entorno
 * (útil para desplegar en Render/Railway) y usa valores por defecto
 * para desarrollo local (NetBeans + XAMPP/WAMP/MySQL local).
 */
public class DBConnection {

    private static final String DB_HOST = getEnvOrDefault("DB_HOST", "localhost");
    private static final String DB_PORT = getEnvOrDefault("DB_PORT", "3306");
    private static final String DB_NAME = getEnvOrDefault("DB_NAME", "trivia_db");
    private static final String DB_USER = getEnvOrDefault("DB_USER", "root");
    private static final String DB_PASSWORD = getEnvOrDefault("DB_PASSWORD", "");

    private static final String URL =
            "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
            + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver de MySQL (mysql-connector-j)", e);
        }
    }

    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value == null || value.isEmpty()) ? defaultValue : value;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }
}
