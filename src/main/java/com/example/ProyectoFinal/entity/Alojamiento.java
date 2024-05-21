package com.example.ProyectoFinal.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Alojamientos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alojamiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idalojamiento;
	private String nombre;
	private String imagen;
	private String imagen1;
	private String imagen2;
	private String tipo;
	private String descripcion;
	private String descripcionlarga;
	private String servicios;
	private String prestaciones;
	private String localizacion;
	private String region;
	private double valoracion;
	private int capacidad;
	private double precio;
	private String numtelf;
	private int numhabs;
	private int lavabos;
	private boolean habsdisponibles;

	@OneToMany(mappedBy = "alojamiento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Reserva> reservas;
}
