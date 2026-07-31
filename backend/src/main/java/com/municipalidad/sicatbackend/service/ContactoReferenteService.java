package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.ContactoReferente;
import com.municipalidad.sicatbackend.repository.ContactoReferenteRepository;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoReferenteService {

    private final ContactoReferenteRepository contactoRepository;
    private final EstablecimientoRepository establecimientoRepository;

    @Autowired
    public ContactoReferenteService(ContactoReferenteRepository contactoRepository,
                                    EstablecimientoRepository establecimientoRepository) {
        this.contactoRepository = contactoRepository;
        this.establecimientoRepository = establecimientoRepository;
    }

    public ContactoReferente guardar(ContactoReferente contacto) {
        // Validar que el establecimiento existe antes de asociar el contacto
        establecimientoRepository.findById(contacto.getIdEstab())
                .orElseThrow(() -> new RuntimeException(
                        "Establecimiento no encontrado: " + contacto.getIdEstab()));

        return contactoRepository.save(contacto);
    }

    public ContactoReferente actualizar(Integer idRef, ContactoReferente datos) {
        ContactoReferente existente = contactoRepository.findById(idRef)
                .orElseThrow(() -> new RuntimeException(
                        "Contacto referente no encontrado: " + idRef));

        if (datos.getNombreReferente()   != null) existente.setNombreReferente(datos.getNombreReferente());
        if (datos.getTelefonoReferente() != null) existente.setTelefonoReferente(datos.getTelefonoReferente());
        if (datos.getCorreoReferente()   != null) existente.setCorreoReferente(datos.getCorreoReferente());
        if (datos.getCargo()             != null) existente.setCargo(datos.getCargo());

        return contactoRepository.save(existente);
    }

    public List<ContactoReferente> listarPorEstablecimiento(String idEstab) {
        return contactoRepository.findByIdEstab(idEstab);
    }

    public void eliminar(Integer idRef) {
        if (!contactoRepository.existsById(idRef)) {
            throw new RuntimeException("Contacto referente no encontrado: " + idRef);
        }
        contactoRepository.deleteById(idRef);
    }
}
