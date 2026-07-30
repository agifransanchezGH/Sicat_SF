package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.Establecimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablecimientoRepository extends JpaRepository<Establecimiento, String> {

    List<Establecimiento> findByNombreContainingIgnoreCase(String nombre);

    List<Establecimiento> findByIdCat(Integer idCat);
}
