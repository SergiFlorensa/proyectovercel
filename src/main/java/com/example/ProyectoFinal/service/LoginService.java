package com.example.ProyectoFinal.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal.entity.Usuario;
import com.example.ProyectoFinal.entity.dto.UsuarioDTO;
import com.example.ProyectoFinal.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public List<Usuario> obtenerTodosLosUsuarios() {
		return loginRepository.findAll(); // Obtener todos los registros de la tabla Usuarios
	}

	public List<Usuario> obtenerTodosLosUsuariosDTO() {
		return loginRepository.findAll(); // Obtener todos los registros de la tabla Usuarios
	}

	public UsuarioDTO obtenerUsuarioPorId(Long idUsuario) {
		if (idUsuario == null || idUsuario <= 0) {
			// Manejar el caso en que la ID de usuario sea nula o negativa
			return null;
		}
		Usuario usuario = loginRepository.findById(idUsuario).orElse(null);
		if (usuario != null) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdusuario(usuario.getIdusuario());
			usuarioDTO.setNombre(usuario.getNombre());
			usuarioDTO.setContrasenia(usuario.getContrasenia());
			usuarioDTO.setEmail(usuario.getEmail());
			return usuarioDTO;
		 } else {
		        // Manejar el caso en que el usuario no existe
		        throw new NoSuchElementException("El usuario con el ID " + idUsuario + " no existe");
		        
		}
	}

	public Long authenticate3(String email, String contrasenia) {
		// Buscar el usuario por email
		Usuario usuario = loginRepository.findByEmail(email);

		// Verificar si el usuario existe y si la contraseña coincide
		if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
			return usuario.getIdusuario(); // Devuelve el ID del usuario si se autentica correctamente
		} else {
			return null; // Devuelve null si no se encuentra el usuario o la contraseña no coincide
		}
	}
}
