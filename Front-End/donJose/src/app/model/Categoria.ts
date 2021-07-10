import { Produto } from "./Produto"


export class Categoria{
    public id : number
    public data:Date
    public promocao:boolean
    public porcentagemPromocao:number
    public inicioPromocao:Date
    public fimPromocao:Date
    public nome : string
    public totalProdutos:number
    public produto : Produto[]
}