package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.Establecimiento;
import com.municipalidad.sicatbackend.service.EstablecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establecimientos")
public class EstablecimientoController {

    private final EstablecimientoService service;

    @Autowired
    public EstablecimientoController(EstablecimientoService service) {
        this.service = service;
    }

    // POST /api/establecimientos → crear un establecimiento nuevo
    @PostMapping
    public ResponseEntity<Establecimiento> crear(@RequestBody Establecimiento establecimiento) {
        Establecimiento guardado = service.guardar(establecimiento);
        return ResponseEntity.ok(guardado);
    }

    // GET /api/establecimientos → listar todos
    @GetMapping
    public List<Establecimiento> listar() {
        return service.listarTodos();
    }

    // GET /api/establecimientos/buscar?nombre=xxx → buscar por nombre
    @GetMapping("/buscar")
    public List<Establecimiento> buscar(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }

    // GET /api/establecimientos/categoria/{idCat} → filtrar por categoría
    @GetMapping("/categoria/{idCat}")
    public List<Establecimiento> porCategoria(@PathVariable Integer idCat) {
        return service.listarPorCategoria(idCat);
    }
}
