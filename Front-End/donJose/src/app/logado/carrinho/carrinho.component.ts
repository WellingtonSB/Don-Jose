import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pedido } from 'src/app/model/Pedido';
import { Produto } from 'src/app/model/Produto';
import { PedidoService } from 'src/app/service/pedido.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-carrinho',
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css']
})
export class CarrinhoComponent implements OnInit {

  nome = environment.nome
  email = environment.email

  pedido: Pedido = new Pedido()
  listaDePedidos:Pedido[]

  listaDeProdutos:Produto[]
  memoria:Produto[] = []
  memoriaV:Produto[] = []

  idCarrinho = environment.pedidos

  idMemoria: number
  
  constructor(
    private pedidoService: PedidoService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(){

    if(localStorage.getItem('token') == null) {
      this.router.navigate(['/login']);
    }
    this.findByIdProdutosCarrinho();
    this.findByIdPedido();
  }

  findByIdProdutosCarrinho() {
this.pedidoService.findAllByProdutosPedidos(environment.pedidos).subscribe((resp: Produto[])=>{
  this.listaDeProdutos = resp;

  let contador:number = 0;
  let repeticao: number = 0;

  //vetor para referencia de validacao
  let refe:number[] = [this.listaDeProdutos.length]

  for(let i = 0; i<this.listaDeProdutos.length;i++){
    //armazena o id dentro do vetor como referencia de validacao(refatorar)
    refe[i] = this.listaDeProdutos[i].id

    for(let item of this.listaDeProdutos){
      if(refe[i]==item.id){
        contador++
      }
      //atribui valor do contador na qtd de um determinado produto.
      this.listaDeProdutos[i].qtdPedidoProduto = contador;
    }

    //insere o primeiro valor para inicializar os valores no vetor
    this.memoria = this.listaDeProdutos;

    //zera o contador para recomecar a contagem
    contador = 0;
  }
 
})

}

findByIdPedido(){
  this.pedidoService.findByIdPedido(environment.pedidos).subscribe((resp: Pedido) => {
    this.pedido = resp;

  })
}

removerDoCarrinho(idProduto: number, idPedido: number) {
  this.pedidoService.removerItemDoCarrinho(idProduto, idPedido).subscribe(() => {
    alert('Item removido do carrinho!');

    this.findByIdProdutosCarrinho();
    this.findByIdPedido();
  
  })

}




}
