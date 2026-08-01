package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.MuseoSalaCultural;
import com.municipalidad.sicatbackend.repository.MuseoSalaCulturalRepository;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuseoSalaCulturalService {

    private static final String PREFIJO_MUSEO = "MUS";

    private final MuseoSalaCulturalRepository museoRepository;
    private final EstablecimientoRepository establecimientoRepository;

    @Autowired
    public MuseoSalaCulturalService(MuseoSalaCulturalRepository museoRepository,
                                    EstablecimientoRepository establecimientoRepository) {
        this.museoRepository = museoRepository;
        this.establecimientoRepository = establecimientoRepository;
    }

    public MuseoSalaCultural guardar(MuseoSalaCultural museo) {
        String idEstab = museo.getIdEstab();

        establecimientoRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "Establecimiento no encontrado: " + idEstab));

        String[] partes = idEstab.split("-");
        if (partes.length < 3 || !PREFIJO_MUSEO.equals(partes[1])) {
            throw new RuntimeException(
                    "El establecimiento '" + idEstab + "' no pertenece a la categoría Museo/Sala Cultural (MUS).");
        }

        if (museoRepository.existsById(idEstab)) {
            throw new RuntimeException(
                    "Ya existe un registro para: " + idEstab +
                    ". Usar PUT /api/museos/" + idEstab);
        }

        return museoRepository.save(museo);
    }

    public MuseoSalaCultural actualizar(String idEstab, MuseoSalaCultural datos) {
        MuseoSalaCultural existente = museoRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "No existe registro de museo/sala cultural para: " + idEstab));

        if (datos.getSubcategoriaMuseo()    != null) existente.setSubcategoriaMuseo(datos.getSubcategoriaMuseo());
        if (datos.getDominio()              != null) existente.setDominio(datos.getDominio());
        if (datos.getFuncionamiento()       != null) existente.setFuncionamiento(datos.getFuncionamiento());
        if (datos.getTipoEntrada()          != null) existente.setTipoEntrada(datos.getTipoEntrada());
        if (datos.getVisitasGuiadas()       != null) existente.setVisitasGuiadas(datos.getVisitasGuiadas());
        if (datos.getDescripcion()          != null) existente.setDescripcion(datos.getDescripcion());
        if (datos.getObservacionesMuseo()   != null) existente.setObservacionesMuseo(datos.getObservacionesMuseo());
        if (datos.getColeccionPrincipal()   != null) existente.setColeccionPrincipal(datos.getColeccionPrincipal());
        if (datos.getServiciosAdicionales() != null) existente.setServiciosAdicionales(datos.getServiciosAdicionales());

        return museoRepository.save(existente);
    }

    public Optional<MuseoSalaCultural> buscarPorId(String idEstab) {
        return museoRepository.findById(idEstab);
    }

    public List<MuseoSalaCultural> listarTodos() {
        return museoRepository.findAll();
    }

    public List<MuseoSalaCultural> buscarPorDominio(String dominio) {
        return museoRepository.findByDominio(dominio);
    }

    public List<MuseoSalaCultural> buscarPorFuncionamiento(String funcionamiento) {
        return museoRepository.findByFuncionamiento(funcionamiento);
    }

    public List<MuseoSalaCultural> buscarPorTipoEntrada(String tipoEntrada) {
        return museoRepository.findByTipoEntrada(tipoEntrada);
    }
}