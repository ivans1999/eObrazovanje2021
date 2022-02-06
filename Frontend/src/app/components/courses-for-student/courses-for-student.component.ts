import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CourseInstance } from 'src/app/model/courseInstance';
import { Student } from 'src/app/model/student';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CoursesService } from '../courses/courses.service';
import { StudentDetailComponent } from '../student-detail/student-detail.component';
import { StudentService } from '../student/student.service';
import { CourseInstanceForStudentService } from './courses-for-student.service';

@Component({
  selector: 'app-courses-for-student',
  templateUrl: './courses-for-student.component.html',
  styleUrls: ['./courses-for-student.component.css']
})
export class CoursesForStudentComponent implements OnInit {

  courseInstances: CourseInstance[] | null = [];
  mode = '';
  numberPages:number[] = [];
  numberPage:number = 0;
  subscription: Subscription;

  @Input() student:Student = new Student({
    id:0,
    cardNumber: '',
    userDTO:{
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      password:'',
      roles:[]
    }
  })

  constructor(
    private coursesService: CourseInstanceForStudentService,
    private studentS:StudentService,
    // private courseService: CoursesService, 
    private auths: AuthenticationService,
    private router: Router,
    private studentDetail: StudentDetailComponent) { 
    if(auths.getRole() === 'ROLE_ADMINISTRATOR'){
      this.student = studentDetail.student;
    }
    this.subscription = coursesService.RegenerateData$.subscribe(() =>
      this.getCoursesInstances(this.mode));
      // this.getStudentCourses());
  }

  ngOnInit(): void {
    this.mode = 'STUDENT';
    
    // this.getStudentCourses();
    this.getCoursesInstances(this.mode);
  }

  // getStudentCourses(){
  //   this.getNumberPages(this.mode);
  //   console.log('pogodjena ocekivana metoda');
  //   this.coursesService.getStudentCourses(this.student.userDTO.userName).subscribe(
  //     response => {
  //       this.courseInstances = response.body;
  //     }
  //   )
  // }

  dateToString(date:Date):Date{
    var d = new Date(date);
    d.setHours(d.getHours()-1);
    return d;
  }

  goToCourseInstance(courseInstance: CourseInstance): void {
    this.router.navigate(['/course-instance', courseInstance.id]);
  }

  goToStudentDetail(courseInstance:CourseInstance):void{
    this.studentS.setStudent(this.student);
    // console.log("CardNumber: "+this.student.cardNumber);
    // console.log("Course id: "+this.student.id)
    this.router.navigate(['student-exam-detail/', courseInstance.id,this.student.cardNumber]);
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.getCoursesInstances(this.mode);
    }
  }

  getCoursesInstances(mode:string){
    this.getNumberPages(mode);
    this.coursesService.getCoursesInstances(this.student.userDTO.userName,this.numberPage).subscribe(
      response => {
        this.courseInstances = response.body;
      });
  }

  getNumberPages(mode:string){
    this.coursesService.getNumberPage(mode,this.student.userDTO.userName,-1).subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getCoursesInstances(this.mode);
  }

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.getCoursesInstances(this.mode);
    }
  }
}
