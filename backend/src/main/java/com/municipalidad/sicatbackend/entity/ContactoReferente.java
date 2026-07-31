package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contacto_referente", schema = "sicat")
public class ContactoReferente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ref")
    private Integer idRef;

    @Column(name = "id_estab", nullable = false, length = 20)
    private String idEstab;

    @Column(name = "nombre_referente", length = 150)
    private String nombreReferente;

    @Column(name = "telefono_referente", length = 20)
    private String telefonoReferente;

    @Column(name = "correo_referente", length = 150)
    private String correoReferente;

    @Column(name = "cargo", length = 130)
    private String cargo;

    // --- Constructores ---
    public ContactoReferente() {}

    // --- Getters y Setters ---
    public Integer getIdRef() { return idRef; }
    public void setIdRef(Integer idRef) { this.idRef = idRef; }

    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public String getNombreReferente() { return nombreReferente; }
    public void setNombreReferente(String nombreReferente) { this.nombreReferente = nombreReferente; }

    public String getTelefonoReferente() { return telefonoReferente; }
    public void setTelefonoReferente(String telefonoReferente) { this.telefonoReferente = telefonoReferente; }

    public String getCorreoReferente() { return correoReferente; }
    public void setCorreoReferente(String correoReferente) { this.correoReferente = correoReferente; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
