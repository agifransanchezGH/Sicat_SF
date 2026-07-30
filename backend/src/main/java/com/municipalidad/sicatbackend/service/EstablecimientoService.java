package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.Establecimiento;
import com.municipalidad.sicatbackend.entity.CategoriaTuristica;
import com.municipalidad.sicatbackend.entity.SecuenciaId;
import com.municipalidad.sicatbackend.repository.EstablecimientoRepository;
import com.municipalidad.sicatbackend.repository.CategoriaTuristicaRepository;
import com.municipalidad.sicatbackend.repository.SecuenciaIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstablecimientoService {

    private final EstablecimientoRepository establecimientoRepository;
    private final CategoriaTuristicaRepository categoriaRepository;
    private final SecuenciaIdRepository secuenciaIdRepository;

    @Autowired
    public EstablecimientoService(
            EstablecimientoRepository establecimientoRepository,
            CategoriaTuristicaRepository categoriaRepository,
            SecuenciaIdRepository secuenciaIdRepository) {
        this.establecimientoRepository = establecimientoRepository;
        this.categoriaRepository = categoriaRepository;
        this.secuenciaIdRepository = secuenciaIdRepository;
    }

    @Transactional
    public Establecimiento guardar(Establecimiento establecimiento) {
        // 1. Obtener el prefijo de la categoría seleccionada
        CategoriaTuristica categoria = categoriaRepository.findById(establecimiento.getIdCat())
                .orElseThrow(() -> new RuntimeException(
                        "Categoría no encontrada: " + establecimiento.getIdCat()));

        String prefijo = categoria.getPrefijo();

        // 2. Incrementar el correlativo en secuencia_id (operación atómica)
        secuenciaIdRepository.incrementar(prefijo);
        SecuenciaId secuencia = secuenciaIdRepository.findById(prefijo)
                .orElseThrow(() -> new RuntimeException(
                        "Secuencia no encontrada para prefijo: " + prefijo));

        // 3. Construir ID en formato SICAT-XXX-NNNNN
        String idGenerado = String.format("SICAT-%s-%05d", prefijo, secuencia.getUltimoNumero());
        establecimiento.setIdEstab(idGenerado);

        // 4. Establecer fecha de carga
        establecimiento.setFechaCarga(LocalDate.now());

        // 5. Persistir — merge() detecta el ID no nulo, hace SELECT → no existe → INSERT
        return establecimientoRepository.save(establecimiento);
    }

    public List<Establecimiento> listarTodos() {
        return establecimientoRepository.findAll();
    }

    public List<Establecimiento> buscarPorNombre(String nombre) {
        return establecimientoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Establecimiento> listarPorCategoria(Integer idCat) {
        return establecimientoRepository.findByIdCat(idCat);
    }
}
