import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServicioTuristico } from '../models/servicio-turistico.model';

@Injectable({
  providedIn: 'root'
})
export class ServicioTuristicoService {
  private baseUrl = 'http://localhost:8080/api/servicios-turisticos';

  constructor(private http: HttpClient) {}

  crear(servicio: ServicioTuristico): Observable<ServicioTuristico> {
    return this.http.post<ServicioTuristico>(this.baseUrl, servicio);
  }

  actualizar(idEstab: string, servicio: ServicioTuristico): Observable<ServicioTuristico> {
    return this.http.put<ServicioTuristico>(`${this.baseUrl}/${idEstab}`, servicio);
  }

  obtener(idEstab: string): Observable<ServicioTuristico> {
    return this.http.get<ServicioTuristico>(`${this.baseUrl}/${idEstab}`);
  }

  listar(): Observable<ServicioTuristico[]> {
    return this.http.get<ServicioTuristico[]>(this.baseUrl);
  }

  buscarPorSubcategoria(valor: string): Observable<ServicioTuristico[]> {
    return this.http.get<ServicioTuristico[]>(`${this.baseUrl}/subcategoria`, { params: { valor } });
  }

  buscarPorTipo(valor: string): Observable<ServicioTuristico[]> {
    return this.http.get<ServicioTuristico[]>(`${this.baseUrl}/tipo`, { params: { valor } });
  }

  buscarPorVehiculo(valor: string): Observable<ServicioTuristico[]> {
    return this.http.get<ServicioTuristico[]>(`${this.baseUrl}/vehiculo`, { params: { valor } });
  }
}
