import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from 'src/app/model/Cliente';
import { AuthService } from 'src/app/service/auth.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  usuario: Cliente = new Cliente();
  confirmarSenha: string;

  nome = environment.nome;
  email = environment.email;
  foto = environment.foto
  idUsuario = environment.id;

  idUser: number

  constructor(
    private router: Router,
    private authService: AuthService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.idUser = this.route.snapshot.params['id']
    this.findByIdUsuario(environment.id);
  }

  findByIdUsuario(id: number) {
    this.authService.findByIdCliente(id).subscribe((resp: Cliente) => {
      this.usuario = resp;
      console.log("Nome: " + this.usuario.nome);
      console.log("carrinho: " + this.usuario.carrinho.id);
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
        environment.email = '';
        environment.senha = '';
        environment.foto = '';
        environment.tipo = '';
        environment.token = '';
        environment.pedidos = 0;
        environment.carrinho = 0;


        this.router.navigate(['/login']);
      }, erro => {
        console.log(erro.status);
        console.log(erro);

      });

    }

  }

}
