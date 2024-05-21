package com.example.ProyectoFinal.repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ProyectoFinal.entity.Actividad; // Asegúrate de ajustar el paquete y la clase de la entidad Actividad según tu estructura de paquetes y nombres de clases
import com.example.ProyectoFinal.entity.Usuario;
import com.example.ProyectoFinal.entity.dto.ActividadDTO;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

	List<Actividad> findByTipo(String tipo);

	List<Actividad> findByLocalizacion(String localizacion);

	List<Actividad> findByDificultad(String dificultad);

	List<Actividad> findByPrecioBetween(double minPrice, double maxPrice);

	List<Actividad> findByHoraBetween(Time horaInicio, Time horaFin);

	List<Actividad> findByLocalizacionContainingIgnoreCase(String localizacion);

	@Query("SELECT a FROM Actividad a " + "WHERE (:tipo IS NULL OR a.tipo = :tipo) "
			+ "AND (:precioMin IS NULL OR a.precio >= :precioMin) "
			+ "AND (:precioMax IS NULL OR a.precio <= :precioMax) "
			+ "AND (:dificultad IS NULL OR a.dificultad = :dificultad)")
	List<Actividad> buscarActividadesConFiltros(@Param("tipo") String tipo,
			@Param("precioMin") Double precioMin, @Param("precioMax") Double precioMax,
			@Param("dificultad") String dificultad);

}
