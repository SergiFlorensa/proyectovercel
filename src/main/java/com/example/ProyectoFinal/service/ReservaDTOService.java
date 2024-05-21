package com.example.ProyectoFinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal.entity.Actividad;
import com.example.ProyectoFinal.entity.Alojamiento;
import com.example.ProyectoFinal.entity.Reserva;
import com.example.ProyectoFinal.entity.Usuario;
import com.example.ProyectoFinal.entity.dto.ReservaUsuarioDTO;
import com.example.ProyectoFinal.repository.ActividadRepository;
import com.example.ProyectoFinal.repository.AlojamientoRepository;
import com.example.ProyectoFinal.repository.ReservaRepository;
import com.example.ProyectoFinal.repository.UsuarioRepository;
@Service
	public class ReservaDTOService {

	    @Autowired
	    private ReservaRepository reservaRepository;

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    @Autowired
	    private AlojamientoRepository alojamientoRepository;

	    @Autowired
	    private ActividadRepository actividadRepository;

	    public Reserva crearReserva(ReservaUsuarioDTO reservaDTO) {
	        Reserva reserva = new Reserva();
	        reserva.setFechaentrada(reservaDTO.getFechaEntrada());
	        reserva.setFechasalida(reservaDTO.getFechaSalida());
	        reserva.setTotalprecio(reservaDTO.getTotalprecio());

	        Usuario usuario = usuarioRepository.findByIdusuario(reservaDTO.getIdUsuario())
	                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	        reserva.setUsuario(usuario);

	        Alojamiento alojamiento = alojamientoRepository.findById(reservaDTO.getIdAlojamiento())
	                .orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));
	        reserva.setAlojamiento(alojamiento);

	        Actividad actividad = actividadRepository.findById(reservaDTO.getIdActividad())
	                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
	        reserva.setActividad(actividad);
	        
	        return reservaRepository.save(reserva);
	    }
	}