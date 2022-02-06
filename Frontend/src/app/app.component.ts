import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public role?: string = undefined;
  public username = '';

  title = 'frontEndEObrazovanje';
  collapsed = true;

  constructor(private router: Router,private authenticationService:AuthenticationService){}

  checkRole() {
		const item = localStorage.getItem('loggedUser');
		if (!item) {
			this.router.navigate(['login']);
      this.role = undefined;
			return;
		}
    if(typeof(this.authenticationService.getLoggedUser().sub as unknown as string)==='string'){
      this.username = this.authenticationService.getLoggedUser().sub as unknown as string;
      console.log("Username: "+this.username)
    }
    const jwt: JwtHelperService = new JwtHelperService();
		this.role = jwt.decodeToken(item).roles[0].authority;
    // console.log(this.role)
	}
}
