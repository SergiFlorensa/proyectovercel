package com.example.ProyectoFinal.entity.dto;

import lombok.Data;

@Data
public class ActividadDTO {
    private Long idactividad;
    private String nombre;
    private String tipo;
    private String localizacion;
    // Otros campos según sea necesario
    
    // Getters y setters
}