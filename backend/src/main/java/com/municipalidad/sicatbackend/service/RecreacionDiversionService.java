package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.RecreacionDiversion;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import com.municipalidad.sicatbackend.repository.RecreacionDiversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecreacionDiversionService {

    private final RecreacionDiversionRepository repository;
    private final EstablecimientoRepository establecimientoRepository;

    @Autowired
    public RecreacionDiversionService(RecreacionDiversionRepository repository,
                                      EstablecimientoRepository establecimientoRepository) {
        this.repository = repository;
        this.establecimientoRepository = establecimientoRepository;
    }

    public RecreacionDiversion guardar(RecreacionDiversion recreacionDiversion) {
        String idEstab = recreacionDiversion.getIdEstab();

        if (idEstab == null || idEstab.trim().isEmpty()) {
            throw new RuntimeException("El ID de establecimiento es obligatorio.");
        }

        establecimientoRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "Establecimiento no encontrado: " + idEstab));

        if (repository.existsById(idEstab)) {
            throw new RuntimeException("Ya existe un registro de recreación/ diversión para: " + idEstab);
        }

        return repository.save(recreacionDiversion);
    }

    public RecreacionDiversion actualizar(String idEstab, RecreacionDiversion datos) {
        RecreacionDiversion existente = repository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "No existe registro de recreación/diversión para: " + idEstab));

        if (datos.getSubcategoriaRecreacion() != null) existente.setSubcategoriaRecreacion(datos.getSubcategoriaRecreacion());
        if (datos.getTipoServicio() != null) existente.setTipoServicio(datos.getTipoServicio());
        if (datos.getCapacidadPersonas() != null) existente.setCapacidadPersonas(datos.getCapacidadPersonas());
        if (datos.getAccesoAdaptado() != null) existente.setAccesoAdaptado(datos.getAccesoAdaptado());
        if (datos.getCantidadPiscinas() != null) existente.setCantidadPiscinas(datos.getCantidadPiscinas());
        if (datos.getAcuaticoCubierto() != null) existente.setAcuaticoCubierto(datos.getAcuaticoCubierto());
        if (datos.getCantidadCancha() != null) existente.setCantidadCancha(datos.getCantidadCancha());
        if (datos.getTipoInstalacionesDeportivas() != null) existente.setTipoInstalacionesDeportivas(datos.getTipoInstalacionesDeportivas());
        if (datos.getCantidadSalasJuegos() != null) existente.setCantidadSalasJuegos(datos.getCantidadSalasJuegos());
        if (datos.getTieneSalaEscape() != null) existente.setTieneSalaEscape(datos.getTieneSalaEscape());
        if (datos.getObservaciones() != null) existente.setObservaciones(datos.getObservaciones());

        return repository.save(existente);
    }

    public Optional<RecreacionDiversion> buscarPorId(String idEstab) {
        return repository.findById(idEstab);
    }

    public List<RecreacionDiversion> listarTodos() {
        return repository.findAll();
    }

    public List<RecreacionDiversion> buscarPorSubcategoria(String subcategoria) {
        return repository.findBySubcategoriaRecreacion(subcategoria);
    }

    public List<RecreacionDiversion> buscarPorTipoServicio(String tipoServicio) {
        return repository.findByTipoServicio(tipoServicio);
    }
}
