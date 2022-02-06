import { Account } from "./accounts";

export class Payment{
    public id?:number;
    public currency: string;
    public amount: number;
    public date: Date;
    public urgently: boolean;
    public note: string;
    public accountDTO: Account;

    constructor(paymentCfg:Payment)
    {
        this.id = paymentCfg.id;
        this.currency = paymentCfg.currency;
        this.amount = paymentCfg.amount;
        this.date = paymentCfg.date;
        this.urgently = paymentCfg.urgently;
        this.note = paymentCfg.note;
        this.accountDTO = paymentCfg.accountDTO;
    }
}