import { Component, OnInit } from '@angular/core';
import { ChangePass } from 'src/app/model/changePass';
import { User } from 'src/app/model/user';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User;
  changePass: ChangePass;
  editInstance:boolean = false;
  repeatPass:string='';

  constructor(private userService: UserService) {
    this.user = new User({
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      password:'',
      roles:[],
    });
    this.changePass = new ChangePass({
      firstName:'',
      lastName:'',
      userName:'',
      oldPass:'',
      newPass:''
    })
   }

  ngOnInit(): void {
    var storage = localStorage.getItem('loggedUser');
    var user = JSON.parse(storage==null?'':storage)
    console.log("User: "+user.username);
    this.getUser();
  }

  submit(){
    if(this.changePass.firstName===''){
      alert('Type the new first name');
    }else if(this.changePass.lastName===''){
      alert('Type the new last name');
    }
    else if(this.changePass.oldPass===''){
      alert('Type the old password');
    }else if(this.changePass.newPass===''){
      alert('Type the new password');
    }else if(this.changePass.newPass!==this.repeatPass){
      alert('Type the repeat password');
    }
    else {
      this.editInstance = false;
      console.log(JSON.stringify(this.changePass) + ' changePass');
      this.userService.changePass(this.changePass).subscribe(res => {
        this.user = res.body==null?this.user:res.body;
      });
    }
  }

  edit(){
    this.editInstance = true;
    this.changePass.firstName = this.user.firstName;
    this.changePass.lastName = this.user.lastName;
    this.changePass.userName = this.user.userName;
    this.changePass.oldPass = '';
    this.changePass.newPass = ''

  }

  cancel(){
    this.editInstance = false;
    // this.getUser();
  }

  getUser(){
    this.userService.getLoggedUser().subscribe(res => {
      this.user = res.body==null ? this.user:res.body;
      this.user.password = '';
    });
  }
}
