export interface Alojamiento {
  idEstab: string;
  tipoAlojamiento?: string;
  reformasUltimos4Anios?: boolean;
  observacionesReforma?: string;
  cantHabitaciones?: number;
  tiposHabitaciones?: string;       // pipe-separated: Single|Doble|Triple|Suite
  totalPlazas?: number;
  cantHabAccesibles?: number;
  cantPlazasAccesibles?: number;
  serviciosGenerales?: string;      // pipe-separated: WiFi|Desayuno|Piscina
  salaEventosNombreCap?: string;
  equipamientoHabitacion?: string;  // pipe-separated: TV|Aire acondicionado|Frigobar
  sistemaInfoCalidad?: string;      // pipe-separated: Encuesta|Estadísticas mensuales
}
