import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TurismoNauticoDeportivo } from '../models/turismo-nautico-deportivo.model';

@Injectable({
  providedIn: 'root'
})
export class TurismoNauticoDeportivoService {
  private apiUrl = '/api/turismo_nautico_deportivo';

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<TurismoNauticoDeportivo[]> {
    return this.http.get<TurismoNauticoDeportivo[]>(this.apiUrl);
  }

  obtenerPorId(idEstab: string): Observable<TurismoNauticoDeportivo> {
    return this.http.get<TurismoNauticoDeportivo>(`${this.apiUrl}/${idEstab}`);
  }

  crear(turismo: TurismoNauticoDeportivo): Observable<TurismoNauticoDeportivo> {
    return this.http.post<TurismoNauticoDeportivo>(this.apiUrl, turismo);
  }

  actualizar(idEstab: string, turismo: TurismoNauticoDeportivo): Observable<TurismoNauticoDeportivo> {
    return this.http.put<TurismoNauticoDeportivo>(`${this.apiUrl}/${idEstab}`, turismo);
  }

  buscarPorSubcategoria(subcategoria: string): Observable<TurismoNauticoDeportivo[]> {
    return this.http.get<TurismoNauticoDeportivo[]>(`${this.apiUrl}/subcategoria/${subcategoria}`);
  }

  buscarPorTipo(tipo: string): Observable<TurismoNauticoDeportivo[]> {
    return this.http.get<TurismoNauticoDeportivo[]>(`${this.apiUrl}/tipo/${tipo}`);
  }

  buscarPorPuerto(puerto: string): Observable<TurismoNauticoDeportivo[]> {
    return this.http.get<TurismoNauticoDeportivo[]>(`${this.apiUrl}/puerto/${puerto}`);
  }
}
