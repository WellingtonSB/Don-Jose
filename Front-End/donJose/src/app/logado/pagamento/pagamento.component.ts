import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from 'src/app/model/Cliente';
import { Pedido } from 'src/app/model/Pedido';
import { Produto } from 'src/app/model/Produto';
import { PedidoService } from 'src/app/service/pedido.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-pagamento',
  templateUrl: './pagamento.component.html',
  styleUrls: ['./pagamento.component.css']
})
export class PagamentoComponent implements OnInit {

  nome = environment.nome
  email = environment.email

  pedido: Pedido = new Pedido()
  listaDePedidos: Pedido[]

  cliente: Cliente[]

  listaDeProdutos: Produto[]
  memoria: Produto[] = []
  memoriaV: Produto[] = []

  idCarrinho = environment.pedidos

  constructor(
    private pedidoService: PedidoService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    if (localStorage.getItem('token') == null) {
      this.router.navigate(['/login']);
    }
    this.findByIdPedido();
  }

  findByIdPedido() {
    this.pedidoService.findByIdPedido(environment.pedidos).subscribe((resp: Pedido) => {
      this.pedido = resp;
    })
  }


}
