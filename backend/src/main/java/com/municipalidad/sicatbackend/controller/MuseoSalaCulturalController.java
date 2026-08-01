package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.MuseoSalaCultural;
import com.municipalidad.sicatbackend.service.MuseoSalaCulturalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/museos")
public class MuseoSalaCulturalController {

    private final MuseoSalaCulturalService service;

    @Autowired
    public MuseoSalaCulturalController(MuseoSalaCulturalService service) {
        this.service = service;
    }

    // POST /api/museos
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody MuseoSalaCultural museo) {
        try {
            return ResponseEntity.ok(service.guardar(museo));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /api/museos/{idEstab}
    @PutMapping("/{idEstab}")
    public ResponseEntity<?> actualizar(@PathVariable String idEstab,
                                        @RequestBody MuseoSalaCultural datos) {
        try {
            return ResponseEntity.ok(service.actualizar(idEstab, datos));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/museos
    @GetMapping
    public List<MuseoSalaCultural> listar() {
        return service.listarTodos();
    }

    // GET /api/museos/{idEstab}
    @GetMapping("/{idEstab}")
    public ResponseEntity<?> obtener(@PathVariable String idEstab) {
        return service.buscarPorId(idEstab)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/museos/dominio?valor=Municipal
    @GetMapping("/dominio")
    public List<MuseoSalaCultural> porDominio(@RequestParam String valor) {
        return service.buscarPorDominio(valor);
    }

    // GET /api/museos/funcionamiento?valor=Abierto
    @GetMapping("/funcionamiento")
    public List<MuseoSalaCultural> porFuncionamiento(@RequestParam String valor) {
        return service.buscarPorFuncionamiento(valor);
    }

    // GET /api/museos/entrada?valor=Gratuita
    @GetMapping("/entrada")
    public List<MuseoSalaCultural> porTipoEntrada(@RequestParam String valor) {
        return service.buscarPorTipoEntrada(valor);
    }
}