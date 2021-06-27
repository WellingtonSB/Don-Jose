import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.css']
})
export class ContaComponent implements OnInit {


   /* Gambiarra-refatorar */
  home: boolean = true
  estoque: boolean = false
  editarProduto: boolean = false
  editarCategoria: boolean = false
  perfil: boolean = false
  promocao: boolean = false
  transacoes: boolean = false

  constructor(
    private router: Router,
    private auth:AuthService
  ) { }

  ngOnInit() {
    if (localStorage.getItem('token') == null) {
      this.router.navigate(['/login']);
    }
  }

 
  /* Gambiarra refatorar */
  dashHome() {
    this.home = true
    this.perfil = false
    this.estoque = false
    this.promocao=false
    this.editarProduto =false
    this.editarCategoria = false
    this.transacoes= false

  }

  dashEditarProduto() {
    this.home = false
    this.perfil = false
    this.estoque = false
    this.promocao=false
    this.editarProduto =true
    this.editarCategoria = false
    this.transacoes= false
  }

  dashEditarCategoria() {
    this.home = false
    this.perfil = false
    this.estoque = false
    this.promocao=false
    this.editarProduto =false
    this.editarCategoria = true
    this.transacoes= false
  }

  dashPerfil() {
    this.home = false
    this.perfil = true
    this.estoque = false
    this.promocao=false
    this.editarProduto =false
    this.editarCategoria = false
    this.transacoes= false
  }

  dashPromocao() {
    this.home = false
    this.perfil = false
    this.estoque = false
    this.promocao=true
    this.editarProduto =false
    this.editarCategoria = false
    this.transacoes= false
  }

  dashTransacoes(){
    this.transacoes= true
    this.home = false
    this.perfil = false
    this.estoque = false
    this.promocao=false
    this.editarProduto =false
    this.editarCategoria = false
  }

  sair(){
    this.auth.logOut()
  }
}
