package com.cafeteria.serviceImpl;

import com.cafeteria.dao.UsuarioDAO;
import com.cafeteria.daoimpl.UsuarioDAOImpl;
import com.cafeteria.entity.Usuario;
import com.cafeteria.service.UsuarioService;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    // Constructor por defecto (usa UsuarioDAOImpl sin necesidad de pasar Connection)
    public UsuarioServiceImpl() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    public boolean registrarUsuario(Usuario usuario)  throws Exception {
        return usuarioDAO.create(usuario);
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) throws Exception {
        return usuarioDAO.update(usuario);
    }

    @Override
    public boolean eliminarUsuario(int id) throws Exception {
        return usuarioDAO.delete(id);
    }

    @Override
    public Usuario buscarPorId(int id) throws Exception {
        return usuarioDAO.read(id);
    }

    @Override
    public Usuario buscarPorCorreo(String correo) throws Exception {
        return usuarioDAO.buscarPorCorreo(correo);
    }

    @Override
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioDAO.readAll();
    }
}
