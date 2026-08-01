package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.ServicioTuristico;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import com.municipalidad.sicatbackend.repository.ServicioTuristicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioTuristicoService {

    private final ServicioTuristicoRepository repository;
    private final EstablecimientoRepository establecimientoRepository;

    @Autowired
    public ServicioTuristicoService(ServicioTuristicoRepository repository,
                                   EstablecimientoRepository establecimientoRepository) {
        this.repository = repository;
        this.establecimientoRepository = establecimientoRepository;
    }

    public ServicioTuristico guardar(ServicioTuristico servicio) {
        String idEstab = servicio.getIdEstab();

        if (idEstab == null || idEstab.trim().isEmpty()) {
            throw new RuntimeException("El ID de establecimiento es obligatorio.");
        }

        establecimientoRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "Establecimiento no encontrado: " + idEstab));

        if (repository.existsById(idEstab)) {
            throw new RuntimeException("Ya existe un registro de servicio turístico para: " + idEstab);
        }

        return repository.save(servicio);
    }

    public ServicioTuristico actualizar(String idEstab, ServicioTuristico datos) {
        ServicioTuristico existente = repository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "No existe registro de servicio turístico para: " + idEstab));

        if (datos.getSubcategoriaServicio() != null) existente.setSubcategoriaServicio(datos.getSubcategoriaServicio());
        if (datos.getNombreServicio() != null) existente.setNombreServicio(datos.getNombreServicio());
        if (datos.getTipoServicio() != null) existente.setTipoServicio(datos.getTipoServicio());
        if (datos.getContacto() != null) existente.setContacto(datos.getContacto());
        if (datos.getLinkReserva() != null) existente.setLinkReserva(datos.getLinkReserva());
        if (datos.getDescripcion() != null) existente.setDescripcion(datos.getDescripcion());
        if (datos.getVehiculoTipo() != null) existente.setVehiculoTipo(datos.getVehiculoTipo());
        if (datos.getZonaCobertura() != null) existente.setZonaCobertura(datos.getZonaCobertura());
        if (datos.getHorarioServicio() != null) existente.setHorarioServicio(datos.getHorarioServicio());
        if (datos.getLineas() != null) existente.setLineas(datos.getLineas());
        if (datos.getFrecuencia() != null) existente.setFrecuencia(datos.getFrecuencia());
        if (datos.getTipoRamal() != null) existente.setTipoRamal(datos.getTipoRamal());
        if (datos.getProgramasOfrecidos() != null) existente.setProgramasOfrecidos(datos.getProgramasOfrecidos());
        if (datos.getAcreditaciones() != null) existente.setAcreditaciones(datos.getAcreditaciones());
        if (datos.getCantidadAsesores() != null) existente.setCantidadAsesores(datos.getCantidadAsesores());

        return repository.save(existente);
    }

    public Optional<ServicioTuristico> buscarPorId(String idEstab) {
        return repository.findById(idEstab);
    }

    public List<ServicioTuristico> listarTodos() {
        return repository.findAll();
    }

    public List<ServicioTuristico> buscarPorSubcategoria(String subcategoria) {
        return repository.findBySubcategoriaServicio(subcategoria);
    }

    public List<ServicioTuristico> buscarPorTipo(String tipoServicio) {
        return repository.findByTipoServicio(tipoServicio);
    }

    public List<ServicioTuristico> buscarPorVehiculo(String vehiculoTipo) {
        return repository.findByVehiculoTipo(vehiculoTipo);
    }
}
