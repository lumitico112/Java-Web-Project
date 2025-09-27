package com.cafeteria.service;

import com.cafeteria.entity.Usuario;

public interface LoginService {

    /**
     * Autentica a un usuario por su nombre de usuario o correo y su contraseña
     * @param userOrEmail usuario o correo
     * @param plainPassword contraseña en texto plano
     * @return Usuario autenticado o null si las credenciales no son válidas
     * @throws Exception si ocurre un error al consultar datos
     */
    Usuario autenticar(String userOrEmail, String plainPassword) throws Exception;
}
