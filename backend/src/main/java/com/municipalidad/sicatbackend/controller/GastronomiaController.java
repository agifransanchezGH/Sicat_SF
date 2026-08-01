package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.Gastronomia;
import com.municipalidad.sicatbackend.service.GastronomiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastronomia")
public class GastronomiaController {

    private final GastronomiaService service;

    @Autowired
    public GastronomiaController(GastronomiaService service) {
        this.service = service;
    }

    // POST /api/gastronomia
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Gastronomia gastronomia) {
        try {
            return ResponseEntity.ok(service.guardar(gastronomia));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /api/gastronomia/{idEstab}
    @PutMapping("/{idEstab}")
    public ResponseEntity<?> actualizar(@PathVariable String idEstab,
                                        @RequestBody Gastronomia datos) {
        try {
            return ResponseEntity.ok(service.actualizar(idEstab, datos));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/gastronomia
    @GetMapping
    public List<Gastronomia> listar() {
        return service.listarTodos();
    }

    // GET /api/gastronomia/{idEstab}
    @GetMapping("/{idEstab}")
    public ResponseEntity<?> obtener(@PathVariable String idEstab) {
        return service.buscarPorId(idEstab)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/gastronomia/subcategoria?valor=Restaurante
    @GetMapping("/subcategoria")
    public List<Gastronomia> porSubcategoria(@RequestParam String valor) {
        return service.buscarPorSubcategoria(valor);
    }

    // GET /api/gastronomia/zona?valor=Costanera
    @GetMapping("/zona")
    public List<Gastronomia> porZona(@RequestParam String valor) {
        return service.buscarPorZona(valor);
    }

    // GET /api/gastronomia/cocina?valor=Pescado
    @GetMapping("/cocina")
    public List<Gastronomia> porTipoCocina(@RequestParam String valor) {
        return service.buscarPorTipoCocina(valor);
    }
}