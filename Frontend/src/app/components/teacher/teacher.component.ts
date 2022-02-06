import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { CourseInstance } from 'src/app/model/courseInstance';
import { Teacher } from 'src/app/model/teacher';
import { CoursesService } from '../courses/courses.service';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {

  teacher: Teacher;
  coursesInstances:CourseInstance[] | null = [];

  constructor(
    private location: Location,
    private userService: UserService,
    private route: ActivatedRoute,
    private coursesService:CoursesService) {
    this.teacher = new Teacher(
      {
        id:0,
        userDTO:{
                id:0,
                firstName:'',
                lastName:'',
                userName:'',
                password:'',
                roles:[]
              }
    })
   }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) => 
          this.userService.getTeacher(+params['id']))) // convert to number
        .subscribe(res => {
          this.teacher = res.body==null ? this.teacher:res.body;
          }
        );
  }

  goBack(): void {
    console.log(this.location)
    this.location.back();
  }
}
