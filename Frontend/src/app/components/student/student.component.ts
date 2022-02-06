import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Student } from 'src/app/model/student';
import { UserService } from '../users/users.service';
import { StudentService } from './student.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  students: Student[] =[];
  numberPages:number[] = [];
  numberPage:number = 0;
  subscription: Subscription;

  constructor(private userService: UserService, private studentService: StudentService, private router: Router) { 
    this.subscription = studentService.RegenerateData$.subscribe(()=>
      this.getStudents()
    );
  }

  ngOnInit(): void {
    this.getStudents();
  }

  getNumberPage(){
    this.userService.getNumberPage('STUDENTS').subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }


  getStudents(){
    this.getNumberPage();
    this.studentService.getStudents(this.numberPage).subscribe(
      response => {
        this.students = response.body == null ? this.students:response.body;
      }
    )
  }
  goToViewStudent(s:Student): void{
    this.router.navigate(['/student-detail', s.id])
  }

  deleteStudent(s: Student): void{
    this.studentService.deleteStudent(s.id==undefined ? 0:s.id).subscribe(
      () => this.getStudents()
    )
  }

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.getStudents();
    }
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.getStudents();
    }
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getStudents();
  }

  isActive(num:number):boolean{
    if(this.numberPage===num){
      return true;
    }
    return false;
  }

}
