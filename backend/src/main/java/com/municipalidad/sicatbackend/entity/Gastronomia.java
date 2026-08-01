package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gastronomia", schema = "sicat")
public class Gastronomia {

    @Id
    @Column(name = "id_estab", length = 20)
    private String idEstab;

    @Column(name = "subcategoria_gastro", length = 100)
    private String subcategoriaGastro;
    // Ej: Restaurante | Bodegón | Bar | Café | Rotisería | Heladería | etc.

    @Column(name = "tipo_cocina", length = 150)
    private String tipoCocina;
    // Ej: Cocina tradicional | Pastas | Pescado de río | Parrilla | Vegana | etc.

    @Column(name = "whatsapp", length = 20)
    private String whatsapp;

    @Column(name = "link_reserva", length = 200)
    private String linkReserva;

    @Column(name = "comentario", length = 300)
    private String comentario;

    @Column(name = "zona_ciudad", length = 50)
    private String zonaCiudad;
    // Ej: Macro centro | Costanera | Sur | Norte | Otro

    @Column(name = "dias_apertura", length = 50)
    private String diasApertura;
    // Ej: Lunes a viernes | Todos los días | Fines de semana

    @Column(name = "horario_apertura", length = 50)
    private String horarioApertura;
    // Formato: HH:MM – HH:MM / HH:MM – HH:MM

    // --- Constructores ---
    public Gastronomia() {}

    // --- Getters y Setters ---
    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public String getSubcategoriaGastro() { return subcategoriaGastro; }
    public void setSubcategoriaGastro(String subcategoriaGastro) { this.subcategoriaGastro = subcategoriaGastro; }

    public String getTipoCocina() { return tipoCocina; }
    public void setTipoCocina(String tipoCocina) { this.tipoCocina = tipoCocina; }

    public String getWhatsapp() { return whatsapp; }
    public void setWhatsapp(String whatsapp) { this.whatsapp = whatsapp; }

    public String getLinkReserva() { return linkReserva; }
    public void setLinkReserva(String linkReserva) { this.linkReserva = linkReserva; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public String getZonaCiudad() { return zonaCiudad; }
    public void setZonaCiudad(String zonaCiudad) { this.zonaCiudad = zonaCiudad; }

    public String getDiasApertura() { return diasApertura; }
    public void setDiasApertura(String diasApertura) { this.diasApertura = diasApertura; }

    public String getHorarioApertura() { return horarioApertura; }
    public void setHorarioApertura(String horarioApertura) { this.horarioApertura = horarioApertura; }
}