import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subcategoria } from '../models/subcategoria.model';

@Injectable({
  providedIn: 'root'
})
export class SubcategoriaService {
  private baseUrl = 'http://localhost:8080/api/subcategorias';

  constructor(private http: HttpClient) {}

  listar(): Observable<Subcategoria[]> {
    return this.http.get<Subcategoria[]>(this.baseUrl);
  }

  // Llamado al seleccionar una categoría → alimenta el dropdown de subcategorías
  listarPorCategoria(idCat: number): Observable<Subcategoria[]> {
    return this.http.get<Subcategoria[]>(this.baseUrl, { params: { idCat } });
  }

  crear(subcategoria: Subcategoria): Observable<Subcategoria> {
    return this.http.post<Subcategoria>(this.baseUrl, subcategoria);
  }
}