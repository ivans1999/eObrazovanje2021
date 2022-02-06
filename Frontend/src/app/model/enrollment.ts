import { CourseInstance } from "./courseInstance";
import { Student } from "./student";

export class Enrollment{
    public id: number;
    public studentDTO: Student;
    public courseInstanceDTO: CourseInstance;

    constructor(enrollmentCfg:Enrollment)
    {
        this.id = enrollmentCfg.id;
        this.studentDTO= enrollmentCfg.studentDTO;
        this.courseInstanceDTO = enrollmentCfg.courseInstanceDTO;
    }
}

