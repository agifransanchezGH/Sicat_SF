import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Relevamiento } from '../models/relevamiento.model';

@Injectable({
  providedIn: 'root'
})
export class RelevamientoService {
  private baseUrl = 'http://localhost:8080/api/relevamientos';

  constructor(private http: HttpClient) {}

  registrar(relevamiento: Relevamiento): Observable<Relevamiento> {
    return this.http.post<Relevamiento>(this.baseUrl, relevamiento);
  }

  listar(): Observable<Relevamiento[]> {
    return this.http.get<Relevamiento[]>(this.baseUrl);
  }

  listarPorEstablecimiento(idEstab: string): Observable<Relevamiento[]> {
    return this.http.get<Relevamiento[]>(`${this.baseUrl}/establecimiento/${idEstab}`);
  }

  listarPorEstado(valor: string): Observable<Relevamiento[]> {
    return this.http.get<Relevamiento[]>(`${this.baseUrl}/estado`, { params: { valor } });
  }

  listarPorTecnico(valor: string): Observable<Relevamiento[]> {
    return this.http.get<Relevamiento[]>(`${this.baseUrl}/tecnico`, { params: { valor } });
  }
}
