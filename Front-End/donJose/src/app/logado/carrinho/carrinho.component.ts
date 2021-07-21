import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ListaDeDesejos } from 'src/app/model/ListaDeDesejos';
import { Pedido } from 'src/app/model/Pedido';
import { Produto } from 'src/app/model/Produto';
import { AuthService } from 'src/app/service/auth.service';
import { ClienteService } from 'src/app/service/cliente.service';
import { PedidoService } from 'src/app/service/pedido.service';
import { ProdutoService } from 'src/app/service/produto.service';
import { environment } from 'src/environments/environment.prod';



@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css']
})
export class CarrinhoComponent implements OnInit {

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
    private authService: AuthService,
    private produtoService: ProdutoService,
    private pedidoService: PedidoService
  ) { }

  ngOnInit() {

    if (localStorage.getItem('token') == null) {
      this.router.navigate(['/login']);
    }
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


  adicionaItemCarrinho(idProduto: number, idCarrinho: number) {
    this.produtoService.adicionaItemCarrinho(idProduto, idCarrinho).subscribe(() => {
      this.findByIdProdutosCarrinho();
    })
  }

  removerDoCarrinho(idProduto: number, idPedido: number) {
    this.pedidoService.removerItemDoCarrinho(idProduto, idPedido).subscribe(() => {
      alert('Item removido do carrinho!');
      this.findByIdProdutosCarrinho();
      this.findByIdPedido();
    })

  }

  totalPrd(){
    this.valorCarrinho = 0
    for(let item of this.listaDeProdutos){
      this.valorCarrinho = this.valorCarrinho + item.preco
      
    }
  }


}

