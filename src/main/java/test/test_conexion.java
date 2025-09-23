package test;

import com.cafeteria.config.Conexion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class test_conexion {

    public static void main(String[] args) {
        System.out.println("Iniciando prueba de conexión UsuarioController la base de datos...");
        try (Connection conn = Conexion.getConection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexión establecida correctamente.");
                try {
                    DatabaseMetaData md = conn.getMetaData();
                    System.out.println("Base de datos: " + md.getDatabaseProductName() + " " + md.getDatabaseProductVersion());
                    System.out.println("URL: " + md.getURL());
                    System.out.println("Usuario: " + md.getUserName());
                } catch (SQLException ignore) {
                    // Si falla la lectura de metadatos, no es crítico para la prueba básica
                }
            } else {
                System.err.println("No se pudo establecer la conexión (objeto Connection nulo o cerrado).");
                System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("Fallo en la conexión:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
