package com.example.ProyectoFinal.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ReservaUsuarioDTO {
	private Long idreserva;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private BigDecimal totalprecio;
	private UsuarioDTO usuario;
	private AlojamientoDTO alojamiento;
	private ActividadDTO actividad;
	
	
	public UsuarioDTO getIdUsuario() {
		// TODO Auto-generated method stub
		return null;
	}
	public Long getIdAlojamiento() {
		// TODO Auto-generated method stub
		return null;
	}
	public Long getIdActividad() {
		// TODO Auto-generated method stub
		return null;
	}

}