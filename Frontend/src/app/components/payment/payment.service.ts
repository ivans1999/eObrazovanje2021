import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { Payment } from "src/app/model/payment";
import { AuthenticationService } from "src/app/services/authentication.service";
import { StudentService } from "../student/student.service";

@Injectable()
export class PaymentService {
    private paymentsUrl = 'api/payment';
    private accountUrl = 'api/account';

    constructor(private http: HttpClient,private authS:AuthenticationService,private studentS:StudentService) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getAccountPayments(numberPage:number): Observable<HttpResponse<Payment[]>> {
        return this.http.get<Payment[]>(`${this.paymentsUrl}?sort=datePayment,desc&page=${numberPage}&size=5`, {observe: 'response'});
    }

    getNumberPage(): Observable<HttpResponse<number>> {
        // console.log("\ngetNumberPage")
        var user = this.authS.getLoggedUser();
        var username = JSON.stringify(user.sub).split('"')[1];
        return this.http.get<number>(`${this.paymentsUrl}/number-payments?username=${username}`, {observe: 'response'});
      }

    getNumberPage2(username:string): Observable<HttpResponse<number>> {
        if(this.authS.getRole() === "ROLE_STUDENT"){
            var user = this.authS.getLoggedUser();
            var username = JSON.stringify(user.sub).split('"')[1];
        }
    
        return this.http.get<number>(`${this.paymentsUrl}/number-payments?username=${username}`, {observe: 'response'});
    }

    getPayment(id: number): Observable<HttpResponse<Payment>> {
        const url = `${this.paymentsUrl}/${id}`;
        return this.http.get<Payment>(url, {observe: 'response'});
    }

    addAccountPayment(payment: Payment,username:string): Observable<HttpResponse<Payment>>{
        var mode = "STUDENT";
        if(this.authS.getRole()==="ROLE_ADMINISTRATOR"){
            mode = "ADMIN";
        }
        const url = `${this.paymentsUrl}?mode=${mode}&username=${username}`
        // console.log("Url: "+url)
        return this.http.post<Payment>(url, payment, {observe:'response'});
    }

    editPayment(payment: Payment): Observable<HttpResponse<Payment>> {
        return this.http.put<Payment>(this.paymentsUrl, payment, {observe: 'response'});
    }

    getStudentPayments(username:string, numberPage:number): Observable<HttpResponse<Payment[]>>{
        var url = `${this.paymentsUrl}/for-student/${username}?sort=datePayment,desc&page=${numberPage}&size=5`;
        return this.http.get<Payment[]>(url, {observe: 'response'});
    }
}