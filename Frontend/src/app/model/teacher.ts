import { User } from "./user";

export class Teacher{
    public id: number;
    public userDTO: User;

    constructor(teacherCfg:Teacher)
    {
        this.id = teacherCfg.id;
        this.userDTO = teacherCfg.userDTO;
    }
}