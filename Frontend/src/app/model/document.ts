import { Student } from "./student";
import { TypeDocument } from "./typeDocument";

export class Document{
    public id: number;
    public title: string;
    public url: string;
    public typeDocumentDTO: TypeDocument;
    public studentDTO: Student;

    constructor(documentCfg: Document)
    {
        this.id = documentCfg.id;
        this.title= documentCfg.title;
        this.url = documentCfg.url;
        this.typeDocumentDTO = documentCfg.typeDocumentDTO;
        this.studentDTO = documentCfg.studentDTO;
    }
}