package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.SalaEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaEventoRepository extends JpaRepository<SalaEvento, String> {
    List<SalaEvento> findByIdEstabPadre(String idEstabPadre);
    List<SalaEvento> findByTipoSala(String tipoSala);
}
