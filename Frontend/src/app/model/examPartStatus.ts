export class ExamPartStatus{
    public id: number;
    public name: string;
	public code: string;

    constructor(examPartStatusCfg:ExamPartStatus)
    {
        this.id = examPartStatusCfg.id;
        this.name = examPartStatusCfg.name;
        this.code = examPartStatusCfg.code;
    }
}