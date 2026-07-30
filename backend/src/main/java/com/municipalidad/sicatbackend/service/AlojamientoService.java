package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.Alojamiento;
import com.municipalidad.sicatbackend.entity.Establecimiento;
import com.municipalidad.sicatbackend.repository.AlojamientoRepository;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlojamientoService {

    // Prefijos válidos para la categoría Alojamiento
    private static final List<String> PREFIJOS_ALOJAMIENTO = List.of("HOT", "DEL");

    private final AlojamientoRepository alojamientoRepository;
    private final EstablecimientoRepository establecimientoRepository;

    @Autowired
    public AlojamientoService(AlojamientoRepository alojamientoRepository,
                              EstablecimientoRepository establecimientoRepository) {
        this.alojamientoRepository = alojamientoRepository;
        this.establecimientoRepository = establecimientoRepository;
    }

    /**
     * Guarda el detalle de alojamiento para un establecimiento ya existente.
     * Valida que el establecimiento exista y que pertenezca a la categoría
     * correcta (HOT o DEL) antes de persistir.
     */
    @Transactional
    public Alojamiento guardar(Alojamiento alojamiento) {
        String idEstab = alojamiento.getIdEstab();

        // 1. Verificar que el establecimiento existe
        establecimientoRepository
                .findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "Establecimiento no encontrado: " + idEstab));

        // 2. Verificar que el ID corresponde a la categoría Alojamiento
        // El prefijo está embebido en el ID → SICAT-HOT-00001 → partes[1] = "HOT"
        String[] partes = idEstab.split("-");
        if (partes.length < 3 || !PREFIJOS_ALOJAMIENTO.contains(partes[1])) {
            throw new RuntimeException(
                    "El establecimiento '" + idEstab + "' no pertenece a la categoría Alojamiento. " +
                    "Prefijos válidos: " + PREFIJOS_ALOJAMIENTO);
        }

        // 3. Evitar duplicados: si ya existe un registro para este ID, indicarlo claramente
        if (alojamientoRepository.existsById(idEstab)) {
            throw new RuntimeException(
                    "Ya existe un registro de alojamiento para: " + idEstab +
                    ". Usar el endpoint PUT /api/alojamientos/" + idEstab);
        }

        return alojamientoRepository.save(alojamiento);
    }

    /**
     * Actualiza el detalle de alojamiento de un establecimiento existente.
     * Solo sobreescribe los campos que llegan con valor no nulo (patch parcial).
     */
    @Transactional
    public Alojamiento actualizar(String idEstab, Alojamiento datos) {
        Alojamiento existente = alojamientoRepository.findById(idEstab)
                .orElseThrow(() -> new RuntimeException(
                        "No existe registro de alojamiento para: " + idEstab));

        if (datos.getTipoAlojamiento()        != null) existente.setTipoAlojamiento(datos.getTipoAlojamiento());
        if (datos.getReformasUltimos4Anios()  != null) existente.setReformasUltimos4Anios(datos.getReformasUltimos4Anios());
        if (datos.getObservacionesReforma()   != null) existente.setObservacionesReforma(datos.getObservacionesReforma());
        if (datos.getCantHabitaciones()       != null) existente.setCantHabitaciones(datos.getCantHabitaciones());
        if (datos.getTiposHabitaciones()      != null) existente.setTiposHabitaciones(datos.getTiposHabitaciones());
        if (datos.getTotalPlazas()            != null) existente.setTotalPlazas(datos.getTotalPlazas());
        if (datos.getCantHabAccesibles()      != null) existente.setCantHabAccesibles(datos.getCantHabAccesibles());
        if (datos.getCantPlazasAccesibles()   != null) existente.setCantPlazasAccesibles(datos.getCantPlazasAccesibles());
        if (datos.getServiciosGenerales()     != null) existente.setServiciosGenerales(datos.getServiciosGenerales());
        if (datos.getSalaEventosNombreCap()   != null) existente.setSalaEventosNombreCap(datos.getSalaEventosNombreCap());
        if (datos.getEquipamientoHabitacion() != null) existente.setEquipamientoHabitacion(datos.getEquipamientoHabitacion());
        if (datos.getSistemaInfoCalidad()     != null) existente.setSistemaInfoCalidad(datos.getSistemaInfoCalidad());

        return alojamientoRepository.save(existente);
    }

    public Optional<Alojamiento> buscarPorId(String idEstab) {
        return alojamientoRepository.findById(idEstab);
    }

    public List<Alojamiento> listarTodos() {
        return alojamientoRepository.findAll();
    }

    public List<Alojamiento> buscarPorTipo(String tipo) {
        return alojamientoRepository.findByTipoAlojamiento(tipo);
    }

    public List<Alojamiento> buscarPorPlazasMinimas(Integer plazas) {
        return alojamientoRepository.findByTotalPlazasGreaterThanEqual(plazas);
    }

    public List<Alojamiento> buscarAccesibles() {
        return alojamientoRepository.findByCantHabAccesiblesGreaterThan((short) 0);
    }

    public List<Alojamiento> buscarPorServicio(String servicio) {
        return alojamientoRepository.findByServicio(servicio);
    }
}
