import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
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
import { ExamPartTypeService } from 'src/app/services/exam-part-type.service';
import { ExamPartService } from '../exam-detail/exam-detail.service';

@Component({
  selector: 'app-add-exam-part',
  templateUrl: './add-exam-part.component.html',
  styleUrls: ['./add-exam-part.component.css']
})
export class AddExamPartComponent implements OnInit {

  examPart:ExamPart;
  typeCode:string = '';
  mode: string = '';
  examPartTypes:ExamPartType[] = [];
  dateString:string = '';

  constructor(
      private examPartService:ExamPartService,
      private location: Location,
      private examPartTypeService:ExamPartTypeService,
      private route: ActivatedRoute) 
  {
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
    this.examPartTypeService.getExamPartType().subscribe(res=>{
      this.examPartTypes=res.body===null ? []:res.body;
    });
    this.mode = 'ADD';
   }

  ngOnInit(): void {
    if (this.route.snapshot.params['examPartId']) {
      this.mode = 'EDIT';
      this.route.params.pipe(switchMap((params: Params) => 
          this.examPartService.getExamPart(+params['examPartId']))) // convert to number
        .subscribe(res => {
          this.examPart = res.body==null ? this.examPart:res.body;
          this.dateString = new Date(this.examPart.date).toISOString().substring(0, 16);
          this.typeCode = this.examPart.examPartTypeDTO.code;
          }
        );
    }
  }

  save(): void {
    this.examPart.examPartTypeDTO=this.examPartTypes.filter(t=>this.typeCode===t.code)[0];
    this.examPart.examDTO.enrollmentDTO.courseInstanceDTO.id=this.examPartService.getCourseId();
    this.mode == 'ADD' ? this.add() : this.edit();    
  }

  add(): void {
    console.log('ADD: '+JSON.stringify(this.examPart));
    this.examPartService.addExamPart(this.examPart)
      .subscribe(() => {
        // this.userService.announceChange();
        this.goBack();
      });
  }

  edit() {
    this.dateString = this.dateString+':00.000Z'
    console.log("Hour: "+this.dateString)
    const date = new Date(this.dateString);
    this.examPart.date=date;
    console.log('EDIT: '+JSON.stringify(this.examPart))
    this.examPartService.editExamPart(this.examPart)
      .subscribe(() => {
        // this.userService.announceChange();
        this.goBack();
      });
  }

  goBack(): void {
    console.log(JSON.stringify(this.examPart));
    this.location.back();
  }

}
