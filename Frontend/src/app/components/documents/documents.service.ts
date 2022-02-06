import { formatDate } from "@angular/common";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { Document } from "src/app/model/document";
import { Url } from "src/app/model/url";
import { AuthenticationService } from "src/app/services/authentication.service";
import { StudentDetailComponent } from "../student-detail/student-detail.component";
import { StudentService } from "../student/student.service";

@Injectable()
export class DocumentsService {
    private documentUrl = 'api/document';

    constructor(private http: HttpClient, private auths: AuthenticationService,private studentS:StudentService){
        
    }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange(){
        this.RegenerateData.next();
    }

    getStudentDocuments(username:string,numberPage:number): Observable<HttpResponse<Document[]>>{
        var url = '';
        if(this.auths.getRole() === 'ROLE_ADMINISTRATOR'){
            url = `${this.documentUrl}/for-student/${username}?sort=title,asc&page=${numberPage}&size=5`;
        }else if(this.auths.getRole() === 'ROLE_STUDENT'){
            url = `${this.documentUrl}?sort=title,asc&page=${numberPage}&size=5`;
        }
        return this.http.get<Document[]>(url, {observe: 'response'});
    }

    getNumberPage(username:string): Observable<HttpResponse<number>> {
        if(this.auths.getRole() === "ROLE_STUDENT"){
            var user = this.auths.getLoggedUser();
            username = JSON.stringify(user.sub).split('"')[1];
        }
        const url = `${this.documentUrl}/number-documents?username=${username}`
        return this.http.get<number>(url, {observe: 'response'});
      }

    getDocument(id: number): Observable<HttpResponse<Document>>{
        const url = `${this.documentUrl}/${id}`;
        return this.http.get<Document>(url, {observe:'response'});
    }

    addFile(formData: FormData): Observable<HttpResponse<Url>> {
        console.log("Saljem fajl!")
        const url = `${this.documentUrl}/add-file`;
        return this.http.post<Url>(url, formData, {observe: 'response'});
    }

    downloadFile(u:string): Observable<Blob> {
        const formData = new FormData();
        formData.append("url", u);
        const url = `${this.documentUrl}/download`;
        return this.http.post(url,formData, {
            responseType: 'blob'
        })
    }

    addDocument(document: Document,username:string): Observable<HttpResponse<Document>> {
        console.log("Cuvam dokument!")
        const url = `${this.documentUrl}?username=${username}`
        return this.http.post<Document>(url, document, {observe: 'response'});
    }

    deleteDocument(documentId: number): Observable<HttpResponse<any>> {
        const url = `${this.documentUrl}/${documentId}`;
        return this.http.delete<any>(url, {observe: 'response'});
    }
}