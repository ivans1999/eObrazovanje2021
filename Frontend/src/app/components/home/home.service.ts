import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { ChangePass } from "src/app/model/changePass";
import { User } from "src/app/model/user";

@Injectable()
export class HomeService {

    private usersUrl = 'api/users';

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    // editUser(user: User): Observable<HttpResponse<User>> {
    //     return this.http.put<User>(this.usersUrl, user, {observe: 'response'});
    // }

    
}