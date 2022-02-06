export class ChangePass{
    public firstName: string;
    public lastName: string;
    public userName: string;
    public oldPass: string;
    public newPass: string;

    constructor(changePassCfg:ChangePass)
    {
        this.firstName = changePassCfg.firstName;
        this.lastName= changePassCfg.lastName;
        this.userName = changePassCfg.userName;
        this.oldPass = changePassCfg.oldPass;
        this.newPass = changePassCfg.newPass;
    }
}