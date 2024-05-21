package com.example.ProyectoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoFinal.entity.Reserva;

@Repository
public interface ReservaDTORepository extends JpaRepository<Reserva, Long> {
}