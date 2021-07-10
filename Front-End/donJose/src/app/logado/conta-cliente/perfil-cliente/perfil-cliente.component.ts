import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from 'src/app/model/Cliente';
import { ListaDeDesejos } from 'src/app/model/ListaDeDesejos';
import { Pedido } from 'src/app/model/Pedido';
import { Produto } from 'src/app/model/Produto';
import { AuthService } from 'src/app/service/auth.service';
import { PedidoService } from 'src/app/service/pedido.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-perfil-cliente',
  templateUrl: './perfil-cliente.component.html',
  styleUrls: ['./perfil-cliente.component.css']
})
export class PerfilClienteComponent implements OnInit {

  usuario: Cliente = new Cliente();
  confirmarSenha: string;

  nome = environment.nome;
  foto = environment.foto
  idUsuario = environment.id;

  idUser: number

  numeroPedidos:number

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
    private authService: AuthService,
    private route: ActivatedRoute,
    private pedidoService: PedidoService
  ) { }

  ngOnInit() {
    this.idUser = this.route.snapshot.params['id']
    this.findByIdUsuario();
    this.findByIdPedido();
    this.findAllPedidos();
  }

  findByIdUsuario() {
    this.authService.findByIdCliente(this.idUser).subscribe((resp: Cliente) => {
      this.usuario = resp;
      console.log("Nome: " + this.usuario.nome);
      console.log("listaDeDesejos: " + this.usuario.listaDeDesejos.produtos);
    })
  }

  findByIdPedido() {
    this.pedidoService.findByIdPedido(environment.pedidos).subscribe((resp: Pedido) => {
      this.pedido = resp
    })
  }

  findAllPedidos(){
    this.pedidoService.findAllByPedidos().subscribe((resp:Pedido[])=>{
      this.listaDePedidos = resp;
    })
  }

  confirmeSenha(event: any) {
    this.confirmarSenha = event.target.value;

  }
  
  atualizar() {
    if (this.usuario.senha != this.confirmarSenha) {
      alert('As senhas estao incorretas!');

    } else {
      this.authService.cadastrar(this.usuario).subscribe((resp: Cliente) => {
        this.usuario = resp;
        alert('Dados atualizados com sucesso, faça login novamente!');

        environment.id = 0;
        environment.nome = '';
        environment.foto = '';
        environment.token = '';
        environment.pedidos = 0;
        environment.listaDeDesejos = 0;


        this.router.navigate(['/login']);
      }, erro => {
        console.log(erro.status);
        console.log(erro);

      });

    }

  }

}
