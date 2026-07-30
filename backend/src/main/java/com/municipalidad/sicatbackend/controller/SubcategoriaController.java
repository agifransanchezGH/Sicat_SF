package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.Subcategoria;
import com.municipalidad.sicatbackend.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategorias")
public class SubcategoriaController {

    private final SubcategoriaService service;

    @Autowired
    public SubcategoriaController(SubcategoriaService service) {
        this.service = service;
    }

    // GET /api/subcategorias → todas
    @GetMapping
    public List<Subcategoria> listar() {
        return service.listarTodas();
    }

    // GET /api/subcategorias?idCat=1 → dropdown dinámico por categoría
    @GetMapping(params = "idCat")
    public List<Subcategoria> listarPorCategoria(@RequestParam Integer idCat) {
        return service.listarPorCategoria(idCat);
    }

    // POST /api/subcategorias → crear
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Subcategoria subcategoria) {
        try {
            return ResponseEntity.ok(service.guardar(subcategoria));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}