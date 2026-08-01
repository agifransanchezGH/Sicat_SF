import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SalaEvento } from '../models/sala-evento.model';

@Injectable({
  providedIn: 'root'
})
export class SalaEventoService {
  private baseUrl = 'http://localhost:8080/api/salas-evento';

  constructor(private http: HttpClient) {}

  crear(salaEvento: SalaEvento): Observable<SalaEvento> {
    return this.http.post<SalaEvento>(this.baseUrl, salaEvento);
  }

  actualizar(idEstab: string, salaEvento: SalaEvento): Observable<SalaEvento> {
    return this.http.put<SalaEvento>(`${this.baseUrl}/${idEstab}`, salaEvento);
  }

  obtener(idEstab: string): Observable<SalaEvento> {
    return this.http.get<SalaEvento>(`${this.baseUrl}/${idEstab}`);
  }

  listar(): Observable<SalaEvento[]> {
    return this.http.get<SalaEvento[]>(this.baseUrl);
  }

  porPadre(idEstabPadre: string): Observable<SalaEvento[]> {
    return this.http.get<SalaEvento[]>(`${this.baseUrl}/padre/${idEstabPadre}`);
  }

  porTipo(tipoSala: string): Observable<SalaEvento[]> {
    return this.http.get<SalaEvento[]>(`${this.baseUrl}/tipo/${tipoSala}`);
  }
}
