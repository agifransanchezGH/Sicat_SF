package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.ServicioTuristico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioTuristicoRepository extends JpaRepository<ServicioTuristico, String> {
    List<ServicioTuristico> findBySubcategoriaServicio(String subcategoriaServicio);
    List<ServicioTuristico> findByTipoServicio(String tipoServicio);
    List<ServicioTuristico> findByVehiculoTipo(String vehiculoTipo);
}
