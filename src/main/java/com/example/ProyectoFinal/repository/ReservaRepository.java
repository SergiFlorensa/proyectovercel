package com.example.ProyectoFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoFinal.entity.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	List<Reserva> findByUsuarioIdusuario(Long userId);
}
