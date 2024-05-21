package com.example.ProyectoFinal.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idusuario;
	private String nombre;
	private String contrasenia;
	private String email;
	private String residencia;
	private String numtelf;

	@OneToMany(mappedBy = "usuario")
	@JsonManagedReference // Indica que esta es la parte "padre" de la relación
	private List<Reserva> reservas;

	@OneToMany(mappedBy = "usuario")
	private List<TarjetaBancaria> tarjetasBancarias;
}
