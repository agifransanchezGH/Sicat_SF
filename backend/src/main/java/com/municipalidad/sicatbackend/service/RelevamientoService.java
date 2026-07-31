package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.Relevamiento;
import com.municipalidad.sicatbackend.repository.RelevamientoRepository;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RelevamientoService {

    private final RelevamientoRepository relevamientoRepository;
    private final EstablecimientoRepository establecimientoRepository;

    @Autowired
    public RelevamientoService(RelevamientoRepository relevamientoRepository,
                               EstablecimientoRepository establecimientoRepository) {
        this.relevamientoRepository = relevamientoRepository;
        this.establecimientoRepository = establecimientoRepository;
    }

    public Relevamiento registrar(Relevamiento relevamiento) {
        establecimientoRepository.findById(relevamiento.getIdEstab())
                .orElseThrow(() -> new RuntimeException(
                        "Establecimiento no encontrado: " + relevamiento.getIdEstab()));

        if (relevamiento.getFechaRelevamiento() == null) {
            relevamiento.setFechaRelevamiento(LocalDate.now());
        }

        // Default alineado con el CHECK del schema
        if (relevamiento.getEstadoCarga() == null || relevamiento.getEstadoCarga().isBlank()) {
            relevamiento.setEstadoCarga("Completo");
        }

        return relevamientoRepository.save(relevamiento);
    }

    public List<Relevamiento> listarPorEstablecimiento(String idEstab) {
        return relevamientoRepository.findByIdEstabOrderByFechaRelevamientoDesc(idEstab);
    }

    public List<Relevamiento> listarTodos() {
        return relevamientoRepository.findAll();
    }

    public List<Relevamiento> listarPorEstado(String estadoCarga) {
        return relevamientoRepository.findByEstadoCarga(estadoCarga);
    }

    public List<Relevamiento> listarPorTecnico(String tecnico) {
        return relevamientoRepository.findByTecnicoResponsableContainingIgnoreCase(tecnico);
    }
}