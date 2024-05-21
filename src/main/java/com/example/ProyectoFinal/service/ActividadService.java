package com.example.ProyectoFinal.service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal.entity.Actividad;
import com.example.ProyectoFinal.repository.ActividadRepository;

@Service
public class ActividadService {

	@Autowired
	private ActividadRepository actividadRepository;

	public List<Actividad> getAllActividades() {
		return actividadRepository.findAll();
	}

	ActividadService(ActividadRepository actividadRepository) {
		this.actividadRepository = actividadRepository;
	}

	public Optional<Actividad> getActividadById(Long id) {
		return actividadRepository.findById(id);
	}

	public void saveOrUpdateActividad(Actividad actividad) {
		actividadRepository.save(actividad);
	}

	public void deleteActividad(Long id) {
		actividadRepository.deleteById(id);
	}

	public List<Actividad> buscarActividadesPorTipo(String tipo) {
		return actividadRepository.findByTipo(tipo);
	}

//	public List<Actividad> buscarActividadesPorLocalizacion(String localizacion) {
//		return actividadRepository.findByLocalizacion(localizacion);
//	}

	public List<Actividad> buscarActividadesPorDificultad(String dificultad) {
		return actividadRepository.findByDificultad(dificultad);
	}

	public List<Actividad> buscarActividadesPorPrecio(double minPrice, double maxPrice) {
		return actividadRepository.findByPrecioBetween(minPrice, maxPrice);
	}

	public List<Actividad> buscarActividadesPorRangoDeHoras(Time horaInicio, Time horaFin) {
		return actividadRepository.findByHoraBetween(horaInicio, horaFin);
	}

	public List<Actividad> buscarPorLocalizacion(String localizacion) {
		return actividadRepository.findByLocalizacionContainingIgnoreCase(localizacion);
	}

	public String obtenerImagenDeActividad(Long id) {
		Optional<Actividad> actividadOptional = actividadRepository.findById(id);
		if (actividadOptional.isPresent()) {
			Actividad actividad = actividadOptional.get();
			return actividad.getImagen();
		} else {
			return null;
		}
	}

	public String obtenerImagen1DeActividad(Long id) {
		Optional<Actividad> actividadOptional = actividadRepository.findById(id);
		if (actividadOptional.isPresent()) {
			Actividad actividad = actividadOptional.get();
			return actividad.getImagen1();
		} else {
			return null;
		}
	}

	public String obtenerImagen2DeActividad(Long id) {
		Optional<Actividad> actividadOptional = actividadRepository.findById(id);
		if (actividadOptional.isPresent()) {
			Actividad actividad = actividadOptional.get();
			return actividad.getImagen2();
		} else {
			return null;
		}
	}

	public List<Actividad> buscarActividadesConFiltros(String tipo, double precioMin, double precioMax,
			String dificultad) {
		return actividadRepository.buscarActividadesConFiltros(tipo, precioMin, precioMax, dificultad);
	}

	public List<Actividad> buscarActividadesDisponibles(String localizacion, LocalDate fechaEntrada,
			LocalDate fechaSalida) {
		// TODO Auto-generated method stub
		return actividadRepository.findByLocalizacion(localizacion);

	}

	public List<Actividad> buscarActividadesPorLocalizacion(String localizacion) {
		return actividadRepository.findByLocalizacionContainingIgnoreCase(localizacion);
	}

}
