import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { ExamPart } from "src/app/model/examPart";
import { AuthenticationService } from "src/app/services/authentication.service";
import { StudentService } from "../student/student.service";

@Injectable()
export class ExamPartService{
    private examDetailUrl = 'api/exam-part';
    private courseId:number = 0;

    constructor(private http: HttpClient,private auths:AuthenticationService,private studentS:StudentService) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange(){
        this.RegenerateData.next();
    }

    getExamParts(courseId: number,mode:string,numberPage:number): Observable<HttpResponse<ExamPart[]>>{
        console.log("Get exam parts!")
        var url = ``;
        this.courseId = courseId;
        if(mode==='TEACHER_EXAM_DETAIL'){
            url = `${this.examDetailUrl}/teacher?sort=date_exam_part,desc&page=${numberPage}&size=5`;
        }
        else if(this.auths.getRole()==='ROLE_ADMINISTRATOR' || this.auths.getRole()==='ROLE_TEACHER'){
            url = `${this.examDetailUrl}/course-instance/${courseId}?sort=date_exam_part,desc&page=${numberPage}&size=5`;
        }else if(this.auths.getRole() === 'ROLE_STUDENT'){
            url = `${this.examDetailUrl}/student/${courseId}?sort=date,desc&page=${numberPage}&size=5`;
        }
        return this.http.get<ExamPart[]>(url, {observe: 'response'});
    }

    getNumberPage(mode:string,code:string): Observable<HttpResponse<number>> {
        console.log("\ngetNumberPage")
        var username = 'undefined';
        if(mode === 'STUDENT_EXAM_DETAIL'){
            username = this.studentS.student.userDTO.userName;
        }
        else if(this.auths.getRole()==='ROLE_TEACHER' || this.auths.getRole()==='ROLE_STUDENT'){
            var user = this.auths.getLoggedUser();
            username = JSON.stringify(user.sub).split('"')[1];
        }
        return this.http.get<number>(`${this.examDetailUrl}/number-exam-part?mode=${mode}&username=${username}&courseId=${this.courseId}&code=${code}`, {observe: 'response'});
      }

    // getExamPartsForTeacher(): Observable<HttpResponse<ExamPart[]>>{
    //     var url = `${this.examDetailUrl}/teacher`;
    //     return this.http.get<ExamPart[]>(url, {observe: 'response'});
    // }

    deleteExamPart(examPartId: number): Observable<HttpResponse<any>> {
        const url = `${this.examDetailUrl}/${examPartId}`;
        return this.http.delete<any>(url, {observe: 'response'});
    }

    getExamPartsForStudent(courseId:number,cardNumber: string,numberPage:number): Observable<HttpResponse<ExamPart[]>>{
        console.log('getExamPartsForStudent...')
        this.courseId = courseId;
        var url = `${this.examDetailUrl}/${courseId}/${cardNumber}?sort=date,desc&page=${numberPage}&size=5`;
        return this.http.get<ExamPart[]>(url, {observe: 'response'});
    }
    
    addExamPart(examPart: ExamPart): Observable<HttpResponse<ExamPart>> {
        return this.http.post<ExamPart>(this.examDetailUrl, examPart, {observe: 'response'});
    }

    editExamPart(examPart: ExamPart): Observable<HttpResponse<ExamPart>> {
        const url = `${this.examDetailUrl}/one-exam-part`
        return this.http.put<ExamPart>(url, examPart, {observe: 'response'});
    }

    registeUnregisterExamPart(examPart: ExamPart): Observable<HttpResponse<ExamPart>> {
        const url = `${this.examDetailUrl}/register-unregister-exam-part`
        return this.http.put<ExamPart>(url, examPart, {observe: 'response'});
    }

    getExamPart(id: number): Observable<HttpResponse<ExamPart>> {
        const url = `${this.examDetailUrl}/${id}`;
        return this.http.get<ExamPart>(url, {observe: 'response'});
    }

    getExamPartsByCode(numberPage:number,code: string): Observable<HttpResponse<ExamPart[]>> {
        const url = `${this.examDetailUrl}/registered-exam-parts/${code}?page=${numberPage}&size=5`;
        return this.http.get<ExamPart[]>(url, {observe: 'response'});
    }

    getExamPartByCode(code: string): Observable<HttpResponse<ExamPart>> {
        const url = `${this.examDetailUrl}/by-code/${code}`;
        return this.http.get<ExamPart>(url, {observe: 'response'});
    }

    evaluationExamParts(examParts: ExamPart[]): Observable<HttpResponse<ExamPart[]>> {
        const url = `${this.examDetailUrl}/evaluation-exam-parts`
        return this.http.put<ExamPart[]>(url, examParts, {observe: 'response'});
    }

    getCourseId(){
        return this.courseId;
    }
}