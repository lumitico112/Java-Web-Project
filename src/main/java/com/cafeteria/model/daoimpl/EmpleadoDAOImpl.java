package com.cafeteria.model.daoimpl;

import com.cafeteria.config.Conexion;
import com.cafeteria.model.dao.EmpleadoDAO;
import com.cafeteria.model.entity.Empleado;
import com.cafeteria.model.enums.Rol;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl implements EmpleadoDAO {
    @Override
    public void insertar(Empleado empleado) throws Exception {
        String sql = "INSERT INTO empleados (empleado_id, nombre, usuario, correo, contrasena_hash, rol, estado, fecha_registro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getEmpleadoId());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getUsuario());
            stmt.setString(4, empleado.getCorreo());
            stmt.setString(5, empleado.getContrasenaHash());
            stmt.setString(6, empleado.getRol().name());
            stmt.setBoolean(7, empleado.isEstado());
            stmt.setDate(8, Date.valueOf(empleado.getFechaRegistro()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate entry
                throw new Exception("Ya existe un empleado con ese ID, usuario o correo.", e);
            }
            throw new Exception("Error al insertar empleado: " + e.getMessage(), e);
        }
    }

    @Override
    public Empleado buscarPorId(String id) throws Exception {
        String sql = "SELECT * FROM empleados WHERE empleado_id=?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapEmpleado(rs);
        }
        return null;
    }

    @Override
    public Empleado buscarPorUsuario(String usuario) throws Exception {
        String sql = "SELECT * FROM empleados WHERE usuario=?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapEmpleado(rs);
        }
        return null;
    }

    @Override
    public Empleado buscarPorCorreo(String correo) throws Exception {
        String sql = "SELECT * FROM empleados WHERE correo=?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapEmpleado(rs);

        } catch (SQLException e) {
            throw new Exception("Error al buscar por correo: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Empleado> listar() throws Exception {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Connection conn = Conexion.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapEmpleado(rs));
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Empleado empleado) throws Exception {
        String sql = "UPDATE empleados SET nombre=?, usuario=?, correo=?, contrasena_hash=?, rol=?, estado=? WHERE empleado_id=?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String hashPassword = BCrypt.hashpw(empleado.getContrasenaHash(), BCrypt.gensalt());

            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getUsuario());
            stmt.setString(3, empleado.getCorreo());
            stmt.setString(4, hashPassword);
            stmt.setString(5, empleado.getRol().name());
            stmt.setBoolean(6, empleado.isEstado());
            stmt.setString(7, empleado.getEmpleadoId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(String id) throws Exception {
        String sql = "DELETE FROM empleados WHERE empleado_id=?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    // =====================
    // Auxiliar
    // =====================
    private Empleado mapEmpleado(ResultSet rs) throws SQLException {
        Empleado e = new Empleado();
        e.setEmpleadoId(rs.getString("empleado_id"));
        e.setNombre(rs.getString("nombre"));
        e.setUsuario(rs.getString("usuario"));
        e.setCorreo(rs.getString("correo"));
        e.setContrasenaHash(rs.getString("contrasena_hash"));
        e.setRol(Rol.valueOf(rs.getString("rol").toUpperCase()));
        e.setEstado(rs.getBoolean("estado"));
        e.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        return e;
    }
}
