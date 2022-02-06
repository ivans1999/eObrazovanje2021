export class Role{
	public id: number;
	public code: string;
	public name: string;
		
	constructor(roleCfg: Role)
	{	
		this.id = roleCfg.id;
        this.code = roleCfg.code;
        this.name = roleCfg.name;			
	}
}