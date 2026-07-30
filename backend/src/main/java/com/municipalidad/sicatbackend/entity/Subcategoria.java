package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "subcategoria",
    schema = "sicat",
    uniqueConstraints = @UniqueConstraint(columnNames = {"id_cat", "nombre_subcat"})
)
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subcat")
    private Integer idSubcat;

    @Column(name = "id_cat", nullable = false)
    private Integer idCat;

    @Column(name = "nombre_subcat", nullable = false, length = 150)
    private String nombreSubcat;

    @Column(name = "prefijo_id", length = 5)
    private String prefijoId;

    // --- Constructores ---
    public Subcategoria() {}

    // --- Getters y Setters ---
    public Integer getIdSubcat() { return idSubcat; }
    public void setIdSubcat(Integer idSubcat) { this.idSubcat = idSubcat; }

    public Integer getIdCat() { return idCat; }
    public void setIdCat(Integer idCat) { this.idCat = idCat; }

    public String getNombreSubcat() { return nombreSubcat; }
    public void setNombreSubcat(String nombreSubcat) { this.nombreSubcat = nombreSubcat; }

    public String getPrefijoId() { return prefijoId; }
    public void setPrefijoId(String prefijoId) { this.prefijoId = prefijoId; }
}