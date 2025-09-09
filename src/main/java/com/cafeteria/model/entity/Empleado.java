package com.cafeteria.model.entity;

import com.cafeteria.model.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    private String empleadoId;
    private String nombre;
    private String usuario;
    private String correo;
    private String contrasenaHash;
    private Rol rol;
    private boolean estado;
    private LocalDate fechaRegistro;

}
