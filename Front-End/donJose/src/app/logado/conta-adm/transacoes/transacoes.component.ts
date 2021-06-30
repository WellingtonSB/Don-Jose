import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/model/Cliente';
import { Pedido } from 'src/app/model/Pedido';
import { Produto } from 'src/app/model/Produto';
import { AuthService } from 'src/app/service/auth.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { PedidoService } from 'src/app/service/pedido.service';
import { ProdutoService } from 'src/app/service/produto.service';

@Component({
  selector: 'app-transacoes',
  templateUrl: './transacoes.component.html',
  styleUrls: ['./transacoes.component.css']
})
export class TransacoesComponent implements OnInit {

  pedido:Pedido= new Pedido();
  listaDePedidos:Pedido[];
  idPedido: number;
  numeroPedido:number;
  
  produto: Produto = new Produto();
  listaDeProdutos: Produto[];
  idProduto:number
  
  cliente:Cliente = new Cliente();
  listaDeClientes: Cliente[];
  idCliente:number

  key = 'data'
  reverse = true

  mostrarTransacao:boolean = false

  constructor(
    private router: Router,
    private produtoService: ProdutoService,
    private authService: AuthService,
    private pedidoService: PedidoService,
    private clienteService:ClienteService
  ) { }

  ngOnInit(){
    this.findAllPedidos();
    this.findAllProdutos();    
    this.findAllClientes();
  }

  findAllPedidos(){
    this.pedidoService.findAllByPedidos().subscribe((resp:Pedido[])=>{
      this.listaDePedidos = resp;
    })
  }


  findAllProdutos() {
    this.produtoService.findAllByProdutos().subscribe((resp: Produto[]) => {
      this.listaDeProdutos = resp;
    })
  }

  findByIdProduto(){
    this.produtoService.findByIdProduto(this.idProduto).subscribe((resp: Produto)=>{
      this.produto =resp
    })
  }

  findAllClientes(){
    this.clienteService.findAllClientes().subscribe((resp:Cliente[])=>{
      this.listaDeClientes = resp
    })
  }

  findByIdPedido(id:number){
    this.pedidoService.findByIdPedido(id).subscribe((resp: Pedido)=>{
      this.pedido =resp
      this.mostrarTransacao=true    
    })
  }

  findByNumeroDoPedido(np:number){
    this.pedidoService.findAllByNumeroPedido(np).subscribe((resp:Pedido)=>{
      this.pedido = resp;
    })
  }
  
}
