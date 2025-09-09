package com.cafeteria.model.dao;

import com.cafeteria.model.entity.Empleado;

import java.util.List;

public interface EmpleadoDAO {

    void insertar(Empleado empleado) throws Exception;
    Empleado buscarPorId(String id) throws Exception;
    Empleado buscarPorUsuario(String usuario) throws Exception;
    Empleado buscarPorCorreo(String correo) throws Exception;
    List<Empleado> listar() throws Exception;
    void actualizar(Empleado empleado) throws Exception;
    void eliminar(String id) throws Exception;
}