package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.TurismoNauticoDeportivo;
import com.municipalidad.sicatbackend.service.TurismoNauticoDeportivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turismo_nautico_deportivo")
@CrossOrigin(origins = "*")
public class TurismoNauticoDeportivoController {

    private final TurismoNauticoDeportivoService service;

    @Autowired
    public TurismoNauticoDeportivoController(TurismoNauticoDeportivoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TurismoNauticoDeportivo> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{idEstab}")
    public ResponseEntity<TurismoNauticoDeportivo> obtenerPorId(@PathVariable String idEstab) {
        return service.buscarPorId(idEstab)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TurismoNauticoDeportivo crear(@RequestBody TurismoNauticoDeportivo turismo) {
        return service.guardar(turismo);
    }

    @PutMapping("/{idEstab}")
    public TurismoNauticoDeportivo actualizar(@PathVariable String idEstab,
                                              @RequestBody TurismoNauticoDeportivo turismo) {
        return service.actualizar(idEstab, turismo);
    }

    @GetMapping("/subcategoria/{subcategoria}")
    public List<TurismoNauticoDeportivo> buscarPorSubcategoria(@PathVariable String subcategoria) {
        return service.buscarPorSubcategoria(subcategoria);
    }

    @GetMapping("/tipo/{tipo}")
    public List<TurismoNauticoDeportivo> buscarPorTipo(@PathVariable String tipo) {
        return service.buscarPorTipo(tipo);
    }

    @GetMapping("/puerto/{puerto}")
    public List<TurismoNauticoDeportivo> buscarPorPuerto(@PathVariable String puerto) {
        return service.buscarPorPuerto(puerto);
    }
}
