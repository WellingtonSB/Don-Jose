import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pedido } from 'src/app/model/Pedido';
import { Produto } from 'src/app/model/Produto';
import { PedidoService } from 'src/app/service/pedido.service';
import { ProdutoService } from 'src/app/service/produto.service';
import { environment } from 'src/environments/environment.prod';



@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css']
})
export class CarrinhoComponent implements OnInit {


  idCarrinho = environment.carrinho
  idPedido = environment.pedidos
  
  pedido: Pedido = new Pedido()
  listaDePedidos: Pedido[]

  produto: Produto = new Produto()
  listaDeProdutos: Produto[]
  memoria: Produto[] = []
  memoriaV: Produto[] = []


  idMemoria: number

  valorCarrinho: number = 0

  constructor(
    private pedidoService: PedidoService,
    private produtoService: ProdutoService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {

    if (localStorage.getItem('token') == null) {
      this.router.navigate(['/login']);
    }
    this.findByIdProdutosCarrinho();
    this.findByIdPedido();
  }

  findByIdProdutosCarrinho() {
    this.pedidoService.findAllByProdutosPedidos(environment.pedidos).subscribe((resp: Produto[]) => {
      this.listaDeProdutos = resp;
      let contador: number = 0;
      let refe: number[] = [this.listaDeProdutos.length]

      for (let i = 0; i < this.listaDeProdutos.length; i++) {
        refe[i] = this.listaDeProdutos[i].id
        for (let item of this.listaDeProdutos) {
          if (refe[i] == item.id) {
            contador++
          }
          this.listaDeProdutos[i].qtdPedidoProduto = contador;
        }
        this.memoria = this.listaDeProdutos;
        contador = 0;
      }
    })
  }

  findByIdPedido() {
    this.pedidoService.findByIdPedido(environment.pedidos).subscribe((resp: Pedido) => {
      this.pedido = resp;
    })
  }

  removerDoCarrinho(idProduto: number, idPedido: number) {
    this.pedidoService.removerItemDoCarrinho(idProduto, idPedido).subscribe(() => {
      //alert('Item removido do carrinho!');
      this.findByIdProdutosCarrinho();
      this.findByIdPedido();
    })
  }


  totalProdutos(idProduto: number, idPedido: number) {
    this.produtoService.compraProduto(idProduto, idPedido).subscribe(() => {
      this.pedido.qtdProduto = this.pedido.qtdProduto + 1
      this.totalPrd()
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

