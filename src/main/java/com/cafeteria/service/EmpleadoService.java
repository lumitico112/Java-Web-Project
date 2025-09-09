package com.cafeteria.service;

import com.cafeteria.model.entity.Empleado;

import java.util.List;

public interface EmpleadoService {
    void registrarEmpleado(Empleado empleado) throws Exception;
    void actualizarEmpleado(Empleado empleado) throws Exception;
    Empleado buscarPorId(String id) throws Exception;
    Empleado buscarPorUsuario(String usuario) throws Exception;   // 🔹 nuevo acceso
    Empleado buscarPorCorreo(String correo) throws Exception;     // 🔹 nuevo acceso
    List<Empleado> listarEmpleados() throws Exception;
    void eliminarEmpleado(String id) throws Exception;
}
