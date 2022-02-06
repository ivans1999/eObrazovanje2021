import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from '../users/users.service';
import { Location } from '@angular/common';
import {switchMap} from 'rxjs/operators';
import { Role } from 'src/app/model/role';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css']
})
export class ViewUserComponent implements OnInit {

  user: User;
  mode: string = '';
  roleCode: string = '';
  password: string = '';
  unassignedRoles: Role[] = [];

  constructor(
      private userService: UserService, 
      private route: ActivatedRoute,
      private location: Location) 
  {
    this.user = new User({
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      password:'',
      roles:[],
    });
    this.mode = 'ADD';
  }

  ngOnInit() {
    if (this.route.snapshot.params['id']) {
      this.mode = 'EDIT';
      this.route.params.pipe(switchMap((params: Params) => 
          this.userService.getUser(+params['id']))) // convert to number
        .subscribe(res => {
          this.user = res.body==null ? this.user:res.body;
          this.userService.getUnassignedRoles(this.user.userName==undefined ? '':this.user.userName).
            subscribe(res =>{
              this.unassignedRoles = [];
              this.unassignedRoles = res.body==null ? []:res.body;
            });
          }
        );
    } else{
      this.userService.getUnassignedRoles('newUser').
            subscribe(res =>{
              this.unassignedRoles = [];
              this.unassignedRoles = res.body==null ? []:res.body;
            });
    }
  }

  save(): void {
    this.mode == 'ADD' ? this.add() : this.edit();    
  }

  private add(): void {
    this.user.password = this.password;
    this.userService.addUser(this.user)
      .subscribe(res => {
        // this.userService.announceChange();
        this.goBack();
      });
  }

  removeRole(code:string) {
    // console.log(code);
    var r:Role[] = this.user.roles.filter(r => r.code===code)
    this.user.roles=this.user.roles.filter(r => r.code!==code);
    this.unassignedRoles.push(r[0]);
    console.log(JSON.stringify(this.unassignedRoles));
  }

  assignRole() {
    console.log("Role: "+this.roleCode);
    const r = this.unassignedRoles.filter(r => r.code===this.roleCode)[0];
    if(r!==undefined){
      
      this.user.roles.push(r);
      console.log("Razlicito od undefinided!: "+JSON.stringify(this.user.roles));
      this.unassignedRoles=this.unassignedRoles.filter(r => r.code!==this.roleCode);
      console.log(JSON.stringify(this.unassignedRoles));
    }else{
      alert("Select role!")
    }
  }

  edit() {
    if(this.password!==''){
      this.user.password = this.password;
    }
    console.log(JSON.stringify(this.user))
    this.userService.editUser(this.user)
      .subscribe(() => {
        // this.userService.announceChange();
        this.goBack();
      });
  }

  goBack(): void {
    this.location.back();
  }
}
