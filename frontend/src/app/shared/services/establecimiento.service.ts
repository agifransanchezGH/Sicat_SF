import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Establecimiento } from '../models/establecimiento.model';

@Injectable({
  providedIn: 'root'
})
export class EstablecimientoService {
  private baseUrl = 'http://localhost:8080/api/establecimientos';

  constructor(private http: HttpClient) {}

  listar(): Observable<Establecimiento[]> {
    return this.http.get<Establecimiento[]>(this.baseUrl);
  }

  buscar(nombre: string): Observable<Establecimiento[]> {
    return this.http.get<Establecimiento[]>(`${this.baseUrl}/buscar`, {
      params: { nombre }
    });
  }

  porCategoria(idCat: number): Observable<Establecimiento[]> {
    return this.http.get<Establecimiento[]>(`${this.baseUrl}/categoria/${idCat}`);
  }

  crear(establecimiento: Establecimiento): Observable<Establecimiento> {
    return this.http.post<Establecimiento>(this.baseUrl, establecimiento);
  }
}
