import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.css']
})
export class ContaComponent implements OnInit {


   /* Gambiarra-refatorar */
  home: boolean = false
  estoque: boolean = false
  editarProduto: boolean = false
  editarCategoria: boolean = false
  perfil: boolean = false
  promocao: boolean = false
  transacoes: boolean = false

  constructor(
    private router: Router,
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

  }
  dashEstoque() {
    this.home = false
    this.perfil = false
    this.estoque = true
    this.promocao=false
    this.editarProduto =false
    this.editarCategoria = false
  }
  dashEditarProduto() {
    this.home = false
    this.perfil = false
    this.estoque = false
    this.promocao=false
    this.editarProduto =true
    this.editarCategoria = false
  }

  dashEditarCategoria() {
    this.home = false
    this.perfil = false
    this.estoque = false
    this.promocao=false
    this.editarProduto =false
    this.editarCategoria = true
  }

  dashPerfil() {
    this.home = false
    this.perfil = true
    this.estoque = false
    this.promocao=false
    this.editarProduto =false
    this.editarCategoria = false
  }

  dashPromocao() {
    this.home = false
    this.perfil = false
    this.estoque = false
    this.promocao=true
    this.editarProduto =false
    this.editarCategoria = false
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

}
