import { Carrinho } from "./Carrinho"
import { Categoria } from "./Categoria"
import { Pedido } from "./Pedido"

export class Produto{
    public id : number
    public nome: String
    public descricao: String
    public img : String
    public preco : number
    public plu : number
    public estoque : number
    public qtdPedidoProduto: number
    public categoria: Categoria
    public pedidos : Pedido []
    public carrinho : Carrinho[]
}