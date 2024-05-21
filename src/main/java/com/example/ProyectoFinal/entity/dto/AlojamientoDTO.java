package com.example.ProyectoFinal.entity.dto;

import lombok.Data;

@Data
public class AlojamientoDTO {
    private Long idalojamiento;
    private String nombre;
    private String tipo;
	private String localizacion;
	private double precio;

    // Otros campos según sea necesario
    
    // Getters y setters
}