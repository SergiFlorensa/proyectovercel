// ReservaService.java
package com.example.ProyectoFinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal.entity.Actividad;
import com.example.ProyectoFinal.entity.Alojamiento;
import com.example.ProyectoFinal.entity.Reserva;
import com.example.ProyectoFinal.entity.Usuario;
import com.example.ProyectoFinal.entity.dto.ActividadDTO;
import com.example.ProyectoFinal.entity.dto.AlojamientoDTO;
import com.example.ProyectoFinal.entity.dto.ReservaDTO;
import com.example.ProyectoFinal.entity.dto.ReservaUsuarioDTO;
import com.example.ProyectoFinal.entity.dto.UsuarioDTO;
import com.example.ProyectoFinal.repository.ReservaRepository;

@Service
public class ReservaService {
	@Autowired
	private ReservaRepository reservaRepository;
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//
//	@Autowired
//	private ActividadRepository actividadRepository;
//
//	@Autowired
//	private AlojamientoRepository alojamientoRepository;

	public void crearReserva(ReservaDTO reservaDTO) {
		// Aquí puedes convertir el DTO a una entidad de reserva si es necesario
		Reserva reserva = new Reserva();
		// Llenar la entidad de reserva con los datos del DTO

		reservaRepository.save(reserva);
	}

	public List<Reserva> getAllReservas() {
		return reservaRepository.findAll();
	}

	public Optional<Reserva> getReservaById(Long id) {
		return reservaRepository.findById(id);
	}

	public void saveOrUpdateReserva(Reserva reserva) {
		reservaRepository.save(reserva);
	}

	public void deleteReserva(Long id) {
		reservaRepository.deleteById(id);
	}

//	public List<ReservaUsuarioDTO> getAllReservasDto() {
//		List<ReservaUsuarioDTO> reservasDto = new ArrayList<>();
//
//		// Obtener datos de la base de datos
//		// Por ejemplo, obtener un usuario, una actividad y un alojamiento por sus
//		// respectivos IDs
//		Usuario usuario = usuarioRepository.findById(2L).orElse(null);
//		Actividad actividad = actividadRepository.findById(5L).orElse(null);
//		Alojamiento alojamiento = alojamientoRepository.findById(7L).orElse(null);
//
//		// Crear DTOs y configurarlos con los datos obtenidos
//		ReservaUsuarioDTO reservaDto = new ReservaUsuarioDTO();
//		reservaDto.setId(1L);
//		reservaDto.setFechaEntrada(new Date());
//		reservaDto.setFechaSalida(new Date());
//		reservaDto.setPrecioTotal(400.0);
//		reservaDto.setUsuario(convertirUsuarioToDTO(usuario));
//		reservaDto.setAlojamiento(convertirAlojamientoToDTO(alojamiento));
//		reservaDto.setActividad(convertirActividadToDTO(actividad));
//		reservasDto.add(reservaDto);
//
//		return reservasDto;
//	}
//
//	private UsuarioDTO convertirUsuarioToDTO(Usuario usuario) {
//        if (usuario != null) {
//            UsuarioDTO usuarioDTO = new UsuarioDTO();
//            usuarioDTO.setIdusuario(usuario.getIdusuario());
//            usuarioDTO.setNombre(usuario.getNombre());
//            usuarioDTO.setEmail(usuario.getEmail());
//            return usuarioDTO;
//        }
//        return null;
//    }
//
//	private ActividadDTO convertirActividadToDTO(Actividad actividad) {
//		if (actividad != null) {
//			ActividadDTO actividadDTO = new ActividadDTO();
//			actividadDTO.setId(actividad.getIdactividad());
//			actividadDTO.setNombre(actividad.getNombre());
//			actividadDTO.setTipo("Tipo Actividad"); // Configurar el tipo de actividad
//
//			// Otros campos
//			return actividadDTO;
//		}
//		return null;
//	}
//
//	private AlojamientoDTO convertirAlojamientoToDTO(Alojamiento alojamiento) {
//		if (alojamiento != null) {
//			AlojamientoDTO alojamientoDTO = new AlojamientoDTO();
//			alojamientoDTO.setId(alojamiento.getIdalojamiento());
//			alojamientoDTO.setNombre(alojamiento.getNombre());
//			alojamientoDTO.setTipo(alojamiento.getTipo());
//			alojamientoDTO.setLocalizacion(alojamiento.getLocalizacion());
//			alojamientoDTO.setPrecio(alojamiento.getPrecio());
//
//			// Otros campos
//			return alojamientoDTO;
//		}
//		return null;
//	}
//
//	public Reserva obtenerReservaPorId(Long id) {
//		return reservaRepository.findById(id).orElse(null);
//	}

	public List<ReservaUsuarioDTO> getReservasUsuario(Long userId) {
		// Obtener las reservas del usuario desde el repositorio de reservas
		List<Reserva> reservas = reservaRepository.findByUsuarioIdusuario(userId);

		// Mapear las reservas a DTOs de reserva de usuario
		List<ReservaUsuarioDTO> reservasDto = new ArrayList<>();
		for (Reserva reserva : reservas) {
			ReservaUsuarioDTO reservaDto = mapToReservaUsuarioDTO(reserva);
			reservasDto.add(reservaDto);
		}

		return reservasDto;
	}

	private ReservaUsuarioDTO mapToReservaUsuarioDTO(Reserva reserva) {
		ReservaUsuarioDTO reservaDto = new ReservaUsuarioDTO();
		reservaDto.setIdreserva(reserva.getIdreserva());
		reservaDto.setFechaEntrada(reserva.getFechaentrada());
		reservaDto.setFechaSalida(reserva.getFechasalida());
		reservaDto.setTotalprecio(reserva.getTotalprecio());
		reservaDto.setUsuario(convertirUsuarioToDTO(reserva.getUsuario()));
		reservaDto.setAlojamiento(convertirAlojamientoToDTO(reserva.getAlojamiento()));
		reservaDto.setActividad(convertirActividadToDTO(reserva.getActividad()));
		return reservaDto;
	}

	private UsuarioDTO convertirUsuarioToDTO(Usuario usuario) {
		if (usuario != null) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdusuario(usuario.getIdusuario());
			usuarioDTO.setNombre(usuario.getNombre());
			usuarioDTO.setEmail(usuario.getEmail());
			// Aquí puedes agregar más atributos según sea necesario
			return usuarioDTO;
		}
		return null;
	}

	private ActividadDTO convertirActividadToDTO(Actividad actividad) {
		if (actividad != null) {
			ActividadDTO actividadDTO = new ActividadDTO();
			actividadDTO.setIdactividad(actividad.getIdactividad());
			actividadDTO.setNombre(actividad.getNombre());
			actividadDTO.setTipo(actividad.getTipo());
			// Aquí puedes agregar más atributos según sea necesario
			return actividadDTO;
		}
		return null;
	}

	private AlojamientoDTO convertirAlojamientoToDTO(Alojamiento alojamiento) {
		if (alojamiento != null) {
			AlojamientoDTO alojamientoDTO = new AlojamientoDTO();
			alojamientoDTO.setIdalojamiento(alojamiento.getIdalojamiento());
			alojamientoDTO.setNombre(alojamiento.getNombre());
			alojamientoDTO.setTipo(alojamiento.getTipo());
			alojamientoDTO.setLocalizacion(alojamiento.getLocalizacion());
			alojamientoDTO.setPrecio(alojamiento.getPrecio());
			// Aquí puedes agregar más atributos según sea necesario
			return alojamientoDTO;
		}
		return null;
	}

	public void realizarReserva(ReservaUsuarioDTO reservaUsuarioDTO) {
		Reserva reserva = new Reserva();
		// Configurar los atributos de la reserva usando los datos proporcionados en
		// reservaUsuarioDTO
		reserva.setFechaentrada(reservaUsuarioDTO.getFechaEntrada());
		reserva.setFechasalida(reservaUsuarioDTO.getFechaSalida());
		reserva.setTotalprecio(reservaUsuarioDTO.getTotalprecio());
		// Configurar otros atributos de la reserva según lo recibido en
		// reservaUsuarioDTO

		// Luego, guardar la reserva en la base de datos
		reservaRepository.save(reserva);
	}

}
