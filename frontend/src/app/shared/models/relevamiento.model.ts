export interface Relevamiento {
  idRelev?: number;
  idEstab: string;
  fechaRelevamiento?: string; // formato ISO: YYYY-MM-DD
  tecnicoResponsable?: string;
  observacionesCarga?: string;
  estadoCarga?: string; // Pendiente | En proceso | Completo | Con errores
}
