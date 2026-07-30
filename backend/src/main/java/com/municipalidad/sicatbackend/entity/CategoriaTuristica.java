package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria_turistica", schema = "sicat")
public class CategoriaTuristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat")
    private Integer idCat;

    @Column(name = "prefijo", nullable = false, unique = true, length = 5)
    private String prefijo;

    @Column(name = "nombre_categoria", nullable = false, unique = true, length = 100)
    private String nombreCategoria;

    // --- Constructores ---
    public CategoriaTuristica() {}

    // --- Getters y Setters ---
    public Integer getIdCat() { return idCat; }
    public void setIdCat(Integer idCat) { this.idCat = idCat; }

    public String getPrefijo() { return prefijo; }
    public void setPrefijo(String prefijo) { this.prefijo = prefijo; }

    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
}