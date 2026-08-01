package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.SalaEvento;
import com.municipalidad.sicatbackend.service.SalaEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas-evento")
public class SalaEventoController {

    private final SalaEventoService service;

    @Autowired
    public SalaEventoController(SalaEventoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody SalaEvento salaEvento) {
        try {
            return ResponseEntity.ok(service.guardar(salaEvento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idEstab}")
    public ResponseEntity<?> actualizar(@PathVariable String idEstab,
                                        @RequestBody SalaEvento datos) {
        try {
            return ResponseEntity.ok(service.actualizar(idEstab, datos));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<SalaEvento> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{idEstab}")
    public ResponseEntity<?> obtener(@PathVariable String idEstab) {
        return service.buscarPorId(idEstab)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/padre/{idEstabPadre}")
    public List<SalaEvento> porPadre(@PathVariable String idEstabPadre) {
        return service.buscarPorEstablecimientoPadre(idEstabPadre);
    }

    @GetMapping("/tipo/{tipoSala}")
    public List<SalaEvento> porTipo(@PathVariable String tipoSala) {
        return service.buscarPorTipoSala(tipoSala);
    }
}
