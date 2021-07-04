import { Categoria } from "./Categoria"
import { ListaDeDesejos } from "./ListaDeDesejos"
import { Pedido } from "./Pedido"

export class Produto{
    public id : number
    public nome: String
    public descricao: String
    public img : String
    public preco : number
    public plu : number
    public promocao:number
    public estoque : number
    public qtdPedidoProduto: number
    public categoria: Categoria
    public pedidos : Pedido []
    public listaDeDesejos: ListaDeDesejos[]
}