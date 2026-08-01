export interface MuseoSalaCultural {
  idEstab: string;
  subcategoriaMuseo?: string;
  dominio?: string;         // Municipal | Provincial | Nacional | Privado | Mixto
  funcionamiento?: string;  // Abierto | Cerrado | Temporario | En reformas
  tipoEntrada?: string;     // Gratuita | Con cargo | Entrada voluntaria | Mixta
  visitasGuiadas?: string;
  descripcion?: string;
  observacionesMuseo?: string;
  coleccionPrincipal?: string;
  serviciosAdicionales?: string;
}