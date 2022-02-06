export class CourseSpecification{
    public id: number;
    public title: string;
	public ects: number;
    public code: string;

    constructor(courseInstanceCfg:CourseSpecification)
    {
        this.id = courseInstanceCfg.id;
        this.title = courseInstanceCfg.title;
        this.ects = courseInstanceCfg.ects;
        this.code = courseInstanceCfg.code;
    }
}