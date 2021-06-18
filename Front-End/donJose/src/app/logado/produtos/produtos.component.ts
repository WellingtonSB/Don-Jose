import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Categoria } from 'src/app/model/Categoria';
import { Produto } from 'src/app/model/Produto';
import { AuthService } from 'src/app/service/auth.service';
import { CategoriaService } from 'src/app/service/categoria.service';
import { ProdutoService } from 'src/app/service/produto.service';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  produto: Produto = new Produto()
  categoria: Categoria = new Categoria()
  token = localStorage.getItem('token')
  listaCategorias: Categoria[]
  listaProdutos: Produto[]
  filterOff: boolean = true
  nomeProduto: String
  key = 'preco'

  reverse = true
  totalProdutos: number
  precoLess: Produto[]
  precoGreater: Produto[]

  constructor(
    public router: Router,
    public authService: AuthService,
    private produtoService: ProdutoService,
    private categoriaService: CategoriaService,
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    if (this.filterOff) {
      this.findAllProduto()
    }

    this.findAllCategoria()

  }

  findAllProduto() {
    this.produtoService.findAllByProdutos().subscribe((resp: Produto[]) => {
      this.listaProdutos = resp
      this.filterOff = true
    })
  }

  findAllCategoria() {
    this.categoriaService.findAllCategorias().subscribe((resp: Categoria[]) => {
      this.listaCategorias = resp
    })
  }

  filtrarCategoria(nome: string) {
    this.categoriaService.findByNomeCategoria(nome).subscribe((resp: Categoria[]) => {
      this.listaCategorias = resp
      this.filterOff = false
    })
 
  } }



