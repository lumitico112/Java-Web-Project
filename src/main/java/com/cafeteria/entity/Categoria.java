package com.cafeteria.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    private Integer idCategoria;
    private String nombre;
    private String descripcion;
}
