import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Carrinho } from 'src/app/model/Carrinho';
import { Cliente } from 'src/app/model/Cliente';
import { Pedido } from 'src/app/model/Pedido';
import { Produto } from 'src/app/model/Produto';
import { AuthService } from 'src/app/service/auth.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { PedidoService } from 'src/app/service/pedido.service';
import { ProdutoService } from 'src/app/service/produto.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-home-dash',
  templateUrl: './home-dash.component.html',
  styleUrls: ['./home-dash.component.css']
})
export class HomeDashComponent implements OnInit {

  nome = environment.nome;
  email = environment.email;
  idUsuario = environment.id;
  idPedido = environment.pedidos;

  minhListaDeDesejos: Carrinho = new Carrinho();
  listaDeDesejosItens: Carrinho[];
  idListaDeDesejos = environment.carrinho;

  produto: Produto = new Produto();
  listaDeDesejos: Produto[];
  listaDeProdutoMemoria: Produto[];

  usuario: Cliente = new Cliente();

  /* DADOS CARRINHO USUARIO */
  pedido: Pedido = new Pedido();
  listaDePedidos: Pedido[];

  listaDeProdutos: Produto[];

  idCarrinho = environment.pedidos;

  

  listaDeClientes:Cliente[];


  totalPedidos: number;
  totalClientes:number;

  dia: Date = new Date();

  constructor(
    private router: Router,
    private pedidoService: PedidoService,
    private authService: AuthService,
    private clienteService:ClienteService
  ) { }

  ngOnInit() {
    this.findByIdUsuario(environment.id);

    if(localStorage.getItem('token') == null) {
      this.router.navigate(['/login']);
    }
    /* DADOS CARRINHO USUARIO */
    this.findByIdPedido();
    this.findAllPedido();
    this.findAllClientes();
  }

  findByIdUsuario(id: number) {
    this.authService.findByIdCliente(id).subscribe((resp: Cliente) => {
      this.usuario = resp;
      console.log("Nome: " + this.usuario.nome);
    })
  }

  findAllClientes(){
    this.clienteService.findAllClientes().subscribe((resp:Cliente[])=>{
      this.listaDeClientes = resp;
      this.totalClientes = this.listaDeClientes.length
    })
  }

  findAllPedido() {
    this.pedidoService.findAllByPedidos().subscribe((resp: Pedido[]) => {
      this.listaDePedidos = resp;
      this.totalPedidos = this.listaDePedidos.length
    })
  }

  findByIdPedido() {
    this.pedidoService.findByIdPedido(environment.pedidos).subscribe((resp: Pedido) => {
      this.pedido = resp;
    })
  }
}
