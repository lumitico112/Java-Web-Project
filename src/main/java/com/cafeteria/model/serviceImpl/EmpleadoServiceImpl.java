package com.cafeteria.model.serviceImpl;

import com.cafeteria.model.dao.EmpleadoDAO;
import com.cafeteria.model.daoimpl.EmpleadoDAOImpl;
import com.cafeteria.model.entity.Empleado;
import com.cafeteria.service.EmpleadoService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class EmpleadoServiceImpl implements EmpleadoService {
    private EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();

    @Override
    public void registrarEmpleado(Empleado empleado) throws Exception {
        // Normalizar datos
        if (empleado.getUsuario() != null) {
            empleado.setUsuario(empleado.getUsuario().trim().toLowerCase());
        }
        if (empleado.getCorreo() != null) {
            empleado.setCorreo(empleado.getCorreo().trim().toLowerCase());
        }

        // Validación previa en el servicio
        if (empleadoDAO.buscarPorId(empleado.getEmpleadoId()) != null) {
            throw new Exception("El ID ya está registrado.");
        }
        if (empleadoDAO.buscarPorUsuario(empleado.getUsuario()) != null) {
            throw new Exception("El usuario ya está registrado.");
        }
        if (((EmpleadoDAOImpl)empleadoDAO).buscarPorCorreo(empleado.getCorreo()) != null) {
            throw new Exception("El correo ya está registrado.");
        }

        // Encriptar contraseña antes de guardar
        String hashPassword = BCrypt.hashpw(empleado.getContrasenaHash(), BCrypt.gensalt());
        empleado.setContrasenaHash(hashPassword);

        // Insertar
        empleadoDAO.insertar(empleado);
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) throws Exception {
        // Normalizar usuario/correo
        if (empleado.getUsuario() != null) {
            empleado.setUsuario(empleado.getUsuario().trim().toLowerCase());
        }
        if (empleado.getCorreo() != null) {
            empleado.setCorreo(empleado.getCorreo().trim().toLowerCase());
        }

        // Si no se envía nueva contraseña, mantener el hash actual de la BD
        if (empleado.getContrasenaHash() == null || empleado.getContrasenaHash().isBlank()) {
            Empleado existente = empleadoDAO.buscarPorId(empleado.getEmpleadoId());
            if (existente == null) {
                throw new Exception("Empleado no encontrado para actualizar: " + empleado.getEmpleadoId());
            }
            empleado.setContrasenaHash(existente.getContrasenaHash());
        } else {
            // Si se envía nueva contraseña en texto plano, encriptar aquí
            String hashPassword = BCrypt.hashpw(empleado.getContrasenaHash(), BCrypt.gensalt());
            empleado.setContrasenaHash(hashPassword);
        }

        // Si no se envía nueva contraseña, mantener el hash actual de la BD
        if (empleado.getContrasenaHash() == null || empleado.getContrasenaHash().isBlank()) {
            Empleado existente = empleadoDAO.buscarPorId(empleado.getEmpleadoId());
            if (existente == null) {
                throw new Exception("Empleado no encontrado para actualizar: " + empleado.getEmpleadoId());
            }
            empleado.setContrasenaHash(existente.getContrasenaHash());
        } else {
            // Si se envía nueva contraseña en texto plano, encriptar aquí
            String hashPassword = BCrypt.hashpw(empleado.getContrasenaHash(), BCrypt.gensalt());
            empleado.setContrasenaHash(hashPassword);
        }

        // Si no se envía nueva contraseña, mantener el hash actual de la BD
        if (empleado.getContrasenaHash() == null || empleado.getContrasenaHash().isBlank()) {
            Empleado existente = empleadoDAO.buscarPorId(empleado.getEmpleadoId());
            if (existente == null) {
                throw new Exception("Empleado no encontrado para actualizar: " + empleado.getEmpleadoId());
            }
            empleado.setContrasenaHash(existente.getContrasenaHash());
        } else {
            // Si se envía una nueva contraseña en texto plano, encriptarla
            String hashPassword = BCrypt.hashpw(empleado.getContrasenaHash(), BCrypt.gensalt());
            empleado.setContrasenaHash(hashPassword);
        }

        empleadoDAO.actualizar(empleado);
    }

    @Override
    public Empleado buscarPorId(String id) throws Exception {
        return empleadoDAO.buscarPorId(id);
    }

    @Override
    public Empleado buscarPorUsuario(String usuario) throws Exception {
        return empleadoDAO.buscarPorUsuario(usuario);
    }

    @Override
    public Empleado buscarPorCorreo(String correo) throws Exception {
        return empleadoDAO.buscarPorCorreo(correo);
    }

    @Override
    public List<Empleado> listarEmpleados() throws Exception {
        return empleadoDAO.listar();
    }

    @Override
    public void eliminarEmpleado(String id) throws Exception {
        empleadoDAO.eliminar(id);
    }

}