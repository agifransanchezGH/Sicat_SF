export interface SalaEvento {
  idEstab: string;
  idEstabPadre?: string;
  nombreSala?: string;
  capacidadPersonas?: number;
  superficieM2?: number;
  tipoSala?: string;
  tieneAudio?: boolean;
  observaciones?: string;
}
