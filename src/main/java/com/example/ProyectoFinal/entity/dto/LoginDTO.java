package com.example.ProyectoFinal.entity.dto;

import lombok.Data;

@Data
public class LoginDTO {
	private Long usuarioid;
    private String email;
    private String contrasenia;
}
