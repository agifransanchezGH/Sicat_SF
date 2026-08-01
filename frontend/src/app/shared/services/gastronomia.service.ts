import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Gastronomia } from '../models/gastronomia.model';

@Injectable({
  providedIn: 'root'
})
export class GastronomiaService {
  private baseUrl = 'http://localhost:8080/api/gastronomia';

  constructor(private http: HttpClient) {}

  crear(gastronomia: Gastronomia): Observable<Gastronomia> {
    return this.http.post<Gastronomia>(this.baseUrl, gastronomia);
  }

  actualizar(idEstab: string, gastronomia: Gastronomia): Observable<Gastronomia> {
    return this.http.put<Gastronomia>(`${this.baseUrl}/${idEstab}`, gastronomia);
  }

  obtener(idEstab: string): Observable<Gastronomia> {
    return this.http.get<Gastronomia>(`${this.baseUrl}/${idEstab}`);
  }

  listar(): Observable<Gastronomia[]> {
    return this.http.get<Gastronomia[]>(this.baseUrl);
  }

  buscarPorSubcategoria(valor: string): Observable<Gastronomia[]> {
    return this.http.get<Gastronomia[]>(`${this.baseUrl}/subcategoria`, { params: { valor } });
  }

  buscarPorZona(valor: string): Observable<Gastronomia[]> {
    return this.http.get<Gastronomia[]>(`${this.baseUrl}/zona`, { params: { valor } });
  }

  buscarPorTipoCocina(valor: string): Observable<Gastronomia[]> {
    return this.http.get<Gastronomia[]>(`${this.baseUrl}/cocina`, { params: { valor } });
  }
}