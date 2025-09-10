package com.cafeteria.model.serviceImpl;

import com.cafeteria.model.dao.EmpleadoDAO;
import com.cafeteria.model.daoimpl.EmpleadoDAOImpl;
import com.cafeteria.model.entity.Empleado;
import com.cafeteria.service.LoginService;
import org.mindrot.jbcrypt.BCrypt;

public class LoginServiceImpl implements LoginService {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();

    @Override
    public Empleado autenticar(String userOrEmail, String plainPassword) throws Exception {
        if (userOrEmail == null || plainPassword == null) {
            return null;
        }

        String raw = userOrEmail.trim();
        String normalized = raw.toLowerCase();

        // Buscar por usuario (normalizado y crudo para compatibilidad con datos antiguos)
        Empleado empleado = empleadoDAO.buscarPorUsuario(normalized);
        if (empleado == null) {
            empleado = empleadoDAO.buscarPorUsuario(raw);
        }
        // Buscar por correo si no se encontró por usuario
        if (empleado == null) {
            empleado = empleadoDAO.buscarPorCorreo(normalized);
        }
        if (empleado == null) {
            empleado = empleadoDAO.buscarPorCorreo(raw);
        }
        if (empleado == null) {
            return null; // usuario/correo no existe
        }

        // Validar que el hash sea correcto (no nulo ni truncado)
        String storedHash = empleado.getContrasenaHash();
        if (storedHash == null || storedHash.length() < 55) {
            // Los hashes de BCrypt suelen medir ~60 chars; si es menor, asumimos truncamiento
            throw new Exception("Hash de contraseña inválido en BD (posible truncamiento o valor nulo).");
        }

        boolean ok;
        try {
            ok = BCrypt.checkpw(plainPassword, storedHash);
        } catch (IllegalArgumentException ex) {
            // Ocurre cuando el hash tiene formato inválido
            throw new Exception("Hash de contraseña con formato inválido en BD.", ex);
        }

        return ok ? empleado : null;
    }
}
