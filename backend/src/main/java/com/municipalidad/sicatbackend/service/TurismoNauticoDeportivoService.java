package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.TurismoNauticoDeportivo;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import com.municipalidad.sicatbackend.repository.TurismoNauticoDeportivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurismoNauticoDeportivoService {

    private final TurismoNauticoDeportivoRepository repository;
    private final EstablecimientoRepository establecimientoRepository;

    @Autowired
    public TurismoNauticoDeportivoService(TurismoNauticoDeportivoRepository repository,
                                          EstablecimientoRepository establecimientoRepository) {
        this.repository = repository;
        this.establecimientoRepository = establecimientoRepository;
    }

    public TurismoNauticoDeportivo guardar(TurismoNauticoDeportivo turismo) {
        String idEstab = turismo.getIdEstab();

        if (idEstab == null || idEstab.trim().isEmpty()) {
            throw new RuntimeException("El ID de establecimiento es obligatorio.");
        }

        establecimientoRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "Establecimiento no encontrado: " + idEstab));

        if (repository.existsById(idEstab)) {
            throw new RuntimeException("Ya existe un registro de turismo náutico deportivo para: " + idEstab);
        }

        return repository.save(turismo);
    }

    public TurismoNauticoDeportivo actualizar(String idEstab, TurismoNauticoDeportivo datos) {
        TurismoNauticoDeportivo existente = repository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "No existe registro de turismo náutico deportivo para: " + idEstab));

        if (datos.getSubcategoriaNautica() != null) existente.setSubcategoriaNautica(datos.getSubcategoriaNautica());
        if (datos.getTipoNautico() != null) existente.setTipoNautico(datos.getTipoNautico());
        if (datos.getPuertoBase() != null) existente.setPuertoBase(datos.getPuertoBase());
        if (datos.getEmbarcacionesCantidad() != null) existente.setEmbarcacionesCantidad(datos.getEmbarcacionesCantidad());
        if (datos.getEmbarcacionesTipo() != null) existente.setEmbarcacionesTipo(datos.getEmbarcacionesTipo());
        if (datos.getServiciosAdicionales() != null) existente.setServiciosAdicionales(datos.getServiciosAdicionales());
        if (datos.getTemporadaOperativa() != null) existente.setTemporadaOperativa(datos.getTemporadaOperativa());
        if (datos.getSalidaConGuia() != null) existente.setSalidaConGuia(datos.getSalidaConGuia());
        if (datos.getDeportesAcuaticos() != null) existente.setDeportesAcuaticos(datos.getDeportesAcuaticos());
        if (datos.getEscuelaNautica() != null) existente.setEscuelaNautica(datos.getEscuelaNautica());
        if (datos.getNumeroAlumnos() != null) existente.setNumeroAlumnos(datos.getNumeroAlumnos());
        if (datos.getZonaProtegida() != null) existente.setZonaProtegida(datos.getZonaProtegida());
        if (datos.getHabilitaciones() != null) existente.setHabilitaciones(datos.getHabilitaciones());
        if (datos.getDescripcion() != null) existente.setDescripcion(datos.getDescripcion());
        if (datos.getObservaciones() != null) existente.setObservaciones(datos.getObservaciones());

        return repository.save(existente);
    }

    public Optional<TurismoNauticoDeportivo> buscarPorId(String idEstab) {
        return repository.findById(idEstab);
    }

    public List<TurismoNauticoDeportivo> listarTodos() {
        return repository.findAll();
    }

    public List<TurismoNauticoDeportivo> buscarPorSubcategoria(String subcategoriaNautica) {
        return repository.findBySubcategoriaNautica(subcategoriaNautica);
    }

    public List<TurismoNauticoDeportivo> buscarPorTipo(String tipoNautico) {
        return repository.findByTipoNautico(tipoNautico);
    }

    public List<TurismoNauticoDeportivo> buscarPorPuerto(String puertoBase) {
        return repository.findByPuertoBase(puertoBase);
    }
}
