package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "establecimiento", schema = "sicat")
public class Establecimiento {

    @Id
    @Column(name = "id_estab", length = 20)
    private String idEstab;

    // FK a categoria_turistica — se guarda solo el ID (sin @ManyToOne para simplificar)
    @Column(name = "id_cat", nullable = false)
    private Integer idCat;

    // FK a subcategoria — opcional
    @Column(name = "id_subcat")
    private Integer idSubcat;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "direccion", length = 250)
    private String direccion;

    @Column(name = "tel_fijo", length = 30)
    private String telFijo;

    @Column(name = "tel_movil", length = 30)
    private String telMovil;

    @Column(name = "whatsapp", length = 20)
    private String whatsapp;

    @Column(name = "correo", length = 150)
    private String correo;

    @Column(name = "web", length = 250)
    private String web;

    @Column(name = "instagram", length = 100)
    private String instagram;

    @Column(name = "facebook", length = 250)
    private String facebook;

    @Column(name = "anio_inauguracion")
    private Short anioInauguracion;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado = "Activo";

    @Column(name = "fecha_carga")
    private LocalDate fechaCarga;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    @Column(name = "personal_permanente")
    private Short personalPermanente;

    @Column(name = "personal_eventual")
    private Short personalEventual;

    @Column(name = "redes_sociales", length = 200)
    private String redesSociales;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    // --- Constructores ---
    public Establecimiento() {}

    // --- Getters y Setters ---
    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public Integer getIdCat() { return idCat; }
    public void setIdCat(Integer idCat) { this.idCat = idCat; }

    public Integer getIdSubcat() { return idSubcat; }
    public void setIdSubcat(Integer idSubcat) { this.idSubcat = idSubcat; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelFijo() { return telFijo; }
    public void setTelFijo(String telFijo) { this.telFijo = telFijo; }

    public String getTelMovil() { return telMovil; }
    public void setTelMovil(String telMovil) { this.telMovil = telMovil; }

    public String getWhatsapp() { return whatsapp; }
    public void setWhatsapp(String whatsapp) { this.whatsapp = whatsapp; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getWeb() { return web; }
    public void setWeb(String web) { this.web = web; }

    public String getInstagram() { return instagram; }
    public void setInstagram(String instagram) { this.instagram = instagram; }

    public String getFacebook() { return facebook; }
    public void setFacebook(String facebook) { this.facebook = facebook; }

    public Short getAnioInauguracion() { return anioInauguracion; }
    public void setAnioInauguracion(Short anioInauguracion) { this.anioInauguracion = anioInauguracion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaCarga() { return fechaCarga; }
    public void setFechaCarga(LocalDate fechaCarga) { this.fechaCarga = fechaCarga; }

    public LocalDate getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDate fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    public Short getPersonalPermanente() { return personalPermanente; }
    public void setPersonalPermanente(Short personalPermanente) { this.personalPermanente = personalPermanente; }

    public Short getPersonalEventual() { return personalEventual; }
    public void setPersonalEventual(Short personalEventual) { this.personalEventual = personalEventual; }

    public String getRedesSociales() { return redesSociales; }
    public void setRedesSociales(String redesSociales) { this.redesSociales = redesSociales; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
