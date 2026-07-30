package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "secuencia_id", schema = "sicat")
public class SecuenciaId {

    @Id
    @Column(name = "prefijo", length = 5)
    private String prefijo;

    @Column(name = "ultimo_numero", nullable = false)
    private Integer ultimoNumero;

    public SecuenciaId() {}

    public String getPrefijo() { return prefijo; }
    public void setPrefijo(String prefijo) { this.prefijo = prefijo; }

    public Integer getUltimoNumero() { return ultimoNumero; }
    public void setUltimoNumero(Integer ultimoNumero) { this.ultimoNumero = ultimoNumero; }
}
