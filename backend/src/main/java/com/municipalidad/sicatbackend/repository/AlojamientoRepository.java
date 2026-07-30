package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.Alojamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlojamientoRepository extends JpaRepository<Alojamiento, String> {

    // Buscar por tipo de alojamiento (ej: "Hotel 3 estrellas")
    List<Alojamiento> findByTipoAlojamiento(String tipoAlojamiento);

    // Buscar alojamientos con capacidad mínima de plazas
    List<Alojamiento> findByTotalPlazasGreaterThanEqual(Integer plazas);

    // Buscar alojamientos con al menos una habitación accesible
    List<Alojamiento> findByCantHabAccesiblesGreaterThan(Short cantidad);

    // Buscar alojamientos que contengan un servicio específico (campo pipe-separated)
    @Query(value = """
            SELECT * FROM sicat.alojamiento
            WHERE servicios_generales ILIKE '%' || :servicio || '%'
            """, nativeQuery = true)
    List<Alojamiento> findByServicio(@Param("servicio") String servicio);
}
