export class TypeDocument{
    public id: number;
    public name: string;
    public code: string;

    constructor(typeDocumentCfg: TypeDocument)
    {
        this.id = typeDocumentCfg.id;
        this.name= typeDocumentCfg.name;
        this.code = typeDocumentCfg.code;
    }
}