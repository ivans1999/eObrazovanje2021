import { Component, OnInit } from '@angular/core';
import { Role } from 'src/app/model/role';
import { User } from 'src/app/model/user';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User;
  roleCode: string = '';
  roles: Role[] = [];
  
  constructor(private userService: UserService) {
    this.newUser = new User({
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      password:'',
      roles:[],
    });
   }

  ngOnInit(): void {
    this.userService.getUnassignedRoles('').
            subscribe(res =>{
              this.roles = [];
              this.roles = res.body==null ? []:res.body;
            });
  }

}
