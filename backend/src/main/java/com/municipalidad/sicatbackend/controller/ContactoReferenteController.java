package com.municipalidad.sicatbackend.controller;

import com.municipalidad.sicatbackend.entity.ContactoReferente;
import com.municipalidad.sicatbackend.service.ContactoReferenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactos-referentes")
public class ContactoReferenteController {

    private final ContactoReferenteService service;

    @Autowired
    public ContactoReferenteController(ContactoReferenteService service) {
        this.service = service;
    }

    // POST /api/contactos-referentes
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ContactoReferente contacto) {
        try {
            return ResponseEntity.ok(service.guardar(contacto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /api/contactos-referentes/{idRef}
    @PutMapping("/{idRef}")
    public ResponseEntity<?> actualizar(@PathVariable Integer idRef,
                                        @RequestBody ContactoReferente datos) {
        try {
            return ResponseEntity.ok(service.actualizar(idRef, datos));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/contactos-referentes/establecimiento/{idEstab}
    @GetMapping("/establecimiento/{idEstab}")
    public List<ContactoReferente> porEstablecimiento(@PathVariable String idEstab) {
        return service.listarPorEstablecimiento(idEstab);
    }

    // DELETE /api/contactos-referentes/{idRef}
    @DeleteMapping("/{idRef}")
    public ResponseEntity<?> eliminar(@PathVariable Integer idRef) {
        try {
            service.eliminar(idRef);
            return ResponseEntity.ok("Contacto eliminado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
