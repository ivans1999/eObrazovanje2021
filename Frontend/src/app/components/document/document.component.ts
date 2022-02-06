import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Document } from 'src/app/model/document';
import { Student } from 'src/app/model/student';
import { TypeDocument } from 'src/app/model/typeDocument';
import { Url } from 'src/app/model/url';
import { User } from 'src/app/model/user';
import { DocumentTypeService } from 'src/app/services/document-type.service';
import { DocumentsService } from '../documents/documents.service';

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements OnInit {

  fileToUpload: File | null;
  document: Document;
  documentTypes:TypeDocument[] = [];
  mode:string = '';
  role:string = '';

  constructor(
      private documentsService:DocumentsService,
      private documentTypeService:DocumentTypeService,
      private location: Location,
      private route: ActivatedRoute,
    ) 
      {
        this.fileToUpload = new File(new Array<Blob>(), "Mock.zip", { type: 'application/zip' });
        this.document = new Document(
          {
            id:0,title:'',
            studentDTO:new Student(
              {
                  id:0,
                  cardNumber:'',
                  userDTO:new User(
                    {
                      id:0,
                      firstName:'',
                      lastName:'',
                      userName:'',
                      password:'',
                      roles:[],
                    })
              }),
            url:'',
            typeDocumentDTO:new TypeDocument({id:0,code:'',name:''})
          }
        );
        this.documentTypeService.getDocumentTypes().subscribe(res=>{
          this.documentTypes = res.body == null ? []:res.body;
        });
        this.mode = 'ADD';
      }

  ngOnInit(): void {
    if (this.route.snapshot.params['id']) {
      this.mode = 'EDIT';
      this.route.params.pipe(switchMap((params: Params) => 
          this.documentsService.getDocument(+params['id']))) // convert to number
        .subscribe(res => {
          this.document = res.body==null ? this.document:res.body;
          }
        );
    }
  }

  onFileSelected(event:Event) {

    const target= event.target as HTMLInputElement;

    this.fileToUpload = (target.files as FileList)[0];

    this.document.title = this.fileToUpload.name;
  }

  submitDocument(){
    if(this.document.typeDocumentDTO.code===''){
      alert("--Select the document type--")
    }
    else if (this.fileToUpload) {

      const formData = new FormData();

      formData.append("file", this.fileToUpload);

      this.documentsService.addFile(formData).subscribe(res=>{
        const url:Url = res.body == null ? new Url({url:''}):res.body;
        this.document.url = url.url;
        console.log("Document: "+JSON.stringify(this.document))
        if (this.route.snapshot.params['username']) {
          this.route.params.pipe(switchMap((params: Params) => 
              this.documentsService.addDocument(this.document,params['username'])))
            .subscribe(() => {
              this.goBack();
              }
            );
        }else{
          this.documentsService.addDocument(this.document,'undefined').subscribe(()=>{
            this.goBack();
          });
        }
      });
    }
  }

  goBack(): void {
    this.location.back();
  }
}
