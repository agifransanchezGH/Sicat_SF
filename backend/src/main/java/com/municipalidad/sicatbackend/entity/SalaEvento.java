package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sala_evento", schema = "sicat")
public class SalaEvento {

    @Id
    @Column(name = "id_estab", length = 20)
    private String idEstab;

    @Column(name = "id_estab_padre", length = 20)
    private String idEstabPadre;

    @Column(name = "nombre_sala", length = 150)
    private String nombreSala;

    @Column(name = "capacidad_personas")
    private Short capacidadPersonas;

    @Column(name = "superficie_m2")
    private Integer superficieM2;

    @Column(name = "tipo_sala", length = 100)
    private String tipoSala;

    @Column(name = "tiene_audio")
    private Boolean tieneAudio;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    public SalaEvento() {}

    public String getIdEstab() {
        return idEstab;
    }

    public void setIdEstab(String idEstab) {
        this.idEstab = idEstab;
    }

    public String getIdEstabPadre() {
        return idEstabPadre;
    }

    public void setIdEstabPadre(String idEstabPadre) {
        this.idEstabPadre = idEstabPadre;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public Short getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public void setCapacidadPersonas(Short capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public Integer getSuperficieM2() {
        return superficieM2;
    }

    public void setSuperficieM2(Integer superficieM2) {
        this.superficieM2 = superficieM2;
    }

    public String getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }

    public Boolean getTieneAudio() {
        return tieneAudio;
    }

    public void setTieneAudio(Boolean tieneAudio) {
        this.tieneAudio = tieneAudio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
