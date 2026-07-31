package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.Relevamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RelevamientoRepository extends JpaRepository<Relevamiento, Integer> {

    // Historial completo de un establecimiento ordenado por fecha descendente
    List<Relevamiento> findByIdEstabOrderByFechaRelevamientoDesc(String idEstab);

    // Filtrar por estado de carga
    List<Relevamiento> findByEstadoCarga(String estadoCarga);

    // Relevamientos de un técnico en particular
    List<Relevamiento> findByTecnicoResponsableContainingIgnoreCase(String tecnico);

    // Relevamientos realizados entre dos fechas
    List<Relevamiento> findByFechaRelevamientoBetween(LocalDate desde, LocalDate hasta);
}
