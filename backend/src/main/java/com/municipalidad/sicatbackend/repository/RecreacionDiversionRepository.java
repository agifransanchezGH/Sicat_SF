package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.RecreacionDiversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecreacionDiversionRepository extends JpaRepository<RecreacionDiversion, String> {
    List<RecreacionDiversion> findBySubcategoriaRecreacion(String subcategoria);
    List<RecreacionDiversion> findByTipoServicio(String tipoServicio);
}
