package com.example.ProyectoFinal.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Tarjetabancaria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaBancaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtarjeta;

	private String nombretitular;

	@Pattern(regexp = "\\d{12}", message = "El número de tarjeta debe tener exactamente 12 dígitos")
	private String numerotarjeta;

	@Pattern(regexp = "\\d{3}", message = "El CVV debe tener exactamente 3 dígitos")
	private String cvv;

	private Date fechacaducidad; // Añadir campo para la fecha de caducidad

	@ManyToOne
    @JoinColumn(name = "idusuario")
	@JsonBackReference
    private Usuario usuario;

}
