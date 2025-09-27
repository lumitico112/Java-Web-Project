package com.cafeteria.dao;

import com.cafeteria.entity.PerfilCliente;
import com.cafeteria.entity.Usuario;
import com.cafeteria.generic.GenericDao;

/**
 *
 * @author Luismi
 */

public interface UsuarioDAO extends GenericDao<Usuario> {
    Usuario buscarPorCorreo(String correo) throws Exception;
    PerfilCliente buscarPerfilCliente(int idUsuario) throws Exception;
    boolean actualizarPerfilCliente(PerfilCliente perfil) throws Exception;
}