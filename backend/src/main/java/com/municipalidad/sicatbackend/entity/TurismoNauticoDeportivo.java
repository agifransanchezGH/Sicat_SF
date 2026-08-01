package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "turismo_nautico_deportivo", schema = "sicat")
public class TurismoNauticoDeportivo {

    @Id
    @Column(name = "id_estab", length = 20)
    private String idEstab;

    @Column(name = "subcategoria_nau", length = 100)
    private String subcategoriaNau;

    @Column(name = "funcionamiento", length = 30)
    private String funcionamiento;

    @Column(name = "deportes", length = 300)
    private String deportes;

    @Column(name = "instalaciones", length = 500)
    private String instalaciones;

    @Column(name = "servicios_club", length = 300)
    private String serviciosClub;

    @Column(name = "requisitos_admision", length = 300)
    private String requisitosAdmision;

    @Column(name = "deportes_nauticos", length = 300)
    private String deportesNauticos;

    @Column(name = "cant_embarcaciones")
    private Short cantEmbarcaciones;

    @Column(name = "servicios_nauticos", length = 300)
    private String serviciosNauticos;

    @Column(name = "servicios_activ_nau", length = 300)
    private String serviciosActivNau;

    @Column(name = "cant_equipo_nautico")
    private Short cantEquipoNautico;

    @Column(name = "servicios_guarderia", length = 300)
    private String serviciosGuarderia;

    @Column(name = "actividades_recreacion", length = 200)
    private String actividadesRecreacion;

    @Column(name = "cant_amarres")
    private Short cantAmarres;

    @Column(name = "servicios_parador", length = 400)
    private String serviciosParador;

    @Column(name = "acceso_playa", length = 100)
    private String accesoPlaya;

    @Column(name = "temporada", length = 100)
    private String temporada;

    @Column(name = "productos_pesca", length = 300)
    private String productosPesca;

    @Column(name = "tipo_pesca", length = 200)
    private String tipoPesca;

    public TurismoNauticoDeportivo() {}

    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public String getSubcategoriaNau() { return subcategoriaNau; }
    public void setSubcategoriaNau(String subcategoriaNau) { this.subcategoriaNau = subcategoriaNau; }

    public String getFuncionamiento() { return funcionamiento; }
    public void setFuncionamiento(String funcionamiento) { this.funcionamiento = funcionamiento; }

    public String getDeportes() { return deportes; }
    public void setDeportes(String deportes) { this.deportes = deportes; }

    public String getInstalaciones() { return instalaciones; }
    public void setInstalaciones(String instalaciones) { this.instalaciones = instalaciones; }

    public String getServiciosClub() { return serviciosClub; }
    public void setServiciosClub(String serviciosClub) { this.serviciosClub = serviciosClub; }

    public String getRequisitosAdmision() { return requisitosAdmision; }
    public void setRequisitosAdmision(String requisitosAdmision) { this.requisitosAdmision = requisitosAdmision; }

    public String getDeportesNauticos() { return deportesNauticos; }
    public void setDeportesNauticos(String deportesNauticos) { this.deportesNauticos = deportesNauticos; }

    public Short getCantEmbarcaciones() { return cantEmbarcaciones; }
    public void setCantEmbarcaciones(Short cantEmbarcaciones) { this.cantEmbarcaciones = cantEmbarcaciones; }

    public String getServiciosNauticos() { return serviciosNauticos; }
    public void setServiciosNauticos(String serviciosNauticos) { this.serviciosNauticos = serviciosNauticos; }

    public String getServiciosActivNau() { return serviciosActivNau; }
    public void setServiciosActivNau(String serviciosActivNau) { this.serviciosActivNau = serviciosActivNau; }

    public Short getCantEquipoNautico() { return cantEquipoNautico; }
    public void setCantEquipoNautico(Short cantEquipoNautico) { this.cantEquipoNautico = cantEquipoNautico; }

    public String getServiciosGuarderia() { return serviciosGuarderia; }
    public void setServiciosGuarderia(String serviciosGuarderia) { this.serviciosGuarderia = serviciosGuarderia; }

    public String getActividadesRecreacion() { return actividadesRecreacion; }
    public void setActividadesRecreacion(String actividadesRecreacion) { this.actividadesRecreacion = actividadesRecreacion; }

    public Short getCantAmarres() { return cantAmarres; }
    public void setCantAmarres(Short cantAmarres) { this.cantAmarres = cantAmarres; }

    public String getServiciosParador() { return serviciosParador; }
    public void setServiciosParador(String serviciosParador) { this.serviciosParador = serviciosParador; }

    public String getAccesoPlaya() { return accesoPlaya; }
    public void setAccesoPlaya(String accesoPlaya) { this.accesoPlaya = accesoPlaya; }

    public String getTemporada() { return temporada; }
    public void setTemporada(String temporada) { this.temporada = temporada; }

    public String getProductosPesca() { return productosPesca; }
    public void setProductosPesca(String productosPesca) { this.productosPesca = productosPesca; }

    public String getTipoPesca() { return tipoPesca; }
    public void setTipoPesca(String tipoPesca) { this.tipoPesca = tipoPesca; }
}
