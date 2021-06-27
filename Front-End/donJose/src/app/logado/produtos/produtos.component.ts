import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Carrinho } from 'src/app/model/Carrinho';
import { Categoria } from 'src/app/model/Categoria';
import { Produto } from 'src/app/model/Produto';
import { AuthService } from 'src/app/service/auth.service';
import { CategoriaService } from 'src/app/service/categoria.service';
import { ProdutoService } from 'src/app/service/produto.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  produto: Produto = new Produto()
  listaDeProdutos: Produto[]

  categoria: Categoria = new Categoria()
  idCategoria: number
  listaDeCategoria: Categoria[]

  carrinho: Carrinho = new Carrinho()
  idCarrinho = environment.carrinho

  idPedido = environment.pedidos

  constructor(
    public router: Router,
    public authService: AuthService,
    private produtoService: ProdutoService,
    private categoriaService: CategoriaService
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    this.findAllByProdutos();
    this.findAllByCategoria();
  }

  /* TRAZ TODOS OS PRODUTOS CADASTRADOS NA BASE DE DADOS */
  findAllByProdutos() {
    this.produtoService.findAllByProdutos().subscribe((resp: Produto[]) => {
      this.listaDeProdutos = resp;
    })
  }

  findAllByCategoria() {
    this.categoriaService.findAllCategorias().subscribe((resp: Categoria[]) => {
      this.listaDeCategoria = resp;
    })
  }

  /* TRAZ SOMENTE UM UNICO PRODUTO POR MEIO DE SEUS ID */
  findByIdProduto(id: number) {
    this.produtoService.findByIdProduto(id).subscribe((resp: Produto) => {
      this.produto = resp;
    })
  }

  /* TRAZ SOMENTE UM UNICO CATEGORI POR MEIO DE SEUS ID */
  findByIdCategoria() {
    this.categoriaService.findByIdCategoria(this.idCategoria).subscribe((resp: Categoria) => {
      this.categoria = resp;

    })

  }

  /* TRAZ UM ARRAY DE PRODUTOS POR MEIO DE UMA QUERY DE NOME */
  findAllByNomeProdutos(nome: string) {
    this.produtoService.findAllByNomeProdutos(nome).subscribe((resp: Produto[]) => {
      this.listaDeProdutos = resp;

    })

  }

  comprarProduto(idProduto: number, idPedido: number){
    this.produtoService.compraProduto(idProduto,idPedido).subscribe(()=>{
      this.router.navigate(['/carrinho'])
    })
  }


  /* ADICIONA PRODUTOS AO CARRINHO DO USUARIO */
  adicionaItemCarrinho(idProduto: number, idCarrinho: number) {
    this.produtoService.adicionaItemCarrinho(idProduto, idCarrinho).subscribe(() => {
      alert('Produto adicionado ao carrinho!');

      this.findAllByProdutos();

    })

  }

}

