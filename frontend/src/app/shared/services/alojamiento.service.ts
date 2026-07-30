import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Alojamiento } from '../models/alojamiento.model';

@Injectable({
  providedIn: 'root'
})
export class AlojamientoService {
  private baseUrl = 'http://localhost:8080/api/alojamientos';

  constructor(private http: HttpClient) {}

  crear(alojamiento: Alojamiento): Observable<Alojamiento> {
    return this.http.post<Alojamiento>(this.baseUrl, alojamiento);
  }

  actualizar(idEstab: string, alojamiento: Alojamiento): Observable<Alojamiento> {
    return this.http.put<Alojamiento>(`${this.baseUrl}/${idEstab}`, alojamiento);
  }

  obtener(idEstab: string): Observable<Alojamiento> {
    return this.http.get<Alojamiento>(`${this.baseUrl}/${idEstab}`);
  }

  listar(): Observable<Alojamiento[]> {
    return this.http.get<Alojamiento[]>(this.baseUrl);
  }

  buscarPorTipo(valor: string): Observable<Alojamiento[]> {
    return this.http.get<Alojamiento[]>(`${this.baseUrl}/tipo`, { params: { valor } });
  }

  buscarAccesibles(): Observable<Alojamiento[]> {
    return this.http.get<Alojamiento[]>(`${this.baseUrl}/accesibles`);
  }

  buscarPorServicio(valor: string): Observable<Alojamiento[]> {
    return this.http.get<Alojamiento[]>(`${this.baseUrl}/servicio`, { params: { valor } });
  }
}
