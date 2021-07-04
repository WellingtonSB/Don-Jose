import { Produto } from "./Produto"


export class Categoria{
    public id : number
    public data:Date
    public totalProdutos:number
    public nome : string
    public produto : Produto[]
}