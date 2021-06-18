import { Carrinho } from "./Carrinho"
import { Pedido } from "./Pedido"


export class Cliente {
    public id: number
    public nome: string
    public email: string
    public celular: string
    public cpf: string
    public cep: string
    public senha: string
    public endereco: string
    public numero: string
    /*  public complemento: string */
    public bairro: string
    public cidade: string
    public estado: string
    public foto: string
    public pedidos:Pedido
    public carrinho: Carrinho
}