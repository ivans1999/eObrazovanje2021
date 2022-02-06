import { Enrollment } from "./enrollment";

export class Exam{
    public id: number;
    public enrollmentDTO: Enrollment;
    public gradle: number;
    public points: number;

    constructor(examCfg:Exam)
    {
        this.id = examCfg.id;
        this.enrollmentDTO= examCfg.enrollmentDTO;
        this.gradle = examCfg.gradle;
        this.points = examCfg.points;
    }
}

