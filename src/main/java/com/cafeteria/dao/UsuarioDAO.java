package com.cafeteria.dao;

import com.cafeteria.entity.Usuario;
import com.cafeteria.generic.GenericDao;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO extends GenericDao<Usuario> {
    Usuario buscarPorCorreo(String correo) throws Exception;
}