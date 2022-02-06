import { Exam } from "./exam";
import { ExamPartType } from "./examPartType";
import { ExamPartStatus } from "./examPartStatus";
export class ExamPart{
    public id: number;
    public date: Date;
    public location: String;
    public points: number;
    public wonPoints: number;
    public code:string;
    public examDTO: Exam;
    public examPartTypeDTO: ExamPartType;
    public statusDTO: ExamPartStatus;

    constructor(examDetailCfg:ExamPart)
    {
        this.id = examDetailCfg.id;
        this.date= examDetailCfg.date;
        this.location = examDetailCfg.location;
        this.points = examDetailCfg.points;
        this.wonPoints = examDetailCfg.wonPoints;
        this.code = examDetailCfg.code;
        this.examDTO = examDetailCfg.examDTO;
        this.examPartTypeDTO= examDetailCfg.examPartTypeDTO;
        this.statusDTO = examDetailCfg.statusDTO;
    }
}
