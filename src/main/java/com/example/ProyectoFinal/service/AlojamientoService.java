package com.example.ProyectoFinal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProyectoFinal.entity.Alojamiento;
import com.example.ProyectoFinal.repository.AlojamientoRepository;

@Service
@Transactional
public class AlojamientoService {

	private final AlojamientoRepository alojamientoRepository;

	public AlojamientoService(AlojamientoRepository alojamientoRepository) {
		this.alojamientoRepository = alojamientoRepository;
	}

	public List<Alojamiento> getAllAlojamientos() {
		List<Alojamiento> alojamientos = alojamientoRepository.findAll();

		// Actualizar el campo habsDisponibles basado en el valor de numHabs
		for (Alojamiento alojamiento : alojamientos) {
			if (alojamiento.getNumhabs() == 0) {
				alojamiento.setHabsdisponibles(false);
			}
		}

		return alojamientos;
	}

	public Optional<Alojamiento> getAlojamientoById(Long id) {
		return alojamientoRepository.findById(id);
	}

	public void saveOrUpdateAlojamiento(Alojamiento alojamiento) {
		alojamientoRepository.save(alojamiento);
	}

	public void deleteAlojamiento(Long id) {
		alojamientoRepository.deleteById(id);
	}

	public List<Alojamiento> buscarPorLocalizacion(String localizacion) {
		return alojamientoRepository.findByLocalizacionContainingIgnoreCase(localizacion);
	}

	public List<Alojamiento> buscarPorTipo(String tipo) {
		return alojamientoRepository.findByTipoContainingIgnoreCase(tipo);
	}

	public List<Alojamiento> obtenerAlojamientosOrdenadosPorPrecio(String orden) {
		if (orden.equalsIgnoreCase("asc")) {
			return alojamientoRepository.findAllByOrderByPrecioAsc();
		} else {
			return alojamientoRepository.findAllByOrderByPrecioDesc();
		}

	}

	public List<Alojamiento> buscarPorCapacidadMinima(int capacidad) {
		return alojamientoRepository.findByCapacidadGreaterThanEqual(capacidad);
	}

	public List<Alojamiento> buscarPorValoracion(double valoracionMinima) {
		return alojamientoRepository.findByValoracionGreaterThanEqual(valoracionMinima);
	}

	public List<Alojamiento> buscarPorServicio(String servicio) {
		return alojamientoRepository.findByServiciosContainingIgnoreCase(servicio);
	}

	public List<Alojamiento> buscarPorPrestacion(String prestacion) {
		return alojamientoRepository.findByPrestacionesContainingIgnoreCase(prestacion);
	}

	public List<Alojamiento> buscarPorRangoDePrecio(double minPrecio, double maxPrecio) {
		return alojamientoRepository.findByPrecioBetween(minPrecio, maxPrecio);
	}

	public List<Alojamiento> findCasasRurales() {
		return alojamientoRepository.findCasasRurales();
	}

	public List<Alojamiento> findHoteles() {
		return alojamientoRepository.findHoteles();
	}

	public List<Alojamiento> findApartamentos() {
		return alojamientoRepository.findApartamentos();
	}

	public List<Alojamiento> findCampings() {
		return alojamientoRepository.findCampings();
	}

	public List<Alojamiento> buscarAlojamientosConFiltros(String tipo, double precioMin, double precioMax,
			String servicio, String prestacion) {
		return alojamientoRepository.buscarAlojamientosConFiltros(tipo, precioMin, precioMax, servicio, prestacion);
	}

	public String obtenerImagenDeAlojamiento(Long id) {
		Optional<Alojamiento> alojamientoOptional = alojamientoRepository.findById(id);
		if (alojamientoOptional.isPresent()) {
			Alojamiento alojamiento = alojamientoOptional.get();
			return alojamiento.getImagen(); // Devuelve la imagen como un array de bytes
		} else {
			return null;
		}
	}

	public String obtenerImagen1DeAlojamiento(Long id) {
		Optional<Alojamiento> alojamientoOptional = alojamientoRepository.findById(id);
		if (alojamientoOptional.isPresent()) {
			Alojamiento alojamiento = alojamientoOptional.get();
			return alojamiento.getImagen1(); // Devuelve la imagen como un array de bytes
		} else {
			return null;
		}
	}

	public String obtenerImagen2DeAlojamiento(Long id) {
		Optional<Alojamiento> alojamientoOptional = alojamientoRepository.findById(id);
		if (alojamientoOptional.isPresent()) {
			Alojamiento alojamiento = alojamientoOptional.get();
			return alojamiento.getImagen2(); // Devuelve la imagen como un array de bytes
		} else {
			return null;
		}
	}

	public String obtenerDescripcionLarga(Long id) {
		Optional<Alojamiento> alojamientoOptional = alojamientoRepository.findById(id);
		if (alojamientoOptional.isPresent()) {
			Alojamiento alojamiento = alojamientoOptional.get();
			return alojamiento.getDescripcionlarga(); // Devuelve la imagen como un array de bytes
		} else {
			return null;
		}
	}

	public Object obtenerLavabos(Long id) {
		Optional<Alojamiento> alojamientoOptional = alojamientoRepository.findById(id);
		if (alojamientoOptional.isPresent()) {
			Alojamiento alojamiento = alojamientoOptional.get();
			return alojamiento.getLavabos(); // Devuelve la imagen como un array de bytes
		} else {
			return null;
		}
	}

	public Object obtenerRegion(Long id) {
		Optional<Alojamiento> alojamientoOptional = alojamientoRepository.findById(id);
		if (alojamientoOptional.isPresent()) {
			Alojamiento alojamiento = alojamientoOptional.get();
			return alojamiento.getRegion(); // Devuelve la imagen como un array de bytes
		} else {
			return null;
		}
	}

	public List<Alojamiento> buscarAlojamientosDisponibles(String localizacion, LocalDate fechaEntrada,
			LocalDate fechaSalida, int numPersonas) {
		List<Alojamiento> alojamientosDisponibles = new ArrayList<>();

		// Obtener todos los alojamientos disponibles en la localización especificada
		List<Alojamiento> alojamientosEnLocalizacion = alojamientoRepository
				.findByLocalizacionContainingIgnoreCase(localizacion);

		// Filtrar los alojamientos según la capacidad disponible
		for (Alojamiento alojamiento : alojamientosEnLocalizacion) {
			if (alojamiento.getCapacidad() >= numPersonas) {
				alojamientosDisponibles.add(alojamiento);
			}
		}

		return alojamientosDisponibles;
	}

	public List<Alojamiento> buscarAlojamientosPorLocalizacion(String localizacion) {
		return alojamientoRepository.findByLocalizacionContainingIgnoreCase(localizacion);
	}
}
