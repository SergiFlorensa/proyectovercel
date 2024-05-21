package com.example.ProyectoFinal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoFinal.entity.Actividad;
import com.example.ProyectoFinal.entity.Alojamiento;
import com.example.ProyectoFinal.entity.Reserva;
import com.example.ProyectoFinal.entity.Usuario;
import com.example.ProyectoFinal.entity.dto.ReservaDTO;
import com.example.ProyectoFinal.entity.dto.ReservaUsuarioDTO;
import com.example.ProyectoFinal.repository.ActividadRepository;
import com.example.ProyectoFinal.repository.AlojamientoRepository;
import com.example.ProyectoFinal.repository.ReservaRepository;
import com.example.ProyectoFinal.repository.UsuarioRepository;
import com.example.ProyectoFinal.service.ReservaDTOService;
import com.example.ProyectoFinal.service.ReservaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequestMapping("reservas/")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	@Autowired
	private ReservaDTOService reservaDTOService;

	@Autowired
	private ReservaRepository reservaRepository; // Suponiendo que tienes un Repositorio de Reservas

	@Autowired
	private UsuarioRepository usuarioRepository; // Repositorio para la entidad de Usuario
	@Autowired
	private AlojamientoRepository alojamientoRepository; // Repositorio para la entidad de Alojamiento
	@Autowired
	private ActividadRepository actividadRepository; // Repositorio para la entidad de Actividad

	@PostMapping("/reservas")
	public ResponseEntity<String> guardarReserva(@RequestBody ReservaDTO reservaDTO) {
		try {
			// Verificar si el usuario, el alojamiento y la actividad existen en la base de
			// datos
			Usuario usuario = usuarioRepository.findByIdusuario(reservaDTO.getIdusuario())
					.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
			Alojamiento alojamiento = alojamientoRepository.findById(reservaDTO.getIdalojamiento())
					.orElseThrow(() -> new RuntimeException("Alojamiento no encontrado"));
			Actividad actividad = actividadRepository.findById(reservaDTO.getIdactividad())
					.orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

			// Crear un nuevo objeto Reserva y asignar los valores del DTO
			Reserva reserva = new Reserva();
			reserva.setIdreserva(reservaDTO.getIdreserva());

			reserva.setFechaentrada(reservaDTO.getFechaentrada());
			reserva.setFechasalida(reservaDTO.getFechasalida());
			reserva.setNumpersonas(reservaDTO.getNumpersonas());
			reserva.setEstadoreserva(reservaDTO.getEstadoreserva());
			reserva.setNumreserva(reservaDTO.getNumreserva());
			reserva.setTotalprecio(reservaDTO.getTotalprecio());
			reserva.setUsuario(usuario);
			reserva.setAlojamiento(alojamiento);
			reserva.setActividad(actividad);

			// Guardar la reserva en la base de datos
			reservaRepository.save(reserva);

			return ResponseEntity.status(HttpStatus.CREATED).body("Reserva guardada correctamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al guardar la reserva: " + e.getMessage());
		}
	}

	@GetMapping
	public List<Reserva> getAllReservas() {
		return reservaService.getAllReservas();
	}

	@GetMapping("/{id}")
	public Optional<Reserva> getReservaById(@PathVariable("id") Long id) {
		return reservaService.getReservaById(id);
	}

	@PostMapping
	public Reserva saveOrUpdateReserva(@RequestBody Reserva reserva) {
		reservaService.saveOrUpdateReserva(reserva);
		return reserva;
	}

	@DeleteMapping("/{id}")
	public void deleteReserva(@PathVariable("id") Long id) {
		reservaService.deleteReserva(id);
	}

//	@GetMapping("/todos")
//	public List<ReservaUsuarioDTO> getAllReservasDto() {
//		return reservaService.getAllReservasDto();
//	}
//	@PostMapping("/realizar-reserva")
//	public ResponseEntity<String> realizarReserva(@RequestParam("fechaEntrada") String fechaEntrada,
//			@RequestParam("fechaSalida") String fechaSalida) {
//
//		// Lógica para realizar la reserva utilizando las fechas proporcionadas
//		// Aquí puedes llamar al servicio de reserva para procesar la reserva
//
//		// Simulación: simplemente devolvemos un mensaje de éxito con las fechas
//		// proporcionadas
//		String mensaje = "Reserva realizada con éxito. Fecha de entrada: " + fechaEntrada + ", Fecha de salida: "
//				+ fechaSalida;
//
//		return new ResponseEntity<>(mensaje, HttpStatus.OK);
//	}

	@GetMapping("/usuarios/{userId}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<List<ReservaUsuarioDTO>> obtenerReservasUsuario(@PathVariable Long userId) {
		List<ReservaUsuarioDTO> reservas = reservaService.getReservasUsuario(userId);
		if (reservas.isEmpty()) {
			return ResponseEntity.noContent().build(); // Devuelve una respuesta sin contenido si no hay reservas
		} else {
			return ResponseEntity.ok(reservas); // Devuelve las reservas si hay alguna
		}
	}

	@PostMapping("/realizar-reserva")
	public ResponseEntity<String> realizarReserva(@RequestBody ReservaUsuarioDTO reservaUsuarioDTO) {
		reservaService.realizarReserva(reservaUsuarioDTO);
		return ResponseEntity.ok("Reserva realizada con éxito.");
	}

}
