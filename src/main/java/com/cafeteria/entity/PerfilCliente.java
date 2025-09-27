package com.cafeteria.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Luismi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilCliente {
    private int idUsuario;
    private String telefono;
    private String direccion;
    private int puntosFidelizacion;
}

