import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { Student } from "src/app/model/student";

@Injectable()
export class StudentService {
    private studentsUrl = 'api/student';
    student:Student;

    constructor(private http: HttpClient) {
        this.student = new Student({
            id:0,
            cardNumber: '',
            userDTO:{
              id:0,
              firstName:'',
              lastName:'',
              userName:'',
              password:'',
              roles:[]
            }
          })
     }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getStudents(numberPage:number): Observable<HttpResponse<Student[]>>{
        const url = `${this.studentsUrl}?page=${numberPage}&size=5`
        return this.http.get<Student[]>(url, {observe:'response'});
    }

    deleteStudent(stId: number): Observable<HttpResponse<any>>{
        const url = `${this.studentsUrl}/${stId}`;
        return this.http.delete<any>(url, {observe:'response'});
    }

    setStudent(student:Student){
        this.student = student;
    }
}