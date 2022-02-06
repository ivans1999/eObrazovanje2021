import { CourseSpecification } from "./courseSpecification";

export class CourseInstance{
    public id: number;
    public startDate: Date;
	public endDate: Date;
    public courseSpecificationDTO: CourseSpecification;

    constructor(courseInstanceCfg:CourseInstance)
    {
        this.id = courseInstanceCfg.id;
        this.startDate = courseInstanceCfg.startDate;
        this.endDate = courseInstanceCfg.endDate;
        this.courseSpecificationDTO = courseInstanceCfg.courseSpecificationDTO;
    }
}