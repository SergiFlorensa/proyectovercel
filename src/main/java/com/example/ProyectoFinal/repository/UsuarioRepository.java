package com.example.ProyectoFinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoFinal.entity.Usuario; // Asegúrate de ajustar el paquete y la clase de la entidad Usuario según tu estructura de paquetes y nombres de clases
import com.example.ProyectoFinal.entity.dto.UsuarioDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

		public Usuario findByEmailAndContrasenia(String contrasenia, String email);
//	    Usuario findByIdusuario(Long idUsuario);
	    Usuario findByNombre(String nombre);
	    Usuario findByEmail(String email);
	    Optional<Usuario> findByIdusuario(UsuarioDTO usuarioDTO);
	    Optional<Usuario> findByIdusuario(Long usuario);

	
			
}