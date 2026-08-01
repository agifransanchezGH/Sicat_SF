package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "museo_sala_cultural", schema = "sicat")
public class MuseoSalaCultural {

    @Id
    @Column(name = "id_estab", length = 20)
    private String idEstab;

    @Column(name = "subcategoria_museo", length = 100)
    private String subcategoriaMuseo;

    // CHECK: Municipal | Provincial | Nacional | Privado | Mixto
    @Column(name = "dominio", length = 20)
    private String dominio;

    // CHECK: Abierto | Cerrado | Temporario | En reformas
    @Column(name = "funcionamiento", length = 20)
    private String funcionamiento;

    // CHECK: Gratuita | Con cargo | Entrada voluntaria | Mixta
    @Column(name = "tipo_entrada", length = 50)
    private String tipoEntrada;

    @Column(name = "visitas_guiadas", length = 100)
    private String visitasGuiadas;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "observaciones_museo", length = 300)
    private String observacionesMuseo;

    @Column(name = "coleccion_principal", length = 100)
    private String coleccionPrincipal;

    @Column(name = "servicios_adicionales", length = 300)
    private String serviciosAdicionales;

    // --- Constructores ---
    public MuseoSalaCultural() {}

    // --- Getters y Setters ---
    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public String getSubcategoriaMuseo() { return subcategoriaMuseo; }
    public void setSubcategoriaMuseo(String subcategoriaMuseo) { this.subcategoriaMuseo = subcategoriaMuseo; }

    public String getDominio() { return dominio; }
    public void setDominio(String dominio) { this.dominio = dominio; }

    public String getFuncionamiento() { return funcionamiento; }
    public void setFuncionamiento(String funcionamiento) { this.funcionamiento = funcionamiento; }

    public String getTipoEntrada() { return tipoEntrada; }
    public void setTipoEntrada(String tipoEntrada) { this.tipoEntrada = tipoEntrada; }

    public String getVisitasGuiadas() { return visitasGuiadas; }
    public void setVisitasGuiadas(String visitasGuiadas) { this.visitasGuiadas = visitasGuiadas; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getObservacionesMuseo() { return observacionesMuseo; }
    public void setObservacionesMuseo(String observacionesMuseo) { this.observacionesMuseo = observacionesMuseo; }

    public String getColeccionPrincipal() { return coleccionPrincipal; }
    public void setColeccionPrincipal(String coleccionPrincipal) { this.coleccionPrincipal = coleccionPrincipal; }

    public String getServiciosAdicionales() { return serviciosAdicionales; }
    public void setServiciosAdicionales(String serviciosAdicionales) { this.serviciosAdicionales = serviciosAdicionales; }
}