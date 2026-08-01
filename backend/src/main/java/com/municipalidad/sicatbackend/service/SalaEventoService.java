package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.SalaEvento;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import com.municipalidad.sicatbackend.repository.SalaEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaEventoService {

    private final SalaEventoRepository salaEventoRepository;
    private final EstablecimientoRepository establecimientoRepository;

    @Autowired
    public SalaEventoService(SalaEventoRepository salaEventoRepository,
                             EstablecimientoRepository establecimientoRepository) {
        this.salaEventoRepository = salaEventoRepository;
        this.establecimientoRepository = establecimientoRepository;
    }

    public SalaEvento guardar(SalaEvento salaEvento) {
        String idEstab = salaEvento.getIdEstab();
        String idEstabPadre = salaEvento.getIdEstabPadre();

        if (idEstab == null || idEstab.trim().isEmpty()) {
            throw new RuntimeException("El ID de sala evento es obligatorio.");
        }

        establecimientoRepository.findById(idEstab)
                .ifPresent(existing -> {
                    throw new RuntimeException("Ya existe una sala evento con ese ID: " + idEstab);
                });

        if (idEstabPadre != null && !idEstabPadre.trim().isEmpty()) {
            establecimientoRepository.findById(idEstabPadre)
                    .orElseThrow(() -> new RuntimeException(
                            "El establecimiento padre no existe: " + idEstabPadre));
        }

        return salaEventoRepository.save(salaEvento);
    }

    public SalaEvento actualizar(String idEstab, SalaEvento datos) {
        SalaEvento existente = salaEventoRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "No existe sala evento para: " + idEstab));

        if (datos.getIdEstabPadre() != null) existente.setIdEstabPadre(datos.getIdEstabPadre());
        if (datos.getNombreSala() != null) existente.setNombreSala(datos.getNombreSala());
        if (datos.getCapacidadPersonas() != null) existente.setCapacidadPersonas(datos.getCapacidadPersonas());
        if (datos.getSuperficieM2() != null) existente.setSuperficieM2(datos.getSuperficieM2());
        if (datos.getTipoSala() != null) existente.setTipoSala(datos.getTipoSala());
        if (datos.getTieneAudio() != null) existente.setTieneAudio(datos.getTieneAudio());
        if (datos.getObservaciones() != null) existente.setObservaciones(datos.getObservaciones());

        return salaEventoRepository.save(existente);
    }

    public Optional<SalaEvento> buscarPorId(String idEstab) {
        return salaEventoRepository.findById(idEstab);
    }

    public List<SalaEvento> listarTodos() {
        return salaEventoRepository.findAll();
    }

    public List<SalaEvento> buscarPorEstablecimientoPadre(String idEstabPadre) {
        return salaEventoRepository.findByIdEstabPadre(idEstabPadre);
    }

    public List<SalaEvento> buscarPorTipoSala(String tipoSala) {
        return salaEventoRepository.findByTipoSala(tipoSala);
    }
}
