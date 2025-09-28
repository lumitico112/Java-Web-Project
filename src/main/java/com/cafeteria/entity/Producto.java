package com.cafeteria.entity;


import com.cafeteria.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal impuesto;
    private Estado estado;
    private String imagenUrl;
    private Categoria categoria;
}