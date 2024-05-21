package com.example.ProyectoFinal.entity.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BusquedaAlojamientoDTO {
	
	private String localizacion;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private int numPersonas;
}
