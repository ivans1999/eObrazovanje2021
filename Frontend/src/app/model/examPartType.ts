export class ExamPartType{
    public id: number;
    public name: string;
	public code: string;

    constructor(examPartTypeCfg:ExamPartType)
    {
        this.id = examPartTypeCfg.id;
        this.name = examPartTypeCfg.name;
        this.code = examPartTypeCfg.code;
    }
}
