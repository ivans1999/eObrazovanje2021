import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { Subscription } from 'rxjs';
import { UserService } from './users.service';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[] = [];
  numberPages:number[] = [];
  numberPage:number = 0;
  subscription: Subscription;

  constructor(private userService: UserService, private router: Router) { 
    this.subscription = userService.RegenerateData$.subscribe(() => 
      this.getUsers()
    );
  }

  getNumberPages(){
    this.userService.getNumberPage('USERS').subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    this.getNumberPages();
    this.userService.getUsers(this.numberPage).subscribe(
      response => {
        // console.log(response)
        this.users = response.body == null? this.users:response.body;
      });
  }

  deleteUser(user: User): void {
    console.log("Brisem: "+JSON.stringify(user));
    this.userService.deleteUser(user.id==undefined ? 0:user.id).subscribe(
      () => this.getUsers()
    );
  }

  goToViewUser(user: User): void {
    this.router.navigate(['/view-user', user.id]);
  }

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.getUsers();
    }
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.getUsers();
    }
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getUsers();
  }

  // isActive(num:number):boolean{
  //   if(this.numberPage===num){
  //     return true;
  //   }
  //   return false;
  // }

}
