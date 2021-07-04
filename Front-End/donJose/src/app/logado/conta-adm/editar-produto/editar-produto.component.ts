import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Categoria } from 'src/app/model/Categoria';
import { Produto } from 'src/app/model/Produto';
import { AuthService } from 'src/app/service/auth.service';
import { CategoriaService } from 'src/app/service/categoria.service';
import { ProdutoService } from 'src/app/service/produto.service';



@Component({
  selector: 'app-editar-produto',
  templateUrl: './editar-produto.component.html',
  styleUrls: ['./editar-produto.component.css']
})
export class EditarProdutoComponent implements OnInit {

  produto: Produto = new Produto();
  listaDeProdutos: Produto[];

  categoria: Categoria = new Categoria();
  listaDeCategoria: Categoria[];
  idCategoria: number;

  addProd: boolean = false
  editar: boolean = false

  constructor(
    private router: Router,
    private produtoService: ProdutoService,
    private authService: AuthService,
    private categoriaService:CategoriaService
  ) { }

  ngOnInit() {
    this.findAllProdutos();
    this.findAllByCategoria();
  }

  /* Gambiarra */
  cadastrarProdutos() {
    this.addProd = true
  }

  cancelar(){
    this.addProd = false
    this.editar = false;
  }
/* Gambiarra*/


  findAllProdutos() {
    this.produtoService.findAllByProdutos().subscribe((resp: Produto[]) => {
      this.listaDeProdutos = resp;
    })
  }

  findAllByCategoria() {
    this.categoriaService.findAllCategorias().subscribe((resp: Categoria[]) => {
      this.listaDeCategoria = resp;

    })

  }
  findByIdProduto(id: number) {
    this.produtoService.findByIdProduto(id).subscribe((resp: Produto) => {
      this.produto = resp;
      this.editar = true;

    })

  }

  findByIdCategoria() {
    this.categoriaService.findByIdCategoria(this.idCategoria).subscribe((resp: Categoria) => {
      this.categoria = resp;
    })

  }

  postProduto() {
    this.categoria.id = this.idCategoria;
    this.produto.categoria = this.categoria;

    this.produtoService.postProduto(this.produto).subscribe((resp: Produto) => {
      this.produto = resp;
      alert(`Produto: ${this.produto.nome} cadastrado com sucesso!`);

      this.produto = new Produto();
      this.addProd = false
      this.findAllProdutos();

    }, erro => {
      if (erro.status == 500) {
        console.log(`Erro: ${erro.status}, algum dado esta sendo inserido incorretamente.`);

      } else if (erro.status >= 400 && erro.status < 500) {
        console.log(`Erro: ${erro.status}, acesso nao autorizado, verifique seu login.`);

      }

    })

  }

  /* ATUALIZA UM DADO DE CATEGORIA NA BASE DE DADOS POR MEIO DO ID */
  putProduto() {
    this.categoria.id = this.idCategoria;
    this.produto.categoria = this.categoria;

    this.produtoService.putProduto(this.produto).subscribe((resp: Produto) => {
      this.produto = resp;

      alert(`Produto: ${this.produto.nome} atualizada com sucesso!`);
      this.editar = false
      this.produto = new Produto();
      this.findAllProdutos();
    }, erro => {
      if (erro.status == 500) {
        console.log(`Erro: ${erro.status}, algum dado esta sendo inserido incorretamente.`)

      } else if (erro.status >= 400 && erro.status < 500) {
        console.log(`Erro: ${erro.status}, acesso nao autorizado, verifique seu login.`)
      }
    })
  }

  /* EXCLUI UM DADO DE CATEGORIA NA BASE DE DADOS POR MEIO DO ID */
  deleteProduto(id: number) {
    this.produtoService.deleteProduto(id).subscribe(() => {
      alert('Categoria excluida com sucesso!');

      this.findAllProdutos();

    })

  }

}
