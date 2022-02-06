import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationService } from "../services/authentication.service";

@Injectable({
	providedIn: 'root'
})
export class RoleGuard implements CanActivate{

    constructor(
		public auth: AuthenticationService,
		public router: Router
	) { }

    canActivate(route: ActivatedRouteSnapshot): boolean {
        // const expectedRoles: string = route.data.expectedRoles;
		// console.log("Route: "+route);
		const token = localStorage.getItem('loggedUser');
		const jwt: JwtHelperService = new JwtHelperService();

		if (!token) {
			this.router.navigate(['/login']);
			return false;
		}
		const info = jwt.decodeToken(token);
		// console.log(JSON.stringify(info) + 'info');

		//const roles: string[] = expectedRoles.split('|', 2);
        //console.log("Roles: "+JSON.stringify(roles));
		// console.log(JSON.stringify(info.roles));
		if (info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "users") {
			this.router.navigate(['/home']);
			return false;
		}else if((info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && info.roles[0].authority.indexOf("ROLE_TEACHER") === -1) && route.url.toString() === "courses"){
			this.router.navigate(['/home']);
			return false;
		}else if((info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && info.roles[0].authority.indexOf("ROLE_TEACHER") === -1) && route.url.toString() === "add-course-instance"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "courses-specifications"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "add-course-specification"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "view-course-specification/:id"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && info.roles[0].authority.indexOf("ROLE_TEACHER") && route.url.toString() === "course-instance/:id"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "add-user"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "students"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "teachers"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "teacher/:id"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "student-detail/:id"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "document/add-for/:username"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && info.roles[0].authority.indexOf("ROLE_TEACHER") && route.url.toString() === "student-exam-detail/:id/:cardNumber"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "view-user/:id"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && info.roles[0].authority.indexOf("ROLE_TEACHER") && info.roles[0].authority.indexOf("ROLE_STUDENT") && route.url.toString() === "course-instance/exam-parts/:id"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_TEACHER") === -1 && route.url.toString() === "teacher-exam-parts/:id"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_TEACHER") === -1 && info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "add-exam-part"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_STUDENT") === -1 && route.url.toString() === "exams"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_STUDENT") === -1 && route.url.toString() === "exam-detail/student/:id"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_STUDENT") === -1 && route.url.toString() === "account"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_STUDENT") === -1 && route.url.toString() === "add-payment"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_STUDENT") === -1 && route.url.toString() === "documents"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_STUDENT") === -1 && route.url.toString() === "document"){
			this.router.navigate(['/home']);
			return false;
		}
		return true;
    }
}
