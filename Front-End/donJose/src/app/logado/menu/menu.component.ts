import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListaDeDesejos } from 'src/app/model/ListaDeDesejos';
import { Pedido } from 'src/app/model/Pedido';
import { Produto } from 'src/app/model/Produto';
import { AuthService } from 'src/app/service/auth.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { PedidoService } from 'src/app/service/pedido.service';
import { ProdutoService } from 'src/app/service/produto.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  minhaListaDeDesejos: ListaDeDesejos = new ListaDeDesejos();
  listaDeDesejosItens: ListaDeDesejos[];
  idListaDeDesejos = environment.listaDeDesejos;

  produto: Produto = new Produto();
  listaDeDesejos: Produto[];
  listaDeProdutoMemoria: Produto[];


  pedido: Pedido = new Pedido();
  listaDePedidos: Pedido[];

  listaDeProdutos: Produto[];
  memoria: Produto[] = [];
  memoriaV: Produto[] = [];

  idCarrinho = environment.pedidos;

  idMemoria: number;

  valorCarrinho: number = 0

  constructor(
    private router: Router,
    private listaDeDesejosService: ClienteService,
    public authService: AuthService,
    private produtoService: ProdutoService,
    private pedidoService: PedidoService
  ) { }

  ngOnInit() {
    this.findByIdListaDeDesejos();
    this.findByIdProdutosCarrinho();
    this.findByIdPedido();
  }

  findByIdListaDeDesejos() {
    this.listaDeDesejosService.findAllByProdutosListaDeDesejos(environment.listaDeDesejos).subscribe((resp: Produto[]) => {
      this.listaDeDesejos = resp;
    })
  }

  findByIdPedido() {
    this.pedidoService.findByIdPedido(environment.pedidos).subscribe((resp: Pedido) => {
      this.pedido = resp
    })
  }

  findByIdProdutosCarrinho() {
    this.pedidoService.findAllByProdutosPedidos(environment.pedidos).subscribe((resp: Produto[]) => {
      this.listaDeProdutos = resp;
      let contador: number = 0;
      let pivo: number[] = [this.listaDeProdutos.length];

      for(let i = 0; i < this.listaDeProdutos.length; i++) {
        pivo[i] = this.listaDeProdutos[i].id;
        for(let item of this.listaDeProdutos) {
          if(pivo[i] == item.id) {
            contador++;
          }
          this.listaDeProdutos[i].qtdPedidoProduto = contador;
        }
        this.memoria = this.listaDeProdutos;
        contador = 0;

			}
    })
  }

  menuCliente(){
    let ok:boolean = false
    if(localStorage.getItem('token') != null) {
      ok = true
    }
    return ok
  }

}

