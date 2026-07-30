package com.municipalidad.sicatbackend.service;

import com.municipalidad.sicatbackend.entity.CategoriaTuristica;
import com.municipalidad.sicatbackend.repository.CategoriaTuristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaTuristicaService {

    private final CategoriaTuristicaRepository repository;

    @Autowired
    public CategoriaTuristicaService(CategoriaTuristicaRepository repository) {
        this.repository = repository;
    }

    public CategoriaTuristica guardar(CategoriaTuristica categoria) {
        return repository.save(categoria);
    }

    public List<CategoriaTuristica> listarTodos() {
        return repository.findAll();
    }

    public List<CategoriaTuristica> buscarPorTexto(String texto) {
        return repository.findByNombreCategoriaContainingIgnoreCase(texto);
    }
}