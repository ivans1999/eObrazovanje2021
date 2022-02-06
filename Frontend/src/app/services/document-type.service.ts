import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TypeDocument } from '../model/typeDocument';

@Injectable({
  providedIn: 'root'
})
export class DocumentTypeService {

  private examPartType = 'api/type-document';

  constructor(private http: HttpClient) { }

  getDocumentTypes(): Observable<HttpResponse<TypeDocument[]>>{
    return this.http.get<TypeDocument[]>(this.examPartType, {observe: 'response'});
  }
}
