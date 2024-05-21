package com.example.ProyectoFinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoFinal.entity.Usuario;
import com.example.ProyectoFinal.entity.dto.LoginDTO;
import com.example.ProyectoFinal.entity.dto.UsuarioDTO;
import com.example.ProyectoFinal.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("usuarios/")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		List<Usuario> usuarios = usuarioService.getAllUsuarios();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
		return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public Usuario saveOrUpdateUsuario(@RequestBody Usuario usuario) {
		usuarioService.saveOrUpdateUsuario(usuario);
		return usuario;
	}

	@DeleteMapping("/{id}")
	public void deleteUsuario(@PathVariable("id") Long id) {
		usuarioService.deleteUsuario(id);
	}

	@PostMapping("/registro")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO registroUsuarioDTO) {
		try {
			// Llamar al servicio para registrar al usuario
			usuarioService.registrarUsuario(registroUsuarioDTO);
			return ResponseEntity.ok("Usuario registrado con éxito.");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> iniciarSesion(@RequestBody LoginDTO loginRequest) {
		if (usuarioService.autenticarUsuario(loginRequest)) {
			return ResponseEntity.ok("Usuario autenticado exitosamente");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
		}
	}

}
