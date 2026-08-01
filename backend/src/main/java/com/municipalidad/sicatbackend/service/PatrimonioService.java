package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.Patrimonio;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import com.municipalidad.sicatbackend.repository.PatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatrimonioService {

    private final PatrimonioRepository patrimonioRepository;
    private final EstablecimientoRepository establecimientoRepository;

    @Autowired
    public PatrimonioService(PatrimonioRepository patrimonioRepository,
                             EstablecimientoRepository establecimientoRepository) {
        this.patrimonioRepository = patrimonioRepository;
        this.establecimientoRepository = establecimientoRepository;
    }

    public Patrimonio guardar(Patrimonio patrimonio) {
        String idEstab = patrimonio.getIdEstab();

        if (idEstab == null || idEstab.trim().isEmpty()) {
            throw new RuntimeException("El ID de establecimiento es obligatorio.");
        }

        establecimientoRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "Establecimiento no encontrado: " + idEstab));

        if (patrimonioRepository.existsById(idEstab)) {
            throw new RuntimeException("Ya existe un registro de patrimonio para: " + idEstab);
        }

        return patrimonioRepository.save(patrimonio);
    }

    public Patrimonio actualizar(String idEstab, Patrimonio datos) {
        Patrimonio existente = patrimonioRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "No existe registro de patrimonio para: " + idEstab));

        if (datos.getSubcategoriaPatrimonio() != null) existente.setSubcategoriaPatrimonio(datos.getSubcategoriaPatrimonio());
        if (datos.getTipoPatrimonio() != null) existente.setTipoPatrimonio(datos.getTipoPatrimonio());
        if (datos.getDescripcion() != null) existente.setDescripcion(datos.getDescripcion());
        if (datos.getAccesoPublico() != null) existente.setAccesoPublico(datos.getAccesoPublico());
        if (datos.getHorarioVisitas() != null) existente.setHorarioVisitas(datos.getHorarioVisitas());
        if (datos.getValorCultural() != null) existente.setValorCultural(datos.getValorCultural());
        if (datos.getEspacioCulturalNombre() != null) existente.setEspacioCulturalNombre(datos.getEspacioCulturalNombre());
        if (datos.getEspacioCulturalCapacidad() != null) existente.setEspacioCulturalCapacidad(datos.getEspacioCulturalCapacidad());
        if (datos.getEspacioCulturalServicios() != null) existente.setEspacioCulturalServicios(datos.getEspacioCulturalServicios());
        if (datos.getDestinoEducativo() != null) existente.setDestinoEducativo(datos.getDestinoEducativo());

        return patrimonioRepository.save(existente);
    }

    public Optional<Patrimonio> buscarPorId(String idEstab) {
        return patrimonioRepository.findById(idEstab);
    }

    public List<Patrimonio> listarTodos() {
        return patrimonioRepository.findAll();
    }

    public List<Patrimonio> buscarPorSubcategoria(String subcategoria) {
        return patrimonioRepository.findBySubcategoriaPatrimonio(subcategoria);
    }

    public List<Patrimonio> buscarPorTipo(String tipoPatrimonio) {
        return patrimonioRepository.findByTipoPatrimonio(tipoPatrimonio);
    }
}
