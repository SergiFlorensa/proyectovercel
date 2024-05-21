package com.example.ProyectoFinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoFinal.entity.Usuario;
import com.example.ProyectoFinal.entity.dto.LoginDTO;
import com.example.ProyectoFinal.entity.dto.UsuarioDTO;
import com.example.ProyectoFinal.repository.LoginRepository;
import com.example.ProyectoFinal.service.LoginService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api") // Base URL para tu API
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private LoginRepository loginRepository;

	// Obtiene la información del usuario que acaba de iniciar sesión
	@GetMapping("/usuarioDTO/{idUsuario}")
	public UsuarioDTO obtenerUsuarioDTO(@PathVariable Long idUsuario) {
		return loginService.obtenerUsuarioPorId(idUsuario);
	}

	// Obtiene todos los usuarios
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> obtenerUsuarios() {
		List<Usuario> usuarios = loginService.obtenerTodosLosUsuarios(); // Obtener datos del servicio
		return ResponseEntity.ok(usuarios); // Devolver la lista de usuarios en formato JSON
	}

	// Endpoint para iniciar sesión
	@PostMapping("/inicio-sesion")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public Long login3(@RequestBody LoginDTO loginDTO) {
		Long idusuario = loginService.authenticate3(loginDTO.getEmail(), loginDTO.getContrasenia());

		if (idusuario != null) {
			// Si es autenticado, devuelves un token o un mensaje de éxito
			return idusuario;
		} else {
			// Si no, devuelves un mensaje de error
			return null;
		}
	}

	// Crea los usuarios
	@PostMapping("/usuarios")
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		return loginRepository.save(usuario);
	}

	// Verifica el email
	@PostMapping("/email")
	public boolean verificarEmail(@RequestBody String email) {
		Usuario usuario = loginRepository.findByEmail(email);
		return usuario != null;
	}

	// Verifica el teléfono
	@PostMapping("/telefono")
	public boolean verificarTelf(@RequestBody String numtelf) {
		Usuario usuario = loginRepository.findByNumtelf(numtelf);
		return usuario != null;
	}
}
