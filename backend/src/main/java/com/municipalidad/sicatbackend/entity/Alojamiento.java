package com.municipalidad.sicatbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "alojamiento", schema = "sicat")
public class Alojamiento {

    /*
     * id_estab es PK y FK a establecimiento a la vez.
     * No se usa @GeneratedValue: el ID lo asigna EstablecimientoService
     * antes del INSERT y se recibe desde el cliente al guardar el detalle.
     */
    @Id
    @Column(name = "id_estab", length = 20)
    private String idEstab;

    // ── Clasificación ─────────────────────────────────────────────────────────

    @Column(name = "tipo_alojamiento", length = 60)
    private String tipoAlojamiento;
    // Valores esperados (no constraint en entidad, sí en DB):
    // Hotel 1 estrella | Hotel 2 estrellas | Hotel 3 estrellas |
    // Hotel 4 estrellas | Hotel 5 estrellas | Hotel Boutique |
    // Apart Hotel | Hostel | Delegación · Complejo Social | Camping | etc.

    @Column(name = "reformas_ultimos_4_anios")
    private Boolean reformasUltimos4Anios;

    @Column(name = "observaciones_reforma", length = 300)
    private String observacionesReforma;

    // ── Capacidad ─────────────────────────────────────────────────────────────

    @Column(name = "cant_habitaciones")
    private Integer cantHabitaciones;

    @Column(name = "tipos_habitaciones", length = 300)
    private String tiposHabitaciones;
    // pipe-separated: Single|Doble|Triple|Suite|Accesibles|...

    @Column(name = "total_plazas")
    private Integer totalPlazas;

    @Column(name = "cant_hab_accesibles")
    private Short cantHabAccesibles;

    @Column(name = "cant_plazas_accesibles")
    private Short cantPlazasAccesibles;

    // ── Servicios ─────────────────────────────────────────────────────────────

    @Column(name = "servicios_generales", length = 500)
    private String serviciosGenerales;
    // pipe-separated: WiFi|Desayuno|Piscina|Estacionamiento|Spa|...

    @Column(name = "sala_eventos_nombre_cap", length = 300)
    private String salaEventosNombreCap;
    // Texto libre: "Salón Principal · Cap: 150 personas"

    @Column(name = "equipamiento_habitacion", length = 300)
    private String equipamientoHabitacion;
    // pipe-separated: TV|Aire acondicionado|Frigobar|WiFi|Caja de seguridad|...

    // ── Sistema de información y calidad ──────────────────────────────────────

    @Column(name = "sistema_info_calidad", length = 400)
    private String sistemaInfoCalidad;
    // pipe-separated: Encuesta satisfacción|Estadísticas mensuales|Distinción de calidad|...
    // Las distinciones detalladas se registran en la tabla distincion_calidad (1:N)

    // ── Constructores ─────────────────────────────────────────────────────────

    public Alojamiento() {}

    // ── Getters y Setters ─────────────────────────────────────────────────────

    public String getIdEstab() { return idEstab; }
    public void setIdEstab(String idEstab) { this.idEstab = idEstab; }

    public String getTipoAlojamiento() { return tipoAlojamiento; }
    public void setTipoAlojamiento(String tipoAlojamiento) { this.tipoAlojamiento = tipoAlojamiento; }

    public Boolean getReformasUltimos4Anios() { return reformasUltimos4Anios; }
    public void setReformasUltimos4Anios(Boolean reformasUltimos4Anios) { this.reformasUltimos4Anios = reformasUltimos4Anios; }

    public String getObservacionesReforma() { return observacionesReforma; }
    public void setObservacionesReforma(String observacionesReforma) { this.observacionesReforma = observacionesReforma; }

    public Integer getCantHabitaciones() { return cantHabitaciones; }
    public void setCantHabitaciones(Integer cantHabitaciones) { this.cantHabitaciones = cantHabitaciones; }

    public String getTiposHabitaciones() { return tiposHabitaciones; }
    public void setTiposHabitaciones(String tiposHabitaciones) { this.tiposHabitaciones = tiposHabitaciones; }

    public Integer getTotalPlazas() { return totalPlazas; }
    public void setTotalPlazas(Integer totalPlazas) { this.totalPlazas = totalPlazas; }

    public Short getCantHabAccesibles() { return cantHabAccesibles; }
    public void setCantHabAccesibles(Short cantHabAccesibles) { this.cantHabAccesibles = cantHabAccesibles; }

    public Short getCantPlazasAccesibles() { return cantPlazasAccesibles; }
    public void setCantPlazasAccesibles(Short cantPlazasAccesibles) { this.cantPlazasAccesibles = cantPlazasAccesibles; }

    public String getServiciosGenerales() { return serviciosGenerales; }
    public void setServiciosGenerales(String serviciosGenerales) { this.serviciosGenerales = serviciosGenerales; }

    public String getSalaEventosNombreCap() { return salaEventosNombreCap; }
    public void setSalaEventosNombreCap(String salaEventosNombreCap) { this.salaEventosNombreCap = salaEventosNombreCap; }

    public String getEquipamientoHabitacion() { return equipamientoHabitacion; }
    public void setEquipamientoHabitacion(String equipamientoHabitacion) { this.equipamientoHabitacion = equipamientoHabitacion; }

    public String getSistemaInfoCalidad() { return sistemaInfoCalidad; }
    public void setSistemaInfoCalidad(String sistemaInfoCalidad) { this.sistemaInfoCalidad = sistemaInfoCalidad; }
}