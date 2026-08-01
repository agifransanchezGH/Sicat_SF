export interface Patrimonio {
  idEstab: string;
  subcategoriaPatrimonio?: string;
  tipoPatrimonio?: string;
  descripcion?: string;
  accesoPublico?: boolean;
  horarioVisitas?: string;
  valorCultural?: string;
  espacioCulturalNombre?: string;
  espacioCulturalCapacidad?: number;
  espacioCulturalServicios?: string;
  destinoEducativo?: boolean;
}
