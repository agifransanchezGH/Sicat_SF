package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.ContactoReferente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoReferenteRepository extends JpaRepository<ContactoReferente, Integer> {

    // Todos los contactos de un establecimiento
    List<ContactoReferente> findByIdEstab(String idEstab);

    // Buscar por nombre del referente
    List<ContactoReferente> findByNombreReferenteContainingIgnoreCase(String nombre);
}
