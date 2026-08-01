package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patrimonio", schema = "sicat")
public class Patrimonio {

    @Id
    @Column(name = "id_estab", length = 20)
    private String idEstab;

    @Column(name = "subcategoria_patrimonio", length = 100)
    private String subcategoriaPatrimonio;

    @Column(name = "tipo_patrimonio", length = 100)
    private String tipoPatrimonio;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "acceso_publico")
    private Boolean accesoPublico;

    @Column(name = "horario_visitas", length = 150)
    private String horarioVisitas;

    @Column(name = "valor_cultural", length = 200)
    private String valorCultural;

    @Column(name = "espacio_cultural_nombre", length = 150)
    private String espacioCulturalNombre;

    @Column(name = "espacio_cultural_capacidad")
    private Short espacioCulturalCapacidad;

    @Column(name = "espacio_cultural_servicios", length = 250)
    private String espacioCulturalServicios;

    @Column(name = "destino_educativo")
    private Boolean destinoEducativo;

    public Patrimonio() {}

    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public String getSubcategoriaPatrimonio() { return subcategoriaPatrimonio; }
    public void setSubcategoriaPatrimonio(String subcategoriaPatrimonio) { this.subcategoriaPatrimonio = subcategoriaPatrimonio; }

    public String getTipoPatrimonio() { return tipoPatrimonio; }
    public void setTipoPatrimonio(String tipoPatrimonio) { this.tipoPatrimonio = tipoPatrimonio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Boolean getAccesoPublico() { return accesoPublico; }
    public void setAccesoPublico(Boolean accesoPublico) { this.accesoPublico = accesoPublico; }

    public String getHorarioVisitas() { return horarioVisitas; }
    public void setHorarioVisitas(String horarioVisitas) { this.horarioVisitas = horarioVisitas; }

    public String getValorCultural() { return valorCultural; }
    public void setValorCultural(String valorCultural) { this.valorCultural = valorCultural; }

    public String getEspacioCulturalNombre() { return espacioCulturalNombre; }
    public void setEspacioCulturalNombre(String espacioCulturalNombre) { this.espacioCulturalNombre = espacioCulturalNombre; }

    public Short getEspacioCulturalCapacidad() { return espacioCulturalCapacidad; }
    public void setEspacioCulturalCapacidad(Short espacioCulturalCapacidad) { this.espacioCulturalCapacidad = espacioCulturalCapacidad; }

    public String getEspacioCulturalServicios() { return espacioCulturalServicios; }
    public void setEspacioCulturalServicios(String espacioCulturalServicios) { this.espacioCulturalServicios = espacioCulturalServicios; }

    public Boolean getDestinoEducativo() { return destinoEducativo; }
    public void setDestinoEducativo(Boolean destinoEducativo) { this.destinoEducativo = destinoEducativo; }
}
