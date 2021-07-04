import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "src/environments/environment.prod";
import { ClienteLogin } from "../model/ClienteLogin";
import { EmailDTO } from "../model/EmailDTO";
import { AuthService } from "../service/auth.service";

@Component({
  selector: 'app-sing-in',
  templateUrl: './sing-in.component.html',
  styleUrls: ['./sing-in.component.css']
})
export class SingInComponent implements OnInit {

  clienteLogin: ClienteLogin = new ClienteLogin();
  emailDTO: EmailDTO = new EmailDTO();
  tokenUsuario: string;
  recusenha:boolean =false

 
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
      environment.usuario = this.clienteLogin.usuario
      environment.foto = this.clienteLogin.foto 
      environment.listaDeDesejos = this.clienteLogin.listaDeDesejos.id
      environment.pedidos = this.clienteLogin.pedidos.id
      environment.id = this.clienteLogin.id

      this.tokenUsuario = this.clienteLogin.token

      localStorage.setItem('token', this.tokenUsuario);

      /* teste... apagar depois */
      console.log("ID: " + environment.id);
      console.log("Token: " + environment.token);
      console.log("Nome: " + environment.nome);
      console.log("Foto: " + environment.foto);
      console.log("Pedido ID: " + environment.pedidos);
      console.log("Lista de desejos ID: " + environment.listaDeDesejos);

      //remover
      if (environment.usuario == 'adm@gmail.com') {
        this.router.navigate(['/conta'])
      } else {
        this.router.navigate(['/products'])
      }


    })
  }

  senhaRecuperar(){
    this.recusenha = true
  }
  esqueciSenha(){
    this.recusenha = false
    this.auth.resetarSenha(this.emailDTO).subscribe((resp: EmailDTO) =>{
      this.emailDTO = resp
  
      let atraso=500; //1 segundo
      setTimeout(function(){
        window.location.reload()
      },atraso);
  
      
      alert('A nova senha foi enviada para o seu email')
    
  
  
      
    }, erro => {
      if(erro.status == 500){
        alert('Email não encontrado.')
      }
      else if(erro.status == 400){
        alert('Formato de e-mail inválido')
      }
    
    })
  }

}
