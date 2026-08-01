package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.TurismoNauticoDeportivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurismoNauticoDeportivoRepository extends JpaRepository<TurismoNauticoDeportivo, String> {
    List<TurismoNauticoDeportivo> findBySubcategoriaNautica(String subcategoriaNautica);
    List<TurismoNauticoDeportivo> findByTipoNautico(String tipoNautico);
    List<TurismoNauticoDeportivo> findByPuertoBase(String puertoBase);
}
