package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.MuseoSalaCultural;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuseoSalaCulturalRepository extends JpaRepository<MuseoSalaCultural, String> {

    List<MuseoSalaCultural> findByDominio(String dominio);

    List<MuseoSalaCultural> findByFuncionamiento(String funcionamiento);

    List<MuseoSalaCultural> findByTipoEntrada(String tipoEntrada);

    List<MuseoSalaCultural> findBySubcategoriaMuseoContainingIgnoreCase(String subcategoria);
}