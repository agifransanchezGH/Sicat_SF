import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MuseoSalaCultural } from '../models/museo-sala-cultural.model';

@Injectable({
  providedIn: 'root'
})
export class MuseoSalaCulturalService {
  private baseUrl = 'http://localhost:8080/api/museos';

  constructor(private http: HttpClient) {}

  crear(museo: MuseoSalaCultural): Observable<MuseoSalaCultural> {
    return this.http.post<MuseoSalaCultural>(this.baseUrl, museo);
  }

  actualizar(idEstab: string, museo: MuseoSalaCultural): Observable<MuseoSalaCultural> {
    return this.http.put<MuseoSalaCultural>(`${this.baseUrl}/${idEstab}`, museo);
  }

  obtener(idEstab: string): Observable<MuseoSalaCultural> {
    return this.http.get<MuseoSalaCultural>(`${this.baseUrl}/${idEstab}`);
  }

  listar(): Observable<MuseoSalaCultural[]> {
    return this.http.get<MuseoSalaCultural[]>(this.baseUrl);
  }

  buscarPorDominio(valor: string): Observable<MuseoSalaCultural[]> {
    return this.http.get<MuseoSalaCultural[]>(`${this.baseUrl}/dominio`, { params: { valor } });
  }

  buscarPorFuncionamiento(valor: string): Observable<MuseoSalaCultural[]> {
    return this.http.get<MuseoSalaCultural[]>(`${this.baseUrl}/funcionamiento`, { params: { valor } });
  }

  buscarPorTipoEntrada(valor: string): Observable<MuseoSalaCultural[]> {
    return this.http.get<MuseoSalaCultural[]>(`${this.baseUrl}/entrada`, { params: { valor } });
  }
}