import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Account } from 'src/app/model/accounts';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-student-navbar',
  templateUrl: './student-sidebar.component.html',
  styleUrls: ['./student-sidebar.component.css']
})
export class StudentSidebarComponent implements OnInit {

  @Input() collapsed:boolean;

  constructor(private app:AppComponent, 
              private authenticationService: AuthenticationService,
              // private toastr: ToastrService,
              private router: Router) 
      {
        this.collapsed = app.collapsed;
      }

  ngOnInit(): void {
  }

}
