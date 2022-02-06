import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ExamPartStatus } from '../model/examPartStatus';

@Injectable({
  providedIn: 'root'
})
export class ExamPartStatusService {

  private examPartType = 'api/exam-part-status';

  constructor(private http: HttpClient) { }

  getExamPartStatus(): Observable<HttpResponse<ExamPartStatus[]>>{
    return this.http.get<ExamPartStatus[]>(this.examPartType, {observe: 'response'});
  }
}
