import { ListaDeDesejos } from "./ListaDeDesejos"
import { Pedido } from "./Pedido"

export class ClienteLogin{
    public id: number
    public nome: string
    public email: string
    public usuario:string
    public celular: string
    public cpf: string
    public senha: string
    public dataNascimento:string
    public foto: string
    public token:string
    /* ViaCep */
    public cep: string
    public logradouro: string
    public numero: string
    public complemento: string
    public localidade: string
    public bairro: string
    public uf: string

    /* Relacionamento */
    public pedidos: Pedido
    public listaDeDesejos: ListaDeDesejos
}