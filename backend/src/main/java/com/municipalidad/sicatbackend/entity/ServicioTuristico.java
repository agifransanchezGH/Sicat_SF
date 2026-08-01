package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "servicio_turistico", schema = "sicat")
public class ServicioTuristico {

    @Id
    @Column(name = "id_estab", length = 20)
    private String idEstab;

    @Column(name = "subcategoria_servicio", length = 100)
    private String subcategoriaServicio;

    @Column(name = "nombre_servicio", length = 150)
    private String nombreServicio;

    @Column(name = "tipo_servicio", length = 100)
    private String tipoServicio;

    @Column(name = "contacto", length = 150)
    private String contacto;

    @Column(name = "link_reserva", length = 200)
    private String linkReserva;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "vehiculo_tipo", length = 100)
    private String vehiculoTipo;

    @Column(name = "zona_cobertura", length = 150)
    private String zonaCobertura;

    @Column(name = "horario_servicio", length = 150)
    private String horarioServicio;

    @Column(name = "lineas", length = 200)
    private String lineas;

    @Column(name = "frecuencia", length = 100)
    private String frecuencia;

    @Column(name = "tipo_ramal", length = 100)
    private String tipoRamal;

    @Column(name = "programas_ofrecidos", length = 300)
    private String programasOfrecidos;

    @Column(name = "acreditaciones", length = 300)
    private String acreditaciones;

    @Column(name = "cantidad_asesores")
    private Short cantidadAsesores;

    public ServicioTuristico() {}

    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public String getSubcategoriaServicio() { return subcategoriaServicio; }
    public void setSubcategoriaServicio(String subcategoriaServicio) { this.subcategoriaServicio = subcategoriaServicio; }

    public String getNombreServicio() { return nombreServicio; }
    public void setNombreServicio(String nombreServicio) { this.nombreServicio = nombreServicio; }

    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    public String getLinkReserva() { return linkReserva; }
    public void setLinkReserva(String linkReserva) { this.linkReserva = linkReserva; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getVehiculoTipo() { return vehiculoTipo; }
    public void setVehiculoTipo(String vehiculoTipo) { this.vehiculoTipo = vehiculoTipo; }

    public String getZonaCobertura() { return zonaCobertura; }
    public void setZonaCobertura(String zonaCobertura) { this.zonaCobertura = zonaCobertura; }

    public String getHorarioServicio() { return horarioServicio; }
    public void setHorarioServicio(String horarioServicio) { this.horarioServicio = horarioServicio; }

    public String getLineas() { return lineas; }
    public void setLineas(String lineas) { this.lineas = lineas; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public String getTipoRamal() { return tipoRamal; }
    public void setTipoRamal(String tipoRamal) { this.tipoRamal = tipoRamal; }

    public String getProgramasOfrecidos() { return programasOfrecidos; }
    public void setProgramasOfrecidos(String programasOfrecidos) { this.programasOfrecidos = programasOfrecidos; }

    public String getAcreditaciones() { return acreditaciones; }
    public void setAcreditaciones(String acreditaciones) { this.acreditaciones = acreditaciones; }

    public Short getCantidadAsesores() { return cantidadAsesores; }
    public void setCantidadAsesores(Short cantidadAsesores) { this.cantidadAsesores = cantidadAsesores; }
}
