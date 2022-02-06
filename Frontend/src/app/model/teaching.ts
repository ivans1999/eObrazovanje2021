import { CourseInstance } from "./courseInstance";
import { Teacher } from "./teacher";
import { TeachingType } from "./teachingType";

export class Teaching{
    public id: number;
    public teachingTypeDTO: TeachingType;
	public teacherDTO: Teacher;
    public courseInstanceDTO: CourseInstance;

    constructor(teachingCfg:Teaching)
    {
        this.id = teachingCfg.id;
        this.teachingTypeDTO = teachingCfg.teachingTypeDTO;
        this.teacherDTO = teachingCfg.teacherDTO;
        this.courseInstanceDTO = teachingCfg.courseInstanceDTO;
    }
}