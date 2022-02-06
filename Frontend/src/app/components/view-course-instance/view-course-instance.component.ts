import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { CoursesService } from '../courses/courses.service';

@Component({
  selector: 'app-view-course-instance',
  templateUrl: './view-course-instance.component.html',
  styleUrls: ['./view-course-instance.component.css']
})
export class ViewCourseInstanceComponent implements OnInit {

  courseInstance: CourseInstance;
  coursesSpecifications:CourseSpecification[] = [];
  courseSpecificationCode:string = '';

  constructor(private courseService: CoursesService, private route: ActivatedRoute,private location: Location) {
    this.courseInstance = new CourseInstance({
      id:0,
      startDate:new Date(),
      endDate:new Date(),
      courseSpecificationDTO:new CourseSpecification({id:0,title:'',ects:0,code:''})
    });
   }

  ngOnInit(): void {
    // if (this.route.snapshot.params['id']) {
    //   this.mode = 'EDIT';
    //   this.route.params.pipe(switchMap((params: Params) => 
    //       this.courseService.getCourseInstance(+params['id']))) // convert to number
    //     .subscribe(res => {
    //       this.courseInstance = res.body==null ? this.courseInstance:res.body;
    //       }
    //     );
    // }else{
      this.courseService.getCoursesSpecifications(-1,'').
            subscribe(res =>{
              this.coursesSpecifications = [];
              this.coursesSpecifications = res.body==null ? []:res.body;
            });
    // }
  }

  save(): void {
    this.add();    
  }

  private add(): void {
    if(this.coursesSpecifications.filter(cs =>cs.code===this.courseSpecificationCode)[0] == undefined){
      alert('--Select a course specification--')
    }else{
      this.courseInstance.courseSpecificationDTO = this.coursesSpecifications.filter(cs =>cs.code===this.courseSpecificationCode)[0];
      console.log(JSON.stringify(this.courseInstance))
      this.courseService.addCourseInstance(this.courseInstance)
        .subscribe(res => {
          // this.userService.announceChange();
          this.goBack();
        });
    }
  }

  searchSpecification(event:Event){
    const target= event.target as HTMLInputElement;
    const searchString = target.value as string;
    console.log(searchString)
    this.courseService.getCoursesSpecifications(-1,searchString).subscribe(res =>
      {
        this.coursesSpecifications = res.body==null ? []:res.body;
      });
  }

  goBack(): void {
    this.location.back();
  }

}
