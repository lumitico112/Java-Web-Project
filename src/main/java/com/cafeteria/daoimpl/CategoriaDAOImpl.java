package com.cafeteria.daoimpl;

import com.cafeteria.config.Conexion;
import com.cafeteria.dao.CategoriaDAO;
import com.cafeteria.entity.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {
    @Override
    public boolean create(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO Categoria (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Categoria categoria) throws SQLException {
        String sql = "UPDATE Categoria SET nombre = ?, descripcion = ? WHERE id_categoria = ?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setInt(3, categoria.getIdCategoria());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM Categoria WHERE id_categoria = ?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Categoria read(int id) throws SQLException {
        String sql = "SELECT * FROM Categoria WHERE id_categoria = ?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Categoria> readAll() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM Categoria";
        try (Connection conn = Conexion.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categorias.add(mapRow(rs));
            }
        }
        return categorias;
    }

    private Categoria mapRow(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("id_categoria"));
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        return c;
    }
}
