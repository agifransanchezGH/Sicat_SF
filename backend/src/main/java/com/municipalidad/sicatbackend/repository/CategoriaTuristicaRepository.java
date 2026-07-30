package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.CategoriaTuristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaTuristicaRepository extends JpaRepository<CategoriaTuristica, Integer> {

    // Método de búsqueda por texto (para la pantalla 2: buscar y listar)
    List<CategoriaTuristica> findByNombreCategoriaContainingIgnoreCase(String texto);
}