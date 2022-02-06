import { Role } from "./role";

export class User{
    public id?: number;
    public firstName: string;
    public lastName: string;
    public userName: string;
    public password: string;
    public roles: Role[];

    constructor(userCfg:User)
    {
        this.id = userCfg.id;
        this.firstName = userCfg.firstName;
        this.lastName = userCfg.lastName;
        this.userName = userCfg.userName;
        this.password = userCfg.password;
        this.roles = userCfg.roles;
    }
}