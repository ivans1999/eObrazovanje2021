import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { CourseInstance } from 'src/app/model/courseInstance';
import { Exam } from 'src/app/model/exam';
import { ExamPart } from 'src/app/model/examPart';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ExamsService } from '../exams/exams.service';
import { ExamPartService } from './exam-detail.service';

@Component({
  selector: 'app-exam-detail',
  templateUrl: './exam-detail.component.html',
  styleUrls: ['./exam-detail.component.css']
})
export class ExamDetailComponent implements OnInit {

  title: String = "";
  exam: Exam;
  examDetails: ExamPart[] = [];
  mode: string = '';
  role?: string = undefined;
  teacherExamPart:boolean = false;
  numberPages:number[] = [];
  numberPage:number = 0;

  constructor(
      private location: Location,
      private examDetailService: ExamPartService,
      private examService: ExamsService, 
      private route: ActivatedRoute,
      private authenticationService:AuthenticationService,
      private router: Router
    ) { 
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
  }

  ngOnInit() {
    if(this.route.snapshot.params['examId']){
      this.mode = 'STUDENT_MY_EXAM_DETAIL';
      this.route.params.pipe(switchMap((params: Params) =>
      this.examService.getExam(+params['examId'])))
      .subscribe(res =>{
        this.exam = res.body==null ? this.exam:res.body;
        this.getExamParts(this.exam.enrollmentDTO.courseInstanceDTO.id);
      }
      );
    }else if(this.route.snapshot.params['courseId']){
      this.mode = 'ADMIN'
      this.route.params.pipe(switchMap((params: Params) =>
      this.examDetailService.getExamParts(+params['courseId'],this.mode,this.numberPage)))
      .subscribe(res =>{
        this.examDetails = res.body == null ? this.examDetails:res.body;
        this.getNumberPages();
      }
      );
    }else if(this.route.snapshot.params['teacherId']){
      this.teacherExamPart = true;
      this.mode = 'TEACHER_EXAM_DETAIL'
      this.getExamParts(-1);
    }
    console.log("Mode: "+this.mode)
  }

  getExamParts(courseId: number){
    // console.log('getExamParts...');
    this.examDetailService.getExamParts(courseId,this.mode,this.numberPage).subscribe(
      response => {
        // console.log('Exam details: '+JSON.stringify(response.body));
        this.examDetails = response.body == null ? this.examDetails:response.body;
      }
      );
      this.getNumberPages();
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

  goToExamPart(examPart: ExamPart): void {
    this.router.navigate(['/add-exam-part', examPart.id]);
  }

  getNumberPages(){
    this.examDetailService.getNumberPage(this.mode,'null').subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      // console.log(num)
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }

  goBack(): void {
    console.log(this.location)
    this.location.back();
  }

  isChecked(id:number):string{
    var status = this.examDetails.filter(ed=>ed.id===id)[0].statusDTO.code;
    return status;
  }

  goToExamPartDetail(examPart: ExamPart): void {
    console.log("Exam part: "+examPart.code)
    this.router.navigate(['/exam-part', examPart.code]);
  }

  isPossibleUnRegister(d:Date): boolean{
    var date = new Date(d);
    const dNow = Date.now();
    var dateNow = new Date(dNow);
    date.setHours(date.getHours()-48)
    dateNow.setHours(dateNow.getHours()+2);
    // console.log("Date: "+JSON.stringify(d.setHours(d.getHours()+48)))
    var isPossibleRegister = false;
    if(date>dateNow){
      isPossibleRegister = true;
      // return true;
    }
    console.log("------------------\nDate now: "+JSON.stringify(dateNow))
    console.log('Date: '+JSON.stringify(date));
    console.log("Is possible register: "+isPossibleRegister)
    return isPossibleRegister;
  }

  goToCourseInstance(courseInstance: CourseInstance): void {
    this.router.navigate(['/course-instance', courseInstance.id]);
  }

  deleteExamPart(examPart: ExamPart): void {
    console.log("Brisem: "+JSON.stringify(examPart));
    this.examDetailService.deleteExamPart(examPart.id).subscribe(
      () => this.getExamParts(this.examDetailService.getCourseId())
    );
  }

  checkValue(examDetail:ExamPart){
    examDetail.statusDTO.code = examDetail.statusDTO.code === 'cr' ? 're':'cr';
    this.examDetailService.registeUnregisterExamPart(examDetail).subscribe();
  }

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.getExamParts(this.examDetailService.getCourseId())
    }
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.getExamParts(this.examDetailService.getCourseId())
    }
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getExamParts(this.examDetailService.getCourseId())
  }

  isActive(num:number):boolean{
    if(this.numberPage===num){
      return true;
    }
    return false;
  }
}
