package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.CategoriaTuristica;
import com.municipalidad.sicatbackend.service.CategoriaTuristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaTuristicaController {

    private final CategoriaTuristicaService service;

    @Autowired
    public CategoriaTuristicaController(CategoriaTuristicaService service) {
        this.service = service;
    }

    // POST /api/categorias -> crear una categoría nueva
    @PostMapping
    public CategoriaTuristica crear(@RequestBody CategoriaTuristica categoria) {
        return service.guardar(categoria);
    }

    // GET /api/categorias -> listar todas
    @GetMapping
    public List<CategoriaTuristica> listar() {
        return service.listarTodos();
    }

    // GET /api/categorias/buscar?texto=xxx -> buscar por nombre
    @GetMapping("/buscar")
    public List<CategoriaTuristica> buscar(@RequestParam String texto) {
        return service.buscarPorTexto(texto);
    }
}