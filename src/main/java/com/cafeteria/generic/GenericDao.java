package com.cafeteria.generic;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Docente
 */
public interface GenericDao<T> {
    boolean create(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
    T read(int id) throws SQLException;
    List<T> readAll() throws SQLException;
}
