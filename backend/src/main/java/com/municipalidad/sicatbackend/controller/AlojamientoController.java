package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.Alojamiento;
import com.municipalidad.sicatbackend.service.AlojamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alojamientos")
public class AlojamientoController {

    private final AlojamientoService service;

    @Autowired
    public AlojamientoController(AlojamientoService service) {
        this.service = service;
    }

    // POST /api/alojamientos
    // Crea el detalle de alojamiento para un establecimiento ya existente
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Alojamiento alojamiento) {
        try {
            Alojamiento guardado = service.guardar(alojamiento);
            return ResponseEntity.ok(guardado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /api/alojamientos/{idEstab}
    // Actualización parcial: solo sobreescribe campos no nulos
    @PutMapping("/{idEstab}")
    public ResponseEntity<?> actualizar(@PathVariable String idEstab,
                                        @RequestBody Alojamiento datos) {
        try {
            Alojamiento actualizado = service.actualizar(idEstab, datos);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/alojamientos
    // Lista todos los registros de alojamiento
    @GetMapping
    public List<Alojamiento> listar() {
        return service.listarTodos();
    }

    // GET /api/alojamientos/{idEstab}
    // Obtiene el detalle de un alojamiento por ID de establecimiento
    @GetMapping("/{idEstab}")
    public ResponseEntity<?> obtener(@PathVariable String idEstab) {
        return service.buscarPorId(idEstab)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/alojamientos/tipo?valor=Hotel 3 estrellas
    @GetMapping("/tipo")
    public List<Alojamiento> porTipo(@RequestParam String valor) {
        return service.buscarPorTipo(valor);
    }

    // GET /api/alojamientos/plazas?min=50
    @GetMapping("/plazas")
    public List<Alojamiento> porPlazasMinimas(@RequestParam Integer min) {
        return service.buscarPorPlazasMinimas(min);
    }

    // GET /api/alojamientos/accesibles
    @GetMapping("/accesibles")
    public List<Alojamiento> accesibles() {
        return service.buscarAccesibles();
    }

    // GET /api/alojamientos/servicio?valor=WiFi
    @GetMapping("/servicio")
    public List<Alojamiento> porServicio(@RequestParam String valor) {
        return service.buscarPorServicio(valor);
    }
}
