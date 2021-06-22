import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "src/environments/environment.prod";
import { ClienteLogin } from "../model/ClienteLogin";
import { AuthService } from "../service/auth.service";

@Component({
  selector: 'app-sing-in',
  templateUrl: './sing-in.component.html',
  styleUrls: ['./sing-in.component.css']
})
export class SingInComponent implements OnInit {

  clienteLogin: ClienteLogin = new ClienteLogin();


  /* ARMAZENA O TOKEN DO USUARIO */
  tokenUsuario: string;

  constructor(
    private auth: AuthService,
    private router: Router,
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
  }

  entrar() {
    this.auth.entrar(this.clienteLogin).subscribe((resp: ClienteLogin) => {
      this.clienteLogin = resp
      alert('Login realizado com sucesso!')

      environment.token = this.clienteLogin.token
      environment.nome = this.clienteLogin.nome
     /*  environment.foto = this.clienteLogin.foto */
      environment.email = this.clienteLogin.email
      environment.carrinho = this.clienteLogin.carrinho.id
      environment.pedidos = this.clienteLogin.pedidos.id
      environment.id = this.clienteLogin.id

      /* ARMAZENA O TOKEN DO USUARIO NA VARIAVEL */
      this.tokenUsuario = this.clienteLogin.token

      /* ARMAZENA O TOKEN DO USUARIO NO LOCAL STORAGE */
      localStorage.setItem('token', this.tokenUsuario);

      /* teste... apagar depois */
      console.log("ID: " + environment.id);
      console.log("Token: " + environment.token);
      console.log("E-mail: " + environment.email);
      console.log("Nome: " + environment.nome);
      console.log("Foto: " + environment.foto);
      console.log("Pedido ID: " + environment.pedidos);
      console.log("Lista de Desejos ID: " + environment.carrinho);



      this.router.navigate(['/products'])

    })
  }


}
