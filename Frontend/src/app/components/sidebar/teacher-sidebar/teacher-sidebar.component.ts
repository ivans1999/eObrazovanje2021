import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-teacher-navbar',
  templateUrl: './teacher-sidebar.component.html',
  styleUrls: ['./teacher-sidebar.component.css']
})
export class TeacherSidebarComponent implements OnInit {

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
