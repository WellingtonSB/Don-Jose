import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from 'src/app/model/Cliente';
import { Pedido } from 'src/app/model/Pedido';
import { Produto } from 'src/app/model/Produto';
import { PedidoService } from 'src/app/service/pedido.service';
import { environment } from 'src/environments/environment.prod';

declare var paypal: { Buttons: (arg0: { createOrder: (data: any, actions: any) => any; onApprove: (data: any, actions: any) => Promise<void>; onError: (err: any) => void; }) => { (): any; new(): any; render: { (arg0: any): void; new(): any; }; }; };
@Component({
  selector: 'app-forma-pagamento',
  templateUrl: './forma-pagamento.component.html',
  styleUrls: ['./forma-pagamento.component.css']
})
export class FormaPagamentoComponent implements OnInit {


  
  nome = environment.nome

  pedido: Pedido = new Pedido()
  listaDePedidos: Pedido[]

  cliente: Cliente[]

  listaDeProdutos: Produto[]
  memoria: Produto[] = []
  memoriaV: Produto[] = []
 
  idCarrinho = environment.pedidos

  valorPedido:number
   // paypal
   @ViewChild('paypal', { static: true }) paypalElement!: ElementRef;

   paidFor = false

  constructor(
    private pedidoService: PedidoService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.findByIdPedido();
    this.findByIdProdutosCarrinho();
    if (localStorage.getItem('token') == null) {
      this.router.navigate(['/login']);
    }
    paypal.Buttons({
      createOrder: (data: any, actions: any) => {
        return actions.order.create({
          purchase_units: [{
            description: "Trazer os dados do pedido",
            amount: {
              currency_code: 'BRL',
              value: this.valorPedido
            }
          }]
        })
      },
      onApprove: async (data: any, actions: any) => {
        const order = await actions.order.capture()
        this.paidFor = true
        console.log(order)
      },
      onError: (err: any) => {
        console.log(err)
      }
    })
      .render(this.paypalElement.nativeElement)


  }

  findByIdPedido() {
    this.pedidoService.findByIdPedido(environment.pedidos).subscribe((resp: Pedido) => {
      this.pedido = resp
      this.valorPedido = this.pedido.valorTotal+this.pedido.frete
    })
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













}
