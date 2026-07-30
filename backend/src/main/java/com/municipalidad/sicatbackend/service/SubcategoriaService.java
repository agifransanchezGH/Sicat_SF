package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.Subcategoria;
import com.municipalidad.sicatbackend.repository.SubcategoriaRepository;
import com.municipalidad.sicatbackend.repository.CategoriaTuristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoriaService {

    private final SubcategoriaRepository subcategoriaRepository;
    private final CategoriaTuristicaRepository categoriaRepository;

    @Autowired
    public SubcategoriaService(SubcategoriaRepository subcategoriaRepository,
                               CategoriaTuristicaRepository categoriaRepository) {
        this.subcategoriaRepository = subcategoriaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Subcategoria> listarTodas() {
        return subcategoriaRepository.findAll();
    }

    public List<Subcategoria> listarPorCategoria(Integer idCat) {
        return subcategoriaRepository.findByIdCatOrderByNombreSubcatAsc(idCat);
    }

    public Subcategoria guardar(Subcategoria subcategoria) {
        // Validar que la categoría padre exista
        categoriaRepository.findById(subcategoria.getIdCat())
                .orElseThrow(() -> new RuntimeException(
                        "Categoría no encontrada: " + subcategoria.getIdCat()));

        // Validar unicidad idCat + nombreSubcat
        if (subcategoriaRepository.existsByIdCatAndNombreSubcat(
                subcategoria.getIdCat(), subcategoria.getNombreSubcat())) {
            throw new RuntimeException(
                    "Ya existe la subcategoría '" + subcategoria.getNombreSubcat() +
                    "' para esa categoría.");
        }

        return subcategoriaRepository.save(subcategoria);
    }
}