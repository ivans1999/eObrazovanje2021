import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { Enrollment } from 'src/app/model/enrollment';
import { Exam } from 'src/app/model/exam';
import { ExamPart } from 'src/app/model/examPart';
import { ExamPartStatus } from 'src/app/model/examPartStatus';
import { ExamPartType } from 'src/app/model/examPartType';
import { Student } from 'src/app/model/student';
import { User } from 'src/app/model/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ExamPartStatusService } from 'src/app/services/exam-part-status.service';
import { ExamPartService } from '../exam-detail/exam-detail.service';
import { ExamsService } from '../exams/exams.service';
import { StudentService } from '../student/student.service';

@Component({
  selector: 'app-student-exam-detail',
  templateUrl: './student-exam-detail.component.html',
  styleUrls: ['./student-exam-detail.component.css']
})
export class StudentExamDetailComponent implements OnInit {

  exam: Exam;
  examParts: ExamPart[] = [];
  role?: string = undefined;
  student:Student;
  editGradle:boolean=false;
  examPartStatuss:ExamPartStatus [] = [];
  examPart:ExamPart;
  numberPages:number[] = [];
  numberPage:number = 0;

  constructor(
    private location: Location,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService:AuthenticationService,
    private examService:ExamsService,
    private examPartService:ExamPartService,
    private examPartStatusService:ExamPartStatusService,
    private studentS:StudentService
    ) 
  {
    this.exam = {
      id:0,
      enrollmentDTO:{
        id:0,
        studentDTO:{
          id:0,
          cardNumber:'',
          userDTO:{
            id:0,
            firstName:'',
            lastName:'',
            userName:'',
            password:'',
            roles:[]
          }
        },
        courseInstanceDTO:{
          id:0,
          startDate:new Date(),
          endDate:new Date(),
          courseSpecificationDTO:{
            id:0,
            title:'',
            ects:0,
            code:''
          }
        },
      },
      gradle:0,
      points:0
    }
    this.role = this.authenticationService.getRole();
    this.student=new Student({
      id:0,
      cardNumber:'',
      userDTO:new User({
        id:0,
        firstName:'',
        lastName:'',
        userName:'',
        password:'',
        roles:[],
      })
    })
    this.examPartStatusService.getExamPartStatus().subscribe(res=>{
      this.examPartStatuss = res.body == null ? this.examPartStatuss:res.body;
    });
    this.examPart=new ExamPart(
      {
        id:0,
        date:new Date(),
        location:'',
        points:0,
        wonPoints:0,
        code:'',
        examDTO:new Exam({
          id:0,
          enrollmentDTO:new Enrollment({
            id:0,
            studentDTO:new Student({
              id:0,
              cardNumber:'',
              userDTO:new User({
                id:0,
                firstName:'',
                lastName:'',
                userName:'',
                password:'',
                roles:[],
              })
            }),
            courseInstanceDTO:new CourseInstance({
               id: 0,
               startDate: new Date(),
               endDate: new Date(),
               courseSpecificationDTO:new CourseSpecification({
                 id:0,
                 title:'',
                 ects:0,
                 code:'',
               })
            })
          }),
          gradle:0,
          points:0,
        }),
        examPartTypeDTO:new ExamPartType({
          id:0,
          name:'',
          code:'',
        }),
        statusDTO:new ExamPartStatus({
          id:0,
          name:'',
          code:'',
        })
      }
    )
   }

  ngOnInit(): void {
    this.getExamParts();
  }

  dateToString(date:Date):Date{
    // console.log('Date: '+date);
    var d = new Date(date);
    d.setHours(d.getHours()-2);
    // console.log(JSON.stringify(d.getHours()))
    // var dateString = new Date(date).toISOString();
    // var pos=dateString.indexOf('T');
    // var s=dateString.substring(0,pos)+' '+dateString.substring(pos+1,dateString.length-5);
    return d;
  }

  getExamParts(){
    this.route.params.pipe(switchMap((params: Params) =>
    this.examPartService.getExamPartsForStudent(+params['courseId'],params['cardNumber'],this.numberPage)))
    .subscribe(res =>{
      this.examParts = res.body==null ? this.examParts:res.body;
      this.student = this.examParts[0].examDTO.enrollmentDTO.studentDTO;
      this.exam = this.examParts[0].examDTO;
      console.log('Student: '+JSON.stringify(this.student))
      this.studentS.setStudent(this.student);
      this.getNumberPages();
    }
    );
  }

  getNumberPages(){
    this.examPartService.getNumberPage('STUDENT_EXAM_DETAIL','null').subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }
  

  goToExamPart(examPart: ExamPart): void {
    this.router.navigate(['/add-exam-part', examPart.id]);
  }

  edit(){
    this.editGradle = true;
  }

  submit(){
    this.examService.editExam(this.exam).subscribe(()=>{
      this.editGradle = false;
    });
  }

  cancel(){
    this.editGradle = false;
  }

  editExamPart(examPart:ExamPart){
    this.examPart = examPart;
  }

  cancelExamPart(){
    this.examPart = new ExamPart(
      {
        id:0,
        date:new Date(),
        location:'',
        points:0,
        wonPoints:0,
        code:'',
        examDTO:new Exam({
          id:0,
          enrollmentDTO:new Enrollment({
            id:0,
            studentDTO:new Student({
              id:0,
              cardNumber:'',
              userDTO:new User({
                id:0,
                firstName:'',
                lastName:'',
                userName:'',
                password:'',
                roles:[],
              })
            }),
            courseInstanceDTO:new CourseInstance({
               id: 0,
               startDate: new Date(),
               endDate: new Date(),
               courseSpecificationDTO:new CourseSpecification({
                 id:0,
                 title:'',
                 ects:0,
                 code:'',
               })
            })
          }),
          gradle:0,
          points:0,
        }),
        examPartTypeDTO:new ExamPartType({
          id:0,
          name:'',
          code:'',
        }),
        statusDTO:new ExamPartStatus({
          id:0,
          name:'',
          code:'',
        })
      }
    )
  }

  submitExamPart(){
    this.examPart.statusDTO = new ExamPartStatus(this.examPartStatuss.filter(s=>s.code===this.examPart.statusDTO.code)[0]);
    // console.log(JSON.stringify(this.examPart))
    this.examPartService.editExamPart(this.examPart).subscribe(()=>{
      this.cancelExamPart();
      this.getExamParts();
    })
  }

  isIn(id:number):boolean{
    if(id==this.examPart.id){
      return true;
    }
    return false;
  }

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.getExamParts();
    }
  }

  goBack(): void {
    console.log(this.location)
    this.location.back();
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.getExamParts()
    }
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getExamParts()
  }

  // isActive(num:number):boolean{
  //   console.log("Poziva se is active?")
  //   if(this.numberPage===num){
  //     return true;
  //   }
  //   return false;
  // }
}
