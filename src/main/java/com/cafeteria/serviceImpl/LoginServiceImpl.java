package com.cafeteria.serviceImpl;

import com.cafeteria.dao.UsuarioDAO;
import com.cafeteria.daoimpl.UsuarioDAOImpl;
import com.cafeteria.entity.Usuario;
import com.cafeteria.service.LoginService;
import org.mindrot.jbcrypt.BCrypt;

public class LoginServiceImpl implements LoginService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @Override
    public Usuario autenticar(String identifier, String plainPassword) throws Exception {
        if (identifier == null || plainPassword == null) {
            return null;
        }

        Usuario usuario = null;

        // 🔹 Primero intentar con ID numérico
        try {
            int id = Integer.parseInt(identifier.trim());
            usuario = usuarioDAO.read(id);
        } catch (NumberFormatException e) {
            // No es número → se intenta como correo
        }

        // 🔹 Si no se encontró por ID, buscar por correo
        if (usuario == null) {
            usuario = usuarioDAO.buscarPorCorreo(identifier.trim().toLowerCase());
        }

        if (usuario == null) {
            return null; // No existe
        }

        // 🔹 Validar contraseña con BCrypt
        String storedHash = usuario.getContrasena();
        if (storedHash == null || storedHash.length() < 55) {
            throw new Exception("Hash de contraseña inválido en BD.");
        }

        boolean ok;
        try {
            ok = BCrypt.checkpw(plainPassword, storedHash);
        } catch (IllegalArgumentException ex) {
            throw new Exception("Formato de hash inválido en BD.", ex);
        }

        return ok ? usuario : null;
    }
}
