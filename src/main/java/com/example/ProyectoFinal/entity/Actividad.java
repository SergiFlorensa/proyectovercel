package com.example.ProyectoFinal.entity;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Actividades")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actividad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idactividad;
	private String nombre;
	private String imagen;
	private String imagen1;
	private String imagen2;
	private String localizacion;
	private String region;
	private double precio;
	private int duracion;
	private String dificultad;
	private String numtelf;
	private String tipo;
	private String descripcion;
	private String descripcionlarga;
	private Time hora;
	private int plazasdisponibles;

	@OneToOne(mappedBy = "actividad")
	private Reserva reserva;
}
