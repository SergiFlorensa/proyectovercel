package com.example.ProyectoFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoFinal.entity.Reserva;
import com.example.ProyectoFinal.entity.dto.ReservaUsuarioDTO;
import com.example.ProyectoFinal.service.ReservaDTOService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequestMapping("/api/reservas")

    public class ReservaDTOController {

        @Autowired
        private ReservaDTOService reservaDTOService;

        @PostMapping
        public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaUsuarioDTO reservaDTO) {
            Reserva reserva = reservaDTOService.crearReserva(reservaDTO);
            return new ResponseEntity<>(reserva, HttpStatus.CREATED);
        }
    }
