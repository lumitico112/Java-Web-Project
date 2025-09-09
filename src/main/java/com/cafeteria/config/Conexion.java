package com.cafeteria.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private Conexion() {
        // Evitar instanciación
    }

    public static Connection getConection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db_cafeteria?serverTimezone=America/Lima";
            String user = "root";
            String password = "DataBase_1.";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión correcta");
        } catch (Exception e) {
            System.out.println("error de conexion");
            e.printStackTrace();
        }
        return conn;
    }
}
