package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.Gastronomia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastronomiaRepository extends JpaRepository<Gastronomia, String> {

    List<Gastronomia> findBySubcategoriaGastro(String subcategoria);

    List<Gastronomia> findByZonaCiudad(String zona);

    List<Gastronomia> findByTipoCocinaContainingIgnoreCase(String tipoCocina);
}