package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Integer> {

    // Todas las subcategorías de una categoría — endpoint principal del dropdown dinámico
    List<Subcategoria> findByIdCatOrderByNombreSubcatAsc(Integer idCat);

    // Búsqueda por texto dentro de una categoría
    List<Subcategoria> findByIdCatAndNombreSubcatContainingIgnoreCase(Integer idCat, String texto);

    // Verificar si ya existe la combinación idCat + nombre (para evitar duplicados)
    boolean existsByIdCatAndNombreSubcat(Integer idCat, String nombreSubcat);
}