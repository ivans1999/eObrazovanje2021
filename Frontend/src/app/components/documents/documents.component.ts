import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Document } from 'src/app/model/document';
import { User } from 'src/app/model/user';
import { DocumentsService } from './documents.service';
import { saveAs } from 'file-saver';
import { StudentDetailComponent } from '../student-detail/student-detail.component';
import { Student } from 'src/app/model/student';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {

  user: User = { id:0, firstName:"", lastName:"", userName:"",password:"", roles:[]};
  documents : Document[] | null = [];
  numberPages:number[] = [];
  numberPage:number = 0;
  adminViewStudent = false;
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

  constructor(private documentsService:DocumentsService, private router: Router,private route: ActivatedRoute, private auths: AuthenticationService ,private studentDetail: StudentDetailComponent) { 
    if(auths.getRole() === 'ROLE_ADMINISTRATOR'){
      this.student = studentDetail.student;
      this.adminViewStudent = true;
    }
    this.subscription = documentsService.RegenerateData$.subscribe(() =>
    this.geStudentDocuments());
  }

  ngOnInit():void { 
        this.geStudentDocuments();
  }

  geStudentDocuments(){
    this.getNumberPages();
    console.log("Get documents!");
    this.documentsService.getStudentDocuments(this.student.userDTO.userName,this.numberPage).subscribe(
      response => {
        this.documents = response.body;
      });
  }

  getNumberPages(){
    this.documentsService.getNumberPage(this.student.userDTO.userName).subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      var i = 1;
      this.numberPages = [];
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }

  deleteDocument(document: Document): void {
    console.log("Brisem: "+JSON.stringify(document));
    this.documentsService.deleteDocument(document.id).subscribe(
      () => this.geStudentDocuments()
    );
  }

  goToAddDocument(username:string){
    this.router.navigate(['/document/add-for/', username]);
  }

  downloadFile(document:Document): void {
    this.documentsService
      .downloadFile(document.url).subscribe(blob => {
        const splitPath = document.url.split("\\");
        const fileName = splitPath[splitPath.length-1];
        saveAs(blob, fileName);
      })
  }

  // goToViewDocument(doc: Document): void{
  //   this.router.navigate(['/document', doc.id]);
  // }

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
      this.geStudentDocuments();
    }
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
      this.geStudentDocuments();
    }
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.geStudentDocuments();
  }

  isActive(num:number):boolean{
    if(this.numberPage===num){
      return true;
    }
    return false;
  }
}