package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.Relevamiento;
import com.municipalidad.sicatbackend.service.RelevamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relevamientos")
public class RelevamientoController {

    private final RelevamientoService service;

    @Autowired
    public RelevamientoController(RelevamientoService service) {
        this.service = service;
    }

    // POST /api/relevamientos
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Relevamiento relevamiento) {
        try {
            return ResponseEntity.ok(service.registrar(relevamiento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/relevamientos
    @GetMapping
    public List<Relevamiento> listar() {
        return service.listarTodos();
    }

    // GET /api/relevamientos/establecimiento/{idEstab}
    // Historial de relevamientos de un establecimiento puntual
    @GetMapping("/establecimiento/{idEstab}")
    public List<Relevamiento> porEstablecimiento(@PathVariable String idEstab) {
        return service.listarPorEstablecimiento(idEstab);
    }

    // GET /api/relevamientos/estado?valor=Pendiente
    @GetMapping("/estado")
    public List<Relevamiento> porEstado(@RequestParam String valor) {
        return service.listarPorEstado(valor);
    }

    // GET /api/relevamientos/tecnico?valor=Juan
    @GetMapping("/tecnico")
    public List<Relevamiento> porTecnico(@RequestParam String valor) {
        return service.listarPorTecnico(valor);
    }
}
