import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

	constructor(
		private http: HttpClient,
		private router: Router
	) { }

	login(auth: any): Observable<any> {
		return this.http.post('api/logIn', {username: auth.username, password: auth.password}, {headers: this.headers, responseType: 'json'});
	}

	logout(): Observable<any> {
		return this.http.get('api/logOut', {headers: this.headers, responseType: 'text'});
	}

	isLoggedIn(): boolean {
		if (!localStorage.getItem('loggedUser')) {
				return false;
		}
		return true;
	}

	getRole():string{
		const item = localStorage.getItem('loggedUser');
		if (!item) {
			this.router.navigate(['login']);
			return '';
		}

		const jwt: JwtHelperService = new JwtHelperService();
		var	role = jwt.decodeToken(item).roles[0].authority;
		return role;
	}

	getLoggedUser():string{
		const item = localStorage.getItem('loggedUser');
		if (!item) {
			this.router.navigate(['login']);
			return '';
		}

		const jwt: JwtHelperService = new JwtHelperService();
		var	loggedUser = jwt.decodeToken(item);
		return loggedUser;
	}
}
