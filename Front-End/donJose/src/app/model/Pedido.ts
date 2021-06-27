import { Cliente } from "./Cliente"
import { Produto } from "./Produto"

export class Pedido{
    public id : number
    public data : Date
    public valorTotal: number
    public qtdProduto: number
    public numeroPedido:number
    public status:string
    public cliente: Cliente
    public produtos: Produto[]
}