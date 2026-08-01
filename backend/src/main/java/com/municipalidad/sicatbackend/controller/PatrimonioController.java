package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.Patrimonio;
import com.municipalidad.sicatbackend.service.PatrimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrimonios")
public class PatrimonioController {

    private final PatrimonioService service;

    @Autowired
    public PatrimonioController(PatrimonioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Patrimonio patrimonio) {
        try {
            return ResponseEntity.ok(service.guardar(patrimonio));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idEstab}")
    public ResponseEntity<?> actualizar(@PathVariable String idEstab,
                                        @RequestBody Patrimonio datos) {
        try {
            return ResponseEntity.ok(service.actualizar(idEstab, datos));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Patrimonio> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{idEstab}")
    public ResponseEntity<?> obtener(@PathVariable String idEstab) {
        return service.buscarPorId(idEstab)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/subcategoria")
    public List<Patrimonio> porSubcategoria(@RequestParam String valor) {
        return service.buscarPorSubcategoria(valor);
    }

    @GetMapping("/tipo")
    public List<Patrimonio> porTipo(@RequestParam String valor) {
        return service.buscarPorTipo(valor);
    }
}
