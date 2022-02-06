import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Teacher } from 'src/app/model/teacher';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {

  teachers:Teacher[] | null = [];
  numberPages:number[] = [];
  numberPage:number = 0;

  constructor(private location: Location, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.getTeachers();
  }

  getTeachers(){
    this.getNumberPages();
    this.userService.getTeachers(this.numberPage,'').subscribe(res=>{
      this.teachers = res.body;
    });
  }

  getNumberPages(){
    this.userService.getNumberPage('TEACHERS').subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }

  goToTeacher(teacher:Teacher){
    this.router.navigate(['/teacher', teacher.id]);
  }

  deleteTeacher(t: Teacher): void{
    this.userService.deleteTeacher(t.id==undefined ? 0:t.id).subscribe(
      () => this.getTeachers()
    )
  }

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.getTeachers();
    }
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.getTeachers();
    }
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getTeachers();
  }

  isActive(num:number):boolean{
    if(this.numberPage===num){
      return true;
    }
    return false;
  }

  goBack(): void {
    console.log(this.location)
    this.location.back();
  }
}
