package com.cafeteria.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    private Integer idRol;       // id_rol en BD
    private String nombre;       // nombre en BD
    private String descripcion;  // descripcion en BD
}
