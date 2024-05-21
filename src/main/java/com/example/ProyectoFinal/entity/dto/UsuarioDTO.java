package com.example.ProyectoFinal.entity.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long idusuario;
    private String email;
	private String contrasenia;	
	private String residencia;
	private String nombre;	
	private String numtelf;
}