package com.example.ProyectoFinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoFinal.entity.TarjetaBancaria;

@Repository
public interface TarjetaBancariaRepository extends JpaRepository<TarjetaBancaria, Long> {

    Optional<TarjetaBancaria> findByNumerotarjeta(String numerotarjeta);

}