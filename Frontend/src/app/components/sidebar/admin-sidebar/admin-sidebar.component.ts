import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// import { ToastrService } from 'ngx-toastr';
import { AppComponent } from 'src/app/app.component';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-admin-sidebar',
  templateUrl: './admin-sidebar.component.html',
  styleUrls: ['./admin-sidebar.component.css']
})
export class AdminSidebarComponent implements OnInit {

  @Input() collapsed:boolean;

  constructor(private app:AppComponent, 
              private authenticationService: AuthenticationService,
		          // private toastr: ToastrService,
		          private router: Router
          ) {
    this.collapsed = app.collapsed;
   }

  ngOnInit(): void {
  }

}
