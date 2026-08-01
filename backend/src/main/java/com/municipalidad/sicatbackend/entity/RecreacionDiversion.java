package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recreacion_diversion", schema = "sicat")
public class RecreacionDiversion {

    @Id
    @Column(name = "id_estab", length = 20)
    private String idEstab;

    @Column(name = "subcategoria_recreacion", length = 100)
    private String subcategoriaRecreacion;

    @Column(name = "tipo_servicio", length = 100)
    private String tipoServicio;

    @Column(name = "capacidad_personas")
    private Short capacidadPersonas;

    @Column(name = "acceso_adaptado")
    private Boolean accesoAdaptado;

    @Column(name = "cantidad_piscinas")
    private Short cantidadPiscinas;

    @Column(name = "acuatico_cubierto")
    private Boolean acuaticoCubierto;

    @Column(name = "cantidad_cancha")
    private Short cantidadCancha;

    @Column(name = "tipo_instalaciones_deportivas", length = 150)
    private String tipoInstalacionesDeportivas;

    @Column(name = "cantidad_salas_juegos")
    private Short cantidadSalasJuegos;

    @Column(name = "tiene_sala_escape")
    private Boolean tieneSalaEscape;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    public RecreacionDiversion() {}

    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public String getSubcategoriaRecreacion() { return subcategoriaRecreacion; }
    public void setSubcategoriaRecreacion(String subcategoriaRecreacion) { this.subcategoriaRecreacion = subcategoriaRecreacion; }

    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }

    public Short getCapacidadPersonas() { return capacidadPersonas; }
    public void setCapacidadPersonas(Short capacidadPersonas) { this.capacidadPersonas = capacidadPersonas; }

    public Boolean getAccesoAdaptado() { return accesoAdaptado; }
    public void setAccesoAdaptado(Boolean accesoAdaptado) { this.accesoAdaptado = accesoAdaptado; }

    public Short getCantidadPiscinas() { return cantidadPiscinas; }
    public void setCantidadPiscinas(Short cantidadPiscinas) { this.cantidadPiscinas = cantidadPiscinas; }

    public Boolean getAcuaticoCubierto() { return acuaticoCubierto; }
    public void setAcuaticoCubierto(Boolean acuaticoCubierto) { this.acuaticoCubierto = acuaticoCubierto; }

    public Short getCantidadCancha() { return cantidadCancha; }
    public void setCantidadCancha(Short cantidadCancha) { this.cantidadCancha = cantidadCancha; }

    public String getTipoInstalacionesDeportivas() { return tipoInstalacionesDeportivas; }
    public void setTipoInstalacionesDeportivas(String tipoInstalacionesDeportivas) { this.tipoInstalacionesDeportivas = tipoInstalacionesDeportivas; }

    public Short getCantidadSalasJuegos() { return cantidadSalasJuegos; }
    public void setCantidadSalasJuegos(Short cantidadSalasJuegos) { this.cantidadSalasJuegos = cantidadSalasJuegos; }

    public Boolean getTieneSalaEscape() { return tieneSalaEscape; }
    public void setTieneSalaEscape(Boolean tieneSalaEscape) { this.tieneSalaEscape = tieneSalaEscape; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
