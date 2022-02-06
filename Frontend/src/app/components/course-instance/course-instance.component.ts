import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { Enrollment } from 'src/app/model/enrollment';
import { Student } from 'src/app/model/student';
import { Teacher } from 'src/app/model/teacher';
import { Teaching } from 'src/app/model/teaching';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { TeachingService } from 'src/app/services/teaching.service';
import { CoursesService } from '../courses/courses.service';
import { StudentService } from '../student/student.service';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-course-instance',
  templateUrl: './course-instance.component.html',
  styleUrls: ['./course-instance.component.css']
})
export class CourseInstanceComponent implements OnInit {

  courseInstance: CourseInstance;
  coursesSpecifications:CourseSpecification[] = [];
  startDate:string='';
  endDate:string='';
  courseSpecificationCode:string = '';
  students:Student[] = [];
  studentCardNumber:string = '';
  otherStudents:Student[] = [];
  editInstance:boolean=false;
  editTeacher:boolean=false;
  enrollment:Enrollment;
  teachers: Teacher[] = [];
  teacherUsername:string = '';
  teacher:Teacher;
  role:string = '';
  numberPages:number[] = [];
  numberPage:number = 0;

  constructor(
    private courseService:CoursesService, 
    private userService:UserService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private studentS:StudentService,
    private authS:AuthenticationService,
    private teachingService:TeachingService) 
    {
      this.role = this.authS.getRole();
      this.courseInstance = new CourseInstance({
        id:0,
        startDate:new Date(),
        endDate:new Date(),
        courseSpecificationDTO: new CourseSpecification({id:0,title:'',ects:0,code:''})
      });
      // this.courseService.getCoursesSpecifications(-1).subscribe(res =>
      //   {
      //     this.coursesSpecifications = res.body==null ? []:res.body;
      //   });
      // this.userService.getTeachers(-1).subscribe(res =>
      //   {
      //     this.teachers = res.body==null ? []:res.body;
      //   });
      this.enrollment=new Enrollment(
        {
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
          courseInstanceDTO:this.courseInstance
      });
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
        this.courseService.getCourseInstance(+params['id']))) // convert to number
      .subscribe(res => {
        this.courseInstance = res.body==null ? this.courseInstance:res.body;
        // console.log('courseInstance: '+JSON.stringify(this.courseInstance));
        this.startDate = new Date(this.courseInstance.startDate).toISOString().substring(0, 10);
        this.endDate = new Date(this.courseInstance.endDate).toISOString().substring(0, 10);
        this.courseSpecificationCode = this.courseInstance.courseSpecificationDTO.code;
        this.getStudents();
        this.getTeacher('');
        }
      );
  }

  submit() {
    if(this.courseSpecificationCode==='' || this.coursesSpecifications.filter(cs=>cs.code===this.courseSpecificationCode)[0]==undefined){
      alert('--Select a course specification--')
    }else{
      this.editInstance = false;
      this.courseInstance.courseSpecificationDTO=this.coursesSpecifications.filter(cs=>cs.code===this.courseSpecificationCode)[0];
      this.courseInstance.startDate=new Date(this.startDate);
      this.courseInstance.endDate=new Date(this.endDate);
      this.courseService.editCourseInstance(this.courseInstance).subscribe();
    }
  }

  teacherSubmit() {
    if(this.teacherUsername==='' || this.teachers.filter(t=>t.userDTO.userName===this.teacherUsername)[0]==undefined){
      alert('--Select a teacher--')
    }else{
      this.editTeacher = false;
      this.teacher=this.teachers.filter(t=>t.userDTO.userName===this.teacherUsername)[0];
      const teching:Teaching = new Teaching({id:0,teachingTypeDTO:{id:0,name:'',code:0},teacherDTO:this.teacher,courseInstanceDTO:this.courseInstance})
      this.teachingService.addTeaching(teching).subscribe(res=>{
        // console.log(JSON.stringify(res.body))
      })
    }
  }

  cancel(){
    this.editInstance = false;
    this.courseSpecificationCode=this.courseInstance.courseSpecificationDTO.code;
  }

  teacherCancel(){
    this.editTeacher = false;
    this.teacherUsername=this.teacher.userDTO.userName;
  }

  edit(){
    this.editInstance = true;
    this.courseSpecificationCode='';
  }

  teacherEdit(){
    this.editTeacher = true;
    this.teacherUsername='';
  }

  addStudent(){
    // console.log("Student cardNubmer: "+this.studentCardNumber);
    if(this.otherStudents.filter(s=>this.studentCardNumber===s.cardNumber)[0]==undefined){
      alert('--Select a student--')
    }else{
      this.enrollment.studentDTO = this.otherStudents.filter(s=>this.studentCardNumber===s.cardNumber)[0];
      this.enrollment.courseInstanceDTO = this.courseInstance;
      this.courseService.addEnrollment(this.enrollment).subscribe(() => {
        this.getStudents(),
        this.studentCardNumber = '';
      });
    }
  }

  removeStudent(student:Student){
    var enrollment = new Enrollment({id:0,studentDTO:student,courseInstanceDTO:this.courseInstance});
    this.courseService.deleteEnrollment(enrollment).subscribe(()=>this.getStudents());
  }

  getTeacher(searchString:string){
    // console.log("Get teacher!")
    this.userService.getCourseInstanceTeacher(this.courseInstance,searchString).subscribe(res =>{
      // console.log(JSON.stringify(res.body));
      this.teacher = res.body == null ? this.teacher:res.body;
      this.teacherUsername = this.teacher.userDTO.userName;
    })
  }

  getStudents(){
    this.userService.getCourseInstanceStudents(this.courseInstance,this.numberPage).
      subscribe(res =>{
        this.students = [];
        this.students = res.body==null ? []:res.body;
        this.getNumberPages();
        // this.userService.getCourseInstanceOtherStudents(this.courseInstance,'').
        //   subscribe(res =>{
        //     this.otherStudents = [];
        //     this.otherStudents = res.body==null ? []:res.body;
        //     // console.log('Other students: '+JSON.stringify(this.otherStudents));
        //   });
      });
  }

  goBack(): void {
    console.log(this.location)
    this.location.back();
  }

  getNumberPages(){
    this.courseService.getNumberPage('STUDENTS_COURSE','',this.courseInstance.id).subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }

  gotToExamParts():void{
    this.router.navigate(['course-instance/exam-parts/', this.courseInstance.id]);
  }

  goToStudentDetail(student:Student):void{
    this.studentS.setStudent(student);
    console.log("CardNumber: "+student.cardNumber);
    console.log("Course id: "+this.courseInstance.id)
    this.router.navigate(['student-exam-detail/', this.courseInstance.id,student.cardNumber]);
  }

  searchStudents(event:Event){
    const target= event.target as HTMLInputElement;
    const searchString = target.value as string;
    console.log(searchString)
    this.userService.getCourseInstanceOtherStudents(this.courseInstance,searchString).
    subscribe(res =>{
      this.otherStudents = [];
      this.otherStudents = res.body==null ? []:res.body;
      // console.log('Other students: '+JSON.stringify(this.otherStudents));
    });
  }

  searchTeacher(event:Event){
    const target= event.target as HTMLInputElement;
    const searchString = target.value as string;
    console.log(searchString)
    this.userService.getTeachers(-1,searchString)
    .subscribe(res =>{
        this.teachers = [];
        this.teachers = res.body==null ? []:res.body;
    });
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

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.getStudents();
    }
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.getStudents()
    }
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getStudents()
  }

  isActive(num:number):boolean{
    if(this.numberPage===num){
      return true;
    }
    return false;
  }

}