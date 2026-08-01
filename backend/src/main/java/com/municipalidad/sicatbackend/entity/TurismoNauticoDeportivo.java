package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "turismo_nautico_deportivo", schema = "sicat")
public class TurismoNauticoDeportivo {

    @Id
    @Column(name = "id_estab", length = 20)
    private String idEstab;

    @Column(name = "subcategoria_nautica", length = 100)
    private String subcategoriaNautica;

    @Column(name = "tipo_nautico", length = 100)
    private String tipoNautico;

    @Column(name = "puerto_base", length = 150)
    private String puertoBase;

    @Column(name = "embarcaciones_cantidad")
    private Short embarcacionesCantidad;

    @Column(name = "embarcaciones_tipo", length = 150)
    private String embarcacionesTipo;

    @Column(name = "servicios_adicionales", length = 250)
    private String serviciosAdicionales;

    @Column(name = "temporada_operativa", length = 100)
    private String temporadaOperativa;

    @Column(name = "salida_con_guia")
    private Boolean salidaConGuia;

    @Column(name = "deportes_acuaticos", length = 200)
    private String deportesAcuaticos;

    @Column(name = "escuela_nautica")
    private Boolean escuelaNautica;

    @Column(name = "numero_alumnos")
    private Short numeroAlumnos;

    @Column(name = "zona_protegida", length = 200)
    private String zonaProtegida;

    @Column(name = "habilitaciones", length = 250)
    private String habilitaciones;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    public TurismoNauticoDeportivo() {}

    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public String getSubcategoriaNautica() { return subcategoriaNautica; }
    public void setSubcategoriaNautica(String subcategoriaNautica) { this.subcategoriaNautica = subcategoriaNautica; }

    public String getTipoNautico() { return tipoNautico; }
    public void setTipoNautico(String tipoNautico) { this.tipoNautico = tipoNautico; }

    public String getPuertoBase() { return puertoBase; }
    public void setPuertoBase(String puertoBase) { this.puertoBase = puertoBase; }

    public Short getEmbarcacionesCantidad() { return embarcacionesCantidad; }
    public void setEmbarcacionesCantidad(Short embarcacionesCantidad) { this.embarcacionesCantidad = embarcacionesCantidad; }

    public String getEmbarcacionesTipo() { return embarcacionesTipo; }
    public void setEmbarcacionesTipo(String embarcacionesTipo) { this.embarcacionesTipo = embarcacionesTipo; }

    public String getServiciosAdicionales() { return serviciosAdicionales; }
    public void setServiciosAdicionales(String serviciosAdicionales) { this.serviciosAdicionales = serviciosAdicionales; }

    public String getTemporadaOperativa() { return temporadaOperativa; }
    public void setTemporadaOperativa(String temporadaOperativa) { this.temporadaOperativa = temporadaOperativa; }

    public Boolean getSalidaConGuia() { return salidaConGuia; }
    public void setSalidaConGuia(Boolean salidaConGuia) { this.salidaConGuia = salidaConGuia; }

    public String getDeportesAcuaticos() { return deportesAcuaticos; }
    public void setDeportesAcuaticos(String deportesAcuaticos) { this.deportesAcuaticos = deportesAcuaticos; }

    public Boolean getEscuelaNautica() { return escuelaNautica; }
    public void setEscuelaNautica(Boolean escuelaNautica) { this.escuelaNautica = escuelaNautica; }

    public Short getNumeroAlumnos() { return numeroAlumnos; }
    public void setNumeroAlumnos(Short numeroAlumnos) { this.numeroAlumnos = numeroAlumnos; }

    public String getZonaProtegida() { return zonaProtegida; }
    public void setZonaProtegida(String zonaProtegida) { this.zonaProtegida = zonaProtegida; }

    public String getHabilitaciones() { return habilitaciones; }
    public void setHabilitaciones(String habilitaciones) { this.habilitaciones = habilitaciones; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
