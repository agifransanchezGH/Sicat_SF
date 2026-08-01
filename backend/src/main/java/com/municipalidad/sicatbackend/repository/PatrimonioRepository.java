package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.Patrimonio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, String> {
    List<Patrimonio> findBySubcategoriaPatrimonio(String subcategoria);
    List<Patrimonio> findByTipoPatrimonio(String tipoPatrimonio);
}
