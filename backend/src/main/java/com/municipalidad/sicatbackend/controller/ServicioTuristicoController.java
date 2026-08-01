package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.ServicioTuristico;
import com.municipalidad.sicatbackend.service.ServicioTuristicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios-turisticos")
public class ServicioTuristicoController {

    private final ServicioTuristicoService service;

    @Autowired
    public ServicioTuristicoController(ServicioTuristicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ServicioTuristico servicio) {
        try {
            return ResponseEntity.ok(service.guardar(servicio));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idEstab}")
    public ResponseEntity<?> actualizar(@PathVariable String idEstab,
                                        @RequestBody ServicioTuristico datos) {
        try {
            return ResponseEntity.ok(service.actualizar(idEstab, datos));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<ServicioTuristico> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{idEstab}")
    public ResponseEntity<?> obtener(@PathVariable String idEstab) {
        return service.buscarPorId(idEstab)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/subcategoria")
    public List<ServicioTuristico> porSubcategoria(@RequestParam String valor) {
        return service.buscarPorSubcategoria(valor);
    }

    @GetMapping("/tipo")
    public List<ServicioTuristico> porTipo(@RequestParam String valor) {
        return service.buscarPorTipo(valor);
    }

    @GetMapping("/vehiculo")
    public List<ServicioTuristico> porVehiculo(@RequestParam String valor) {
        return service.buscarPorVehiculo(valor);
    }
}
