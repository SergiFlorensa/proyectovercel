package com.example.ProyectoFinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProyectoFinal.entity.Usuario;

public interface LoginRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);

	Optional<Usuario> findByidusuario(Long idusuario);

	Usuario findByNumtelf(String numtelf);
	// JpaRepository proporciona el método findAll para obtener todos los registros
}
