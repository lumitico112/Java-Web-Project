package com.cafeteria.service;

import com.cafeteria.entity.PerfilCliente;
import com.cafeteria.entity.Usuario;

import java.util.List;

/**
 *
 * @author Luismi
 */

public interface UsuarioService {
    boolean registrarUsuario(Usuario usuario) throws Exception;
    boolean actualizarUsuario(Usuario usuario) throws Exception;
    boolean eliminarUsuario(int id) throws Exception;
    Usuario buscarPorId(int id) throws Exception;
    Usuario buscarPorCorreo(String correo) throws Exception;
    List<Usuario> listarUsuarios() throws Exception;
    PerfilCliente buscarPerfilCliente(int idUsuario) throws Exception;
    boolean actualizarPerfilCliente(PerfilCliente perfil) throws Exception;
}
