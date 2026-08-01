package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.Gastronomia;
import com.municipalidad.sicatbackend.repository.GastronomiaRepository;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastronomiaService {

    private static final String PREFIJO_GASTRONOMIA = "GAS";

    private final GastronomiaRepository gastronomiaRepository;
    private final EstablecimientoRepository establecimientoRepository;

    @Autowired
    public GastronomiaService(GastronomiaRepository gastronomiaRepository,
                              EstablecimientoRepository establecimientoRepository) {
        this.gastronomiaRepository = gastronomiaRepository;
        this.establecimientoRepository = establecimientoRepository;
    }

    public Gastronomia guardar(Gastronomia gastronomia) {
        String idEstab = gastronomia.getIdEstab();

        establecimientoRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "Establecimiento no encontrado: " + idEstab));

        String[] partes = idEstab.split("-");
        if (partes.length < 3 || !PREFIJO_GASTRONOMIA.equals(partes[1])) {
            throw new RuntimeException(
                    "El establecimiento '" + idEstab + "' no pertenece a la categoría Gastronomía (GAS).");
        }

        if (gastronomiaRepository.existsById(idEstab)) {
            throw new RuntimeException(
                    "Ya existe un registro de gastronomía para: " + idEstab +
                    ". Usar PUT /api/gastronomia/" + idEstab);
        }

        return gastronomiaRepository.save(gastronomia);
    }

    public Gastronomia actualizar(String idEstab, Gastronomia datos) {
        Gastronomia existente = gastronomiaRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "No existe registro de gastronomía para: " + idEstab));

        if (datos.getSubcategoriaGastro() != null) existente.setSubcategoriaGastro(datos.getSubcategoriaGastro());
        if (datos.getTipoCocina()         != null) existente.setTipoCocina(datos.getTipoCocina());
        if (datos.getWhatsapp()           != null) existente.setWhatsapp(datos.getWhatsapp());
        if (datos.getLinkReserva()        != null) existente.setLinkReserva(datos.getLinkReserva());
        if (datos.getComentario()         != null) existente.setComentario(datos.getComentario());
        if (datos.getZonaCiudad()         != null) existente.setZonaCiudad(datos.getZonaCiudad());
        if (datos.getDiasApertura()       != null) existente.setDiasApertura(datos.getDiasApertura());
        if (datos.getHorarioApertura()    != null) existente.setHorarioApertura(datos.getHorarioApertura());

        return gastronomiaRepository.save(existente);
    }

    public Optional<Gastronomia> buscarPorId(String idEstab) {
        return gastronomiaRepository.findById(idEstab);
    }

    public List<Gastronomia> listarTodos() {
        return gastronomiaRepository.findAll();
    }

    public List<Gastronomia> buscarPorSubcategoria(String subcategoria) {
        return gastronomiaRepository.findBySubcategoriaGastro(subcategoria);
    }

    public List<Gastronomia> buscarPorZona(String zona) {
        return gastronomiaRepository.findByZonaCiudad(zona);
    }

    public List<Gastronomia> buscarPorTipoCocina(String tipoCocina) {
        return gastronomiaRepository.findByTipoCocinaContainingIgnoreCase(tipoCocina);
    }
}