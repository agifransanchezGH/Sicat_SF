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

        if (datos.getSubcategoriaNau() != null) existente.setSubcategoriaNau(datos.getSubcategoriaNau());
        if (datos.getFuncionamiento() != null) existente.setFuncionamiento(datos.getFuncionamiento());
        if (datos.getDeportes() != null) existente.setDeportes(datos.getDeportes());
        if (datos.getInstalaciones() != null) existente.setInstalaciones(datos.getInstalaciones());
        if (datos.getServiciosClub() != null) existente.setServiciosClub(datos.getServiciosClub());
        if (datos.getRequisitosAdmision() != null) existente.setRequisitosAdmision(datos.getRequisitosAdmision());
        if (datos.getDeportesNauticos() != null) existente.setDeportesNauticos(datos.getDeportesNauticos());
        if (datos.getCantEmbarcaciones() != null) existente.setCantEmbarcaciones(datos.getCantEmbarcaciones());
        if (datos.getServiciosNauticos() != null) existente.setServiciosNauticos(datos.getServiciosNauticos());
        if (datos.getServiciosActivNau() != null) existente.setServiciosActivNau(datos.getServiciosActivNau());
        if (datos.getCantEquipoNautico() != null) existente.setCantEquipoNautico(datos.getCantEquipoNautico());
        if (datos.getServiciosGuarderia() != null) existente.setServiciosGuarderia(datos.getServiciosGuarderia());
        if (datos.getActividadesRecreacion() != null) existente.setActividadesRecreacion(datos.getActividadesRecreacion());
        if (datos.getCantAmarres() != null) existente.setCantAmarres(datos.getCantAmarres());
        if (datos.getServiciosParador() != null) existente.setServiciosParador(datos.getServiciosParador());
        if (datos.getAccesoPlaya() != null) existente.setAccesoPlaya(datos.getAccesoPlaya());
        if (datos.getTemporada() != null) existente.setTemporada(datos.getTemporada());
        if (datos.getProductosPesca() != null) existente.setProductosPesca(datos.getProductosPesca());
        if (datos.getTipoPesca() != null) existente.setTipoPesca(datos.getTipoPesca());

        return repository.save(existente);
    }

    public Optional<TurismoNauticoDeportivo> buscarPorId(String idEstab) {
        return repository.findById(idEstab);
    }

    public List<TurismoNauticoDeportivo> listarTodos() {
        return repository.findAll();
    }

    public List<TurismoNauticoDeportivo> buscarPorSubcategoria(String subcategoriaNau) {
        return repository.findBySubcategoriaNau(subcategoriaNau);
    }

    public List<TurismoNauticoDeportivo> buscarPorFuncionamiento(String funcionamiento) {
        return repository.findByFuncionamiento(funcionamiento);
    }

    public List<TurismoNauticoDeportivo> buscarPorAccesoPlaya(String accesoPlaya) {
        return repository.findByAccesoPlaya(accesoPlaya);
    }
}
