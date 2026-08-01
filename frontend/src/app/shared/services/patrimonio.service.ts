import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patrimonio } from '../models/patrimonio.model';

@Injectable({
  providedIn: 'root'
})
export class PatrimonioService {
  private baseUrl = 'http://localhost:8080/api/patrimonios';

  constructor(private http: HttpClient) {}

  crear(patrimonio: Patrimonio): Observable<Patrimonio> {
    return this.http.post<Patrimonio>(this.baseUrl, patrimonio);
  }

  actualizar(idEstab: string, patrimonio: Patrimonio): Observable<Patrimonio> {
    return this.http.put<Patrimonio>(`${this.baseUrl}/${idEstab}`, patrimonio);
  }

  obtener(idEstab: string): Observable<Patrimonio> {
    return this.http.get<Patrimonio>(`${this.baseUrl}/${idEstab}`);
  }

  listar(): Observable<Patrimonio[]> {
    return this.http.get<Patrimonio[]>(this.baseUrl);
  }

  buscarPorSubcategoria(valor: string): Observable<Patrimonio[]> {
    return this.http.get<Patrimonio[]>(`${this.baseUrl}/subcategoria`, { params: { valor } });
  }

  buscarPorTipo(valor: string): Observable<Patrimonio[]> {
    return this.http.get<Patrimonio[]>(`${this.baseUrl}/tipo`, { params: { valor } });
  }
}
