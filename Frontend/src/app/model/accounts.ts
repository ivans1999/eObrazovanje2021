import { Payment } from "./payment";
import { Student } from "./student";

export class Account{
    public id?:number;
    public amount: number;
    public studentDTO: Student;
    public payments:Payment[];

    constructor(accountCfg:Account)
    {
        this.id = accountCfg.id;
        this.amount = accountCfg.amount;
        this.studentDTO = accountCfg.studentDTO;
        this.payments = accountCfg.payments;
    }
}