package test;

import com.cafeteria.config.Conexion;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class test_conexion {

    @Test
    public void testConexion() {
        System.out.println("Iniciando prueba de conexión a la base de datos...");
        try (Connection conn = Conexion.getConection()) {
            assertNotNull(conn, "La conexión no debe ser nula.");
            assertFalse(conn.isClosed(), "La conexión no debe estar cerrada.");

            System.out.println("Conexión establecida correctamente.");
            DatabaseMetaData md = conn.getMetaData();
            System.out.println("Base de datos: " + md.getDatabaseProductName() + " " + md.getDatabaseProductVersion());
            System.out.println("URL: " + md.getURL());
            System.out.println("Usuario: " + md.getUserName());

        } catch (Exception e) {
            fail("Fallo en la conexión: " + e.getMessage());
        }
    }
}