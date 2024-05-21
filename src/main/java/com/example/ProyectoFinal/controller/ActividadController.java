package com.example.ProyectoFinal.controller;

import java.sql.Time;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoFinal.entity.Actividad;
import com.example.ProyectoFinal.service.ActividadService;

@RestController
@RequestMapping("actividades/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ActividadController {

	@Autowired
	private ActividadService actividadService;

	@GetMapping

	public List<Actividad> getAllActividades() {
		return actividadService.getAllActividades();
	}

	@GetMapping("/{id}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")

	public Optional<Actividad> getActividadById(@PathVariable("id") Long id) {
		return actividadService.getActividadById(id);
	}

	@PostMapping
	public Actividad saveOrUpdateActividad(@RequestBody Actividad actividad) {
		actividadService.saveOrUpdateActividad(actividad);
		return actividad;
	}

	@DeleteMapping("/{id}")
	public void deleteActividad(@PathVariable("id") Long id) {
		actividadService.deleteActividad(id);
	}

	@GetMapping("/actividades-por-tipo")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public List<Actividad> buscarActividadesPorTipo(@RequestParam String tipo) {
		return actividadService.buscarActividadesPorTipo(tipo);
	}

	@GetMapping("/buscar-por-localizacion")
	@CrossOrigin(origins = "*", allowedHeaders = "*")

	public List<Actividad> buscarActividadesPorLocalizacion(@RequestParam String localizacion) {
		return actividadService.buscarActividadesPorLocalizacion(localizacion);
	}

	@GetMapping("/buscar-por-dificultad")
	@CrossOrigin(origins = "*", allowedHeaders = "*")

	public List<Actividad> buscarActividadesPorDificultad(@RequestParam String dificultad) {
		return actividadService.buscarActividadesPorDificultad(dificultad);
	}

	@GetMapping("/buscar-por-precio")
	@CrossOrigin(origins = "*", allowedHeaders = "*")

	public List<Actividad> buscarActividadesPorPrecio(@RequestParam double minPrice, @RequestParam double maxPrice) {
		return actividadService.buscarActividadesPorPrecio(minPrice, maxPrice);
	}

	@GetMapping("/buscar-por-rango-de-horas")
	public List<Actividad> buscarActividadesPorRangoDeHoras(@RequestParam Time horaInicio, @RequestParam Time horaFin) {
		return actividadService.buscarActividadesPorRangoDeHoras(horaInicio, horaFin);
	}

	@GetMapping("/buscar-con-filtros")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<?> buscarActividadesConFiltros(@RequestParam(value = "tipo", required = false) String tipo,
			@RequestParam(value = "precioMin", required = false, defaultValue = "0") double precioMin,
			@RequestParam(value = "precioMax", required = false, defaultValue = "300") double precioMax,
			@RequestParam(value = "dificultad", required = false) String dificultad) {
		List<Actividad> actividades = actividadService.buscarActividadesConFiltros(tipo, precioMin, precioMax,
				dificultad);
		if (actividades.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron actividades según los filtros proporcionados.");
		} else {
			return ResponseEntity.ok(actividades);
		}

	}

}