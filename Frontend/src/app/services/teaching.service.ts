import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Teaching } from '../model/teaching';

@Injectable({
  providedIn: 'root'
})
export class TeachingService {

  private teachingUrl = 'api/teaching';

  constructor(private http: HttpClient) { }

  addTeaching(teaching: Teaching): Observable<HttpResponse<Teaching>> {
    return this.http.post<Teaching>(this.teachingUrl, teaching, {observe: 'response'});
}
}
