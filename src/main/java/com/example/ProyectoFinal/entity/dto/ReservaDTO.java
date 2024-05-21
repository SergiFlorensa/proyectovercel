package com.example.ProyectoFinal.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
	private Long idreserva;
    private LocalDate fechaentrada;
    private LocalDate fechasalida;
    private Integer numpersonas;
    private String estadoreserva;
    private String numreserva;
    private BigDecimal totalprecio;
    private Long idusuario;
    private Long idalojamiento;
    private Long idactividad;
}
