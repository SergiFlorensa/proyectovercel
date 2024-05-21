package com.example.ProyectoFinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ProyectoFinal.entity.Usuario; // Ajusta el paquete y la clase de la entidad Usuario según tu estructura de paquetes y nombres de clases
import com.example.ProyectoFinal.entity.dto.LoginDTO;
import com.example.ProyectoFinal.entity.dto.UsuarioDTO;
import com.example.ProyectoFinal.repository.UsuarioRepository; // Ajusta el paquete según tu estructura de paquetes

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> getUsuarioById(Long id) {
		return usuarioRepository.findById(id);
	}

	public void deleteUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

	public void saveOrUpdateUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	@Transactional
	public Usuario registrarUsuario(UsuarioDTO registroUsuarioDTO) {
	    // Verificar si el correo electrónico ya está registrado
	    if (usuarioRepository.findByEmail(registroUsuarioDTO.getEmail()) != null) {
	        throw new RuntimeException("El correo electrónico ya está registrado.");
	    }

	    // Crear una nueva instancia de Usuario con los datos del DTO
	    Usuario nuevoUsuario = new Usuario();
	    nuevoUsuario.setNombre(registroUsuarioDTO.getNombre());
	    nuevoUsuario.setContrasenia(registroUsuarioDTO.getContrasenia());
	    nuevoUsuario.setEmail(registroUsuarioDTO.getEmail());
	    nuevoUsuario.setResidencia(registroUsuarioDTO.getResidencia());
	    nuevoUsuario.setNumtelf(registroUsuarioDTO.getNumtelf());

	    // Guardar el nuevo usuario en la base de datos
	    return usuarioRepository.save(nuevoUsuario);
	}

	
	public boolean autenticarUsuario(LoginDTO loginRequest) {
	    Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail());
	    
	    return usuario != null && usuario.getContrasenia().equals(loginRequest.getContrasenia());
	}



	
}