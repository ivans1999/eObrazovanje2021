import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { CoursesService } from '../courses/courses.service';

@Component({
  selector: 'app-view-course-specification',
  templateUrl: './view-course-specification.component.html',
  styleUrls: ['./view-course-specification.component.css']
})
export class ViewCourseSpecificationComponent implements OnInit {

  courseSpecification:CourseSpecification;
  mode: string = '';

  constructor(private courseService: CoursesService, private route: ActivatedRoute,private location: Location) {
    this.courseSpecification = new CourseSpecification({
      id:0,
      title:'',
      ects:0,
      code:''
    });
    this.mode = 'ADD';
   }

  ngOnInit(): void {
    if (this.route.snapshot.params['id']) {
      this.mode = 'EDIT';
      this.route.params.pipe(switchMap((params: Params) => 
          this.courseService.getCourseSpecification(+params['id']))) // convert to number
        .subscribe(res => {
          this.courseSpecification = res.body==null ? this.courseSpecification:res.body;
          }
        );
    }
  }

  save(): void {
    this.mode == 'ADD' ? this.add() : this.edit();    
  }

  private add(): void {
    this.courseService.addCourseSpecification(this.courseSpecification)
      .subscribe(res => {
        // this.userService.announceChange();
        this.goBack();
      });
  }

  edit() {
    this.courseService.editCourseSpecification(this.courseSpecification)
      .subscribe(() => {
        // this.userService.announceChange();
        this.goBack();
      });
  }

  goBack(): void {
    this.location.back();
  }

}
