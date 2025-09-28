package com.cafeteria.daoimpl;

import com.cafeteria.config.Conexion;
import com.cafeteria.dao.ProductoDAO;
import com.cafeteria.entity.Categoria;
import com.cafeteria.entity.Producto;
import com.cafeteria.enums.Estado;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public boolean create(Producto producto) throws SQLException {
        String sql = "INSERT INTO Producto (nombre, descripcion, precio, impuesto, estado, imagen_url, id_categoria) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setBigDecimal(3, producto.getPrecio());
            stmt.setBigDecimal(4, producto.getImpuesto());
            stmt.setString(5, producto.getEstado().name().toLowerCase());
            stmt.setString(6, producto.getImagenUrl());
            stmt.setInt(7, producto.getCategoria().getIdCategoria());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Producto producto) throws SQLException {
        String sql = "UPDATE Producto SET nombre = ?, descripcion = ?, precio = ?, impuesto = ?, estado = ?, imagen_url = ?, id_categoria = ? " +
                "WHERE id_producto = ?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setBigDecimal(3, producto.getPrecio());
            stmt.setBigDecimal(4, producto.getImpuesto());
            stmt.setString(5, producto.getEstado().name().toLowerCase());
            stmt.setString(6, producto.getImagenUrl());
            stmt.setInt(7, producto.getCategoria().getIdCategoria());
            stmt.setInt(8, producto.getIdProducto());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM Producto WHERE id_producto = ?";
        try (Connection conn = Conexion.getConection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Producto read(int id) throws SQLException {
        String sql = "SELECT p.*, c.nombre as cat_nombre, c.descripcion as cat_desc " +
                "FROM Producto p " +
                "JOIN Categoria c ON p.id_categoria = c.id_categoria " +
                "WHERE p.id_producto = ?";
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
    public List<Producto> readAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.*, c.nombre as cat_nombre, c.descripcion as cat_desc " +
                "FROM Producto p " +
                "JOIN Categoria c ON p.id_categoria = c.id_categoria " +
                "ORDER BY p.nombre";
        try (Connection conn = Conexion.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(mapRow(rs));
            }
        }
        return productos;
    }

    private Producto mapRow(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getBigDecimal("precio"));
        p.setImpuesto(rs.getBigDecimal("impuesto"));
        p.setEstado(Estado.valueOf(rs.getString("estado").toUpperCase()));
        p.setImagenUrl(rs.getString("imagen_url"));

        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("id_categoria"));
        c.setNombre(rs.getString("cat_nombre"));
        c.setDescripcion(rs.getString("cat_desc"));
        p.setCategoria(c);

        return p;
    }
}