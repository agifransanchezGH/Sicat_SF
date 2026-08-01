import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RecreacionDiversion } from '../models/recreacion-diversion.model';

@Injectable({
  providedIn: 'root'
})
export class RecreacionDiversionService {
  private baseUrl = 'http://localhost:8080/api/recreacion-diversion';

  constructor(private http: HttpClient) {}

  crear(payload: RecreacionDiversion): Observable<RecreacionDiversion> {
    return this.http.post<RecreacionDiversion>(this.baseUrl, payload);
  }

  actualizar(idEstab: string, payload: RecreacionDiversion): Observable<RecreacionDiversion> {
    return this.http.put<RecreacionDiversion>(`${this.baseUrl}/${idEstab}`, payload);
  }

  obtener(idEstab: string): Observable<RecreacionDiversion> {
    return this.http.get<RecreacionDiversion>(`${this.baseUrl}/${idEstab}`);
  }

  listar(): Observable<RecreacionDiversion[]> {
    return this.http.get<RecreacionDiversion[]>(this.baseUrl);
  }

  buscarPorSubcategoria(valor: string): Observable<RecreacionDiversion[]> {
    return this.http.get<RecreacionDiversion[]>(`${this.baseUrl}/subcategoria`, { params: { valor } });
  }

  buscarPorTipo(valor: string): Observable<RecreacionDiversion[]> {
    return this.http.get<RecreacionDiversion[]>(`${this.baseUrl}/tipo`, { params: { valor } });
  }
}
