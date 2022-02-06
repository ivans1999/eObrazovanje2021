import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ExamPartType } from '../model/examPartType';

@Injectable({
  providedIn: 'root'
})
export class ExamPartTypeService {

  private examPartType = 'api/exam-part-type';

  constructor(private http: HttpClient) { }

  getExamPartType(): Observable<HttpResponse<ExamPartType[]>>{
    return this.http.get<ExamPartType[]>(this.examPartType, {observe: 'response'});
  }
}
