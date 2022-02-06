import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, JsonpClientBackend } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { JWT } from '../model/jwt';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  private jwt: JWT={value:''};

  constructor(private inj: Injector)  { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //let authenticationService:AuthenticationService = this.inj.get(AuthenticationService); 
    var j = localStorage.getItem('loggedUser')?.split(":");
    var l = j === undefined ? 0:j?.length
    var token = j===undefined?'':j[l-1].split("\"")[1];
    this.jwt = token==null ? this.jwt:{value:token};
    req = req.clone({
      setHeaders: {
        'X-Auth-Token': this.jwt.value
      }
    });

    return next.handle(req);

  }
}
