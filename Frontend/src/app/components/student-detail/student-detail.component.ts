import { Location } from '@angular/common';
import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { CourseInstance } from 'src/app/model/courseInstance';
import { Payment } from 'src/app/model/payment';
import { Role } from 'src/app/model/role';
import { Student } from 'src/app/model/student';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StudentService } from '../student/student.service';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {

  student: Student;
  courseI:boolean=false;
  courses:CourseInstance[] = [];
  documentI:boolean=false;
  documents:Document[] = [];
  paymentsI:boolean=false;
  payments:Payment[] = [];
  
  // role:Role=new Role({
  //   id:0,
  //   code:'',
  //   name:''
  // })

  constructor(private location: Location,private studentS: StudentService,private userService: UserService, private route: ActivatedRoute,private router: Router) { 
    // this.role = auths.getRole();
    
    this.student = new Student({
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
  }

  ngOnInit(): void {
    if (this.route.snapshot.params['id']) {
      this.route.params.pipe(switchMap((params: Params) => 
          this.userService.getStudent(+params['id']))) // convert to number
        .subscribe(res => {
          this.student = res.body==null ? this.student:res.body;
          this.studentS.setStudent(this.student);
          this.courseI = true;
          }
        );
    }
  }

  click(button:string){
    this.documentI = false;
    this.paymentsI = false;
    this.courseI = false;
    
    if(button === 'documents'){
      this.documentI = true;
    }else if(button === 'payments'){
      this.paymentsI = true;
    }else if(button === 'courses'){
      this.courseI = true;
    }
  }

  goBack(): void {
    console.log(this.location)
    this.location.back();
  }
}
