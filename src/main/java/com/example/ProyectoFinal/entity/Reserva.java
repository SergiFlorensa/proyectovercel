package com.example.ProyectoFinal.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reserva")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idreserva;
	private LocalDate fechaentrada;
	private LocalDate fechasalida;
	private Integer numpersonas;
	private String estadoreserva;
	private String numreserva;
	private BigDecimal totalprecio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario")
	@JsonBackReference
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idalojamiento")
	@JsonBackReference
	private Alojamiento alojamiento;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idactividad")
	@JsonBackReference
	private Actividad actividad;

}
