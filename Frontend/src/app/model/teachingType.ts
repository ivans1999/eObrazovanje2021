export class TeachingType{
    public id: number;
    public name: string;
	public code: number;

    constructor(teachingTypeCfg:TeachingType)
    {
        this.id = teachingTypeCfg.id;
        this.name = teachingTypeCfg.name;
        this.code = teachingTypeCfg.code;
    }
}