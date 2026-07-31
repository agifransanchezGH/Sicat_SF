import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ContactoReferente } from '../models/contacto-referente.model';

@Injectable({
  providedIn: 'root'
})
export class ContactoReferenteService {
  private baseUrl = 'http://localhost:8080/api/contactos-referentes';

  constructor(private http: HttpClient) {}

  crear(contacto: ContactoReferente): Observable<ContactoReferente> {
    return this.http.post<ContactoReferente>(this.baseUrl, contacto);
  }

  actualizar(idRef: number, contacto: ContactoReferente): Observable<ContactoReferente> {
    return this.http.put<ContactoReferente>(`${this.baseUrl}/${idRef}`, contacto);
  }

  listarPorEstablecimiento(idEstab: string): Observable<ContactoReferente[]> {
    return this.http.get<ContactoReferente[]>(`${this.baseUrl}/establecimiento/${idEstab}`);
  }

  eliminar(idRef: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/${idRef}`);
  }
}
