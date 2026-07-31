package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "relevamiento", schema = "sicat")
public class Relevamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relev")
    private Integer idRelev;

    @Column(name = "id_estab", nullable = false, length = 20)
    private String idEstab;

    @Column(name = "fecha_relevamiento", nullable = false)
    private LocalDate fechaRelevamiento;

    @Column(name = "tecnico_responsable", length = 100)
    private String tecnicoResponsable;

    // Nombre exacto de columna en DB: observaciones_carga
    @Column(name = "observaciones_carga", length = 500)
    private String observacionesCarga;

    // Valores válidos: Completo | Incompleto | En revisión | Con observaciones
    @Column(name = "estado_carga", length = 30)
    private String estadoCarga;

    // --- Constructores ---
    public Relevamiento() {}

    // --- Getters y Setters ---
    public Integer getIdRelev() { return idRelev; }
    public void setIdRelev(Integer idRelev) { this.idRelev = idRelev; }

    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public LocalDate getFechaRelevamiento() { return fechaRelevamiento; }
    public void setFechaRelevamiento(LocalDate fechaRelevamiento) { this.fechaRelevamiento = fechaRelevamiento; }

    public String getTecnicoResponsable() { return tecnicoResponsable; }
    public void setTecnicoResponsable(String tecnicoResponsable) { this.tecnicoResponsable = tecnicoResponsable; }

    public String getObservacionesCarga() { return observacionesCarga; }
    public void setObservacionesCarga(String observacionesCarga) { this.observacionesCarga = observacionesCarga; }

    public String getEstadoCarga() { return estadoCarga; }
    public void setEstadoCarga(String estadoCarga) { this.estadoCarga = estadoCarga; }
}