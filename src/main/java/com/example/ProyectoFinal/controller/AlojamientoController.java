package com.example.ProyectoFinal.controller;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoFinal.entity.Actividad;
import com.example.ProyectoFinal.entity.Alojamiento;
import com.example.ProyectoFinal.entity.dto.BusquedaAlojamientoDTO;
import com.example.ProyectoFinal.service.ActividadService;
import com.example.ProyectoFinal.service.AlojamientoService;

@RestController
@RequestMapping("alojamientos/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlojamientoController {

	@Autowired
	private AlojamientoService alojamientoService;

	@Autowired
	private ActividadService actividadService;

	@GetMapping
	public List<Alojamiento> getAllAlojamientos() {
		return alojamientoService.getAllAlojamientos();
	}

	@GetMapping("/{id}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public Optional<Alojamiento> getAlojamientoById(@PathVariable("id") Long id) {
		return alojamientoService.getAlojamientoById(id);
	}

	@PostMapping
	public Alojamiento saveOrUpdateAlojamiento(@RequestBody Alojamiento alojamiento) {
		alojamientoService.saveOrUpdateAlojamiento(alojamiento);
		return alojamiento;
	}

	@DeleteMapping("/{id}")
	public void deleteAlojamiento(@PathVariable("id") Long id) {
		alojamientoService.deleteAlojamiento(id);
	}

	@GetMapping("/buscar-por-tipo")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<?> buscarPorTipo(@RequestParam("tipo") String tipo) {
		List<Alojamiento> alojamientos = alojamientoService.buscarPorTipo(tipo);
		if (alojamientos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay alojamientos de tipo '" + tipo + "'.");
		} else {
			return ResponseEntity.ok(alojamientos);
		}
	}

	@GetMapping("/ordenar-por-precio")
	public ResponseEntity<?> obtenerAlojamientosOrdenadosPorPrecio(@RequestParam("orden") String orden) {
		List<Alojamiento> alojamientos = alojamientoService.obtenerAlojamientosOrdenadosPorPrecio(orden);
		if (alojamientos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron alojamientos.");
		} else {
			return ResponseEntity.ok(alojamientos);
		}

	}

	@GetMapping("/buscar-por-capacidad-minima")
	public ResponseEntity<?> buscarPorCapacidadMinima(@RequestParam("capacidadMinima") Integer capacidadMinima) {
		if (capacidadMinima == null || capacidadMinima <= 0) {
			return ResponseEntity.badRequest()
					.body("El parámetro 'capacidadMinima' es obligatorio y debe ser un número entero mayor que cero.");
		}

		List<Alojamiento> alojamientos = alojamientoService.buscarPorCapacidadMinima(capacidadMinima);
		if (alojamientos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron alojamientos con capacidad mínima de " + capacidadMinima);
		} else {
			return ResponseEntity.ok(alojamientos);
		}

	}

	@GetMapping("/buscar-por-valoracion")
	public ResponseEntity<?> buscarPorValoracion(@RequestParam("valoracionMinima") Double valoracionMinima) {
		if (valoracionMinima == null || valoracionMinima < 0 || valoracionMinima > 5) {
			return ResponseEntity.badRequest().body("La valoración mínima debe ser un número entre 0 y 5.");
		}

		List<Alojamiento> alojamientos = alojamientoService.buscarPorValoracion(valoracionMinima);
		if (alojamientos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron alojamientos con una valoración mínima de " + valoracionMinima);
		} else {
			return ResponseEntity.ok(alojamientos);
		}

	}

	@GetMapping("/buscar-por-servicio")
	public ResponseEntity<?> buscarPorServicio(@RequestParam("servicio") String servicio) {
		List<Alojamiento> alojamientos = alojamientoService.buscarPorServicio(servicio);
		if (alojamientos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron alojamientos que ofrezcan el servicio '" + servicio + "'.");
		} else {
			return ResponseEntity.ok(alojamientos);
		}
	}

	@GetMapping("/buscar-por-prestacion")
	public ResponseEntity<?> buscarPorPrestacion(@RequestParam("prestacion") String prestacion) {
		List<Alojamiento> alojamientos = alojamientoService.buscarPorPrestacion(prestacion);
		if (alojamientos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron alojamientos con la prestación '" + prestacion + "'.");
		} else {
			return ResponseEntity.ok(alojamientos);
		}

	}

	@GetMapping("/buscar-por-precio")
	public ResponseEntity<?> buscarPorRangoDePrecio(@RequestParam("minPrecio") double minPrecio,
			@RequestParam("maxPrecio") double maxPrecio) {

		if (minPrecio < 0 || maxPrecio > 300 || minPrecio > maxPrecio) {
			return ResponseEntity.badRequest().body("El rango de precios es inválido.");
		}

		List<Alojamiento> alojamientos = alojamientoService.buscarPorRangoDePrecio(minPrecio, maxPrecio);
		if (alojamientos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron alojamientos dentro del rango de precios especificado.");
		} else {
			return ResponseEntity.ok(alojamientos);
		}

	}

	@GetMapping("/casas-rurales")
	public List<Alojamiento> getCasasRurales() {
		return alojamientoService.findCasasRurales();
	}

	@GetMapping("/hoteles")
	public List<Alojamiento> getHoteles() {
		return alojamientoService.findHoteles();
	}

	@GetMapping("/apartamentos")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<Alojamiento> getApartamentos() {
		return alojamientoService.findApartamentos();
	}

	@GetMapping("/buscar-con-filtros")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<?> buscarAlojamientosConFiltros(@RequestParam("tipo") String tipo,
			@RequestParam(value = "precioMin", required = false, defaultValue = "0") double precioMin,
			@RequestParam(value = "precioMax", required = false, defaultValue = "300") double precioMax,
			@RequestParam(value = "servicio", required = false) String servicio,
			@RequestParam(value = "prestacion", required = false) String prestacion) {
		List<Alojamiento> alojamientos = alojamientoService.buscarAlojamientosConFiltros(tipo, precioMin, precioMax,
				servicio, prestacion);
		if (alojamientos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron alojamientos según los filtros proporcionados.");
		} else {
			return ResponseEntity.ok(alojamientos);
		}
	}

	@PostMapping("/buscar")
	public ResponseEntity<?> buscarAlojamientosDisponibles(@RequestBody BusquedaAlojamientoDTO busquedaAlojamientoDTO) {
		try {
			// Llamar al servicio para buscar alojamientos disponibles
			List<Alojamiento> alojamientosDisponibles = alojamientoService.buscarAlojamientosDisponibles(
					busquedaAlojamientoDTO.getLocalizacion(), busquedaAlojamientoDTO.getFechaEntrada(),
					busquedaAlojamientoDTO.getFechaSalida(), busquedaAlojamientoDTO.getNumPersonas());

			// Retornar la lista de alojamientos disponibles en la respuesta
			return ResponseEntity.ok(alojamientosDisponibles);
		} catch (DateTimeParseException e) {
			// Manejar errores de parseo de fechas
			String errorMessage = "Las fechas proporcionadas no tienen el formato correcto.";
			return ResponseEntity.badRequest().body(errorMessage);
		} catch (Exception e) {
			// Manejar otros errores
			String errorMessage = "Ocurrió un error al buscar los alojamientos disponibles.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	@GetMapping("/buscar-todo-por-localizacion")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<?> buscarTodoPorLocalizacion(@RequestParam("localizacion") String localizacion) {
		try {
			List<Alojamiento> alojamientos = alojamientoService.buscarPorLocalizacion(localizacion);
			List<Actividad> actividades = actividadService.buscarPorLocalizacion(localizacion);

			Map<String, Object> resultado = new HashMap<>();
			resultado.put("alojamientos", alojamientos);
			resultado.put("actividades", actividades);

			return ResponseEntity.ok(resultado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al buscar actividades y alojamientos por localización.");
		}
	}

	@GetMapping("/entidadesPorLocalizacion")
	@CrossOrigin(origins = "", allowedHeaders = "")
	public ResponseEntity<List<Object>> obtenerEntidadesPorLocalizacion(@RequestParam String localizacion,
			@RequestParam int capacidadNecesaria) {
		// Buscar alojamientos por localización
		List<Alojamiento> alojamientos = alojamientoService.buscarAlojamientosPorLocalizacion(localizacion);
		// Filtrar alojamientos según la capacidad disponible
		List<Alojamiento> alojamientosDisponibles = filtrarAlojamientosPorCapacidad(alojamientos, capacidadNecesaria);

		// Buscar actividades por localización
		List<Actividad> actividades = actividadService.buscarActividadesPorLocalizacion(localizacion);
		// Filtrar actividades según las plazas disponibles
		List<Actividad> actividadesDisponibles = filtrarActividadesPorPlazasDisponibles(actividades,
				capacidadNecesaria);

		// Combinar resultados de alojamientos y actividades en una lista de objetos
		List<Object> entidades = combineResults(alojamientosDisponibles, actividadesDisponibles);

		return ResponseEntity.ok(entidades);
	}

	private List<Object> combineResults(List<Alojamiento> alojamientosDisponibles,
			List<Actividad> actividadesDisponibles) {
		List<Object> entidades = new ArrayList<>();
		entidades.addAll(alojamientosDisponibles);
		entidades.addAll(actividadesDisponibles);
		return entidades;
	}

	// Método para filtrar alojamientos por capacidad disponible
	private List<Alojamiento> filtrarAlojamientosPorCapacidad(List<Alojamiento> alojamientos, int capacidadNecesaria) {
		List<Alojamiento> alojamientosDisponibles = new ArrayList<>();
		for (Alojamiento alojamiento : alojamientos) {
			if (alojamiento.getCapacidad() >= capacidadNecesaria) {
				alojamientosDisponibles.add(alojamiento);
			}
		}
		return alojamientosDisponibles;
	}

	// Método para filtrar actividades por plazas disponibles
	private List<Actividad> filtrarActividadesPorPlazasDisponibles(List<Actividad> actividades,
			int capacidadNecesaria) {
		List<Actividad> actividadesDisponibles = new ArrayList<>();
		for (Actividad actividad : actividades) {
			if (actividad.getPlazasdisponibles() >= capacidadNecesaria) {
				actividadesDisponibles.add(actividad);
			}
		}
		return actividadesDisponibles;
	}
}
