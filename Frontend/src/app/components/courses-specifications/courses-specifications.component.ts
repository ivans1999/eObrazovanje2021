import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { CoursesService } from '../courses/courses.service';

@Component({
  selector: 'app-courses-specifications',
  templateUrl: './courses-specifications.component.html',
  styleUrls: ['./courses-specifications.component.css']
})
export class CoursesSpecificationsComponent implements OnInit {

  coursesSpecifications: CourseSpecification[] | null = [];
  numberPages:number[] = [];
  numberPage:number = 0;
  subscription: Subscription;

  constructor(private courseService: CoursesService,private router: Router) {
    this.subscription = courseService.RegenerateData$.subscribe(() => 
      this.getCoursesSpecifications()
    );
   }

  ngOnInit(): void {
    this.getCoursesSpecifications();
  }

  getCoursesSpecifications(){
    this.getNumberPages();
    this.courseService.getCoursesSpecifications(this.numberPage,'').subscribe(
      response => {
        console.log(response.body)
        this.coursesSpecifications = response.body
      });
  }

  getNumberPages(){
    this.courseService.getNumberPage('COURSE_SPECIFICATION','',-1).subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }

  deleteCourseSpecification(courseSpecification: CourseSpecification): void {
    console.log("Brisem: "+JSON.stringify(courseSpecification));
    this.courseService.deleteCourseSpecification(courseSpecification.id==undefined ? 0:courseSpecification.id).subscribe(
      () => this.getCoursesSpecifications()
    );
  }

  goToViewCourseSpecification(courseSpecification: CourseSpecification): void {
    this.router.navigate(['/view-course-specification', courseSpecification.id]);
  }

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.getCoursesSpecifications();
    }
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.getCoursesSpecifications();
    }
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getCoursesSpecifications();
  }

  isActive(num:number):boolean{
    if(this.numberPage===num){
      return true;
    }
    return false;
  }

}
