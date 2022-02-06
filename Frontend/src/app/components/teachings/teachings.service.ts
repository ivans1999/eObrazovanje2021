import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { JWT } from "src/app/model/jwt";
import { Teaching } from "src/app/model/teaching";

@Injectable()
export class TeachingsService {
    private teachingsUrl = "api/teaching"

    constructor(private http: HttpClient){ }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange(){
        this.RegenerateData.next();
    }

    getTeachings(): Observable<HttpResponse<Teaching[]>> {
        return this.http.get<Teaching[]>(this.teachingsUrl, {observe: 'response'});
    }
}