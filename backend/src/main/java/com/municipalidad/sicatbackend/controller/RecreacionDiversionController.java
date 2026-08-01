package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.RecreacionDiversion;
import com.municipalidad.sicatbackend.service.RecreacionDiversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recreacion-diversion")
public class RecreacionDiversionController {

    private final RecreacionDiversionService service;

    @Autowired
    public RecreacionDiversionController(RecreacionDiversionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody RecreacionDiversion recreacionDiversion) {
        try {
            return ResponseEntity.ok(service.guardar(recreacionDiversion));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idEstab}")
    public ResponseEntity<?> actualizar(@PathVariable String idEstab,
                                        @RequestBody RecreacionDiversion datos) {
        try {
            return ResponseEntity.ok(service.actualizar(idEstab, datos));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<RecreacionDiversion> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{idEstab}")
    public ResponseEntity<?> obtener(@PathVariable String idEstab) {
        return service.buscarPorId(idEstab)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/subcategoria")
    public List<RecreacionDiversion> porSubcategoria(@RequestParam String valor) {
        return service.buscarPorSubcategoria(valor);
    }

    @GetMapping("/tipo")
    public List<RecreacionDiversion> porTipo(@RequestParam String valor) {
        return service.buscarPorTipoServicio(valor);
    }
}
