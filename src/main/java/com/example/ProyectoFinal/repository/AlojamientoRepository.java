package com.example.ProyectoFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ProyectoFinal.entity.Alojamiento;

@Repository
public interface AlojamientoRepository extends JpaRepository<Alojamiento, Long> {
	List<Alojamiento> findByLocalizacionContainingIgnoreCase(String localizacion);

	List<Alojamiento> findByTipoContainingIgnoreCase(String tipo);

	List<Alojamiento> findAllByOrderByPrecioAsc();

	List<Alojamiento> findAllByOrderByPrecioDesc();

	List<Alojamiento> findByCapacidadGreaterThanEqual(int capacidad);

	List<Alojamiento> findByValoracionGreaterThanEqual(double valoracionMinima);

	List<Alojamiento> findByServiciosContainingIgnoreCase(String servicio);

	List<Alojamiento> findByPrestacionesContainingIgnoreCase(String prestacion);

	List<Alojamiento> findByLocalizacionStartingWithIgnoreCase(String letraInicial);

	List<Alojamiento> findByPrecioBetween(double minPrecio, double maxPrecio);

	@Query("SELECT a FROM Alojamiento a WHERE a.tipo = 'Casa rural'")
	List<Alojamiento> findCasasRurales();

	@Query("SELECT a FROM Alojamiento a WHERE a.tipo = 'Hotel'")
	List<Alojamiento> findHoteles();

	@Query("SELECT a FROM Alojamiento a WHERE a.tipo = 'Apartamento'")
	List<Alojamiento> findApartamentos();

	@Query("SELECT a FROM Alojamiento a WHERE a.tipo = 'Camping'")
	List<Alojamiento> findCampings();

	@Query("SELECT a FROM Alojamiento a " + "WHERE a.tipo = :tipo " + "AND a.precio BETWEEN :precioMin AND :precioMax "
			+ "AND (:servicio IS NULL OR a.servicios LIKE CONCAT('%', :servicio, '%')) "
			+ "AND (:prestacion IS NULL OR a.prestaciones LIKE CONCAT('%', :prestacion, '%'))")
	List<Alojamiento> buscarAlojamientosConFiltros(@Param("tipo") String tipo, @Param("precioMin") double precioMin,
			@Param("precioMax") double precioMax, @Param("servicio") String servicio,
			@Param("prestacion") String prestacion);
}
