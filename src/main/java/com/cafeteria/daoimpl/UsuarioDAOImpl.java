package com.cafeteria.daoimpl;

import com.cafeteria.config.Conexion;
import com.cafeteria.dao.UsuarioDAO;
import com.cafeteria.entity.PerfilCliente;
import com.cafeteria.entity.Usuario;
import com.cafeteria.entity.Rol;
import com.cafeteria.enums.Estado;
import org.mindrot.jbcrypt.BCrypt;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luismi
 */

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public boolean create(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre, apellido, correo, contrasena, estado, fecha_creacion, id_rol) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt()));
            stmt.setString(5, usuario.getEstado().name().toLowerCase());
            stmt.setTimestamp(6, Timestamp.valueOf(
                    usuario.getFechaCreacion() != null ? usuario.getFechaCreacion() : LocalDateTime.now()
            ));
            stmt.setInt(7, usuario.getRol().getIdRol());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Usuario usuario) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE Usuario SET nombre=?, apellido=?, correo=?, estado=?, id_rol=? ");
        if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
            sql.append(", contrasena=? ");
        }
        sql.append("WHERE id_usuario=?");

        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getEstado().name().toLowerCase());
            stmt.setInt(5, usuario.getRol().getIdRol());

            if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
                stmt.setString(6, BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt()));
                stmt.setInt(7, usuario.getIdUsuario());
            } else {
                stmt.setInt(6, usuario.getIdUsuario());
            }

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id_usuario=?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Usuario read(int id) throws SQLException {
        String sql = "SELECT u.*, r.nombre AS rol_nombre, r.descripcion AS rol_desc " +
                "FROM Usuario u JOIN Rol r ON u.id_rol = r.id_rol WHERE u.id_usuario=?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapUsuario(rs);
            }
        }
        return null;
    }

    @Override
    public List<Usuario> readAll() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT u.*, r.nombre AS rol_nombre, r.descripcion AS rol_desc " +
                "FROM Usuario u JOIN Rol r ON u.id_rol = r.id_rol";
        try (Connection conn = Conexion.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapUsuario(rs));
            }
        }
        return lista;
    }

    @Override
    public Usuario buscarPorCorreo(String correo) throws Exception {
        String sql = "SELECT u.*, r.nombre AS rol_nombre, r.descripcion AS rol_desc " +
                "FROM Usuario u JOIN Rol r ON u.id_rol = r.id_rol WHERE u.correo=?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapUsuario(rs);
            }
        }
        return null;
    }


    /**
     * Mapea un ResultSet a un Usuario con su Rol.
     */
    private Usuario mapUsuario(ResultSet rs) throws SQLException {
        Rol rol = new Rol(
                rs.getInt("id_rol"),
                rs.getString("rol_nombre"),
                rs.getString("rol_desc")
        );

        return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("correo"),
                rs.getString("contrasena"),
                Estado.valueOf(rs.getString("estado").toUpperCase()),
                rs.getTimestamp("fecha_creacion").toLocalDateTime(),
                rol
        );
    }

    @Override
    public PerfilCliente buscarPerfilCliente(int idUsuario) throws Exception {
        String sql = "SELECT id_usuario, telefono, direccion, puntos_fidelizacion " +
                "FROM Perfil_Cliente WHERE id_usuario=?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PerfilCliente perfil = new PerfilCliente();
                    perfil.setIdUsuario(rs.getInt("id_usuario"));
                    perfil.setTelefono(rs.getString("telefono"));
                    perfil.setDireccion(rs.getString("direccion"));
                    perfil.setPuntosFidelizacion(rs.getInt("puntos_fidelizacion"));
                    return perfil;
                }
            }
        }
        return null;
    }

    @Override
    public boolean actualizarPerfilCliente(PerfilCliente perfil) throws Exception {
        String sql = "UPDATE Perfil_Cliente SET telefono=?, direccion=?, puntos_fidelizacion=? " +
                "WHERE id_usuario=?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perfil.getTelefono());
            stmt.setString(2, perfil.getDireccion());
            stmt.setInt(3, perfil.getPuntosFidelizacion());
            stmt.setInt(4, perfil.getIdUsuario());

            return stmt.executeUpdate() > 0;
        }
    }

}
