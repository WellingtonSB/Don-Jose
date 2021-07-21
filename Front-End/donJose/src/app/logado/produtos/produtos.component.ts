import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Categoria } from 'src/app/model/Categoria';
import { ListaDeDesejos } from 'src/app/model/ListaDeDesejos';
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

  produto: Produto = new Produto();
  listaDeProdutos: Produto[];
  idListaDeDesejos = environment.listaDeDesejos;
  idPedido = environment.pedidos;

  categoria: Categoria = new Categoria();
  listaDeCategoria: Categoria[];
  listaDeCategorias:Categoria[];
  idCategoria: number;

  listaDeDesejos: ListaDeDesejos = new ListaDeDesejos();

  key = 'data'
  reverse = true
  filterOff: boolean = true

  totalProdutos:number

  constructor(
    public router: Router,
    public authService: AuthService,
    private produtoService: ProdutoService,
    private categoriaService: CategoriaService
  ) { }

  ngOnInit() {
        window.scroll(0, 0)
    if(this.filterOff){
      this.findAllByProdutos();
    }
    this.findAllByCategoria();
  }

  /* TRAZ TODOS OS PRODUTOS CADASTRADOS NA BASE DE DADOS */
  findAllByProdutos() {
    this.produtoService.findAllByProdutos().subscribe((resp: Produto[]) => {
      this.listaDeProdutos = resp;
      this.filterOff = true
    })
  }

  findAllByCategoria() {
    this.categoriaService.findAllCategorias().subscribe((resp: Categoria[]) => {
      this.listaDeCategoria = resp;
      this.totalProdutos=this.listaDeCategoria.length-1
      console.log(this.totalProdutos)
    })
  }

  findByIdProduto(id: number) {
    this.produtoService.findByIdProduto(id).subscribe((resp: Produto) => {
      this.produto = resp;

    })
  }

  findByIdCategoria() {
    this.categoriaService.findByIdCategoria(this.idCategoria).subscribe((resp: Categoria) => {
      this.categoria = resp;
    })
  }

  findAllByNomeProdutos(nome: string) {
    this.produtoService.findAllByNomeProdutos(nome).subscribe((resp: Produto[]) => {
      this.listaDeProdutos = resp;
    })
  }

  filtrarCategoria(nome: string){
    this.categoriaService.findByNomeCategoria(nome).subscribe((resp:Categoria[])=>{
      this.listaDeCategorias = resp
      this.filterOff = false
    })
  }



  adicionaItemListaDeDesejos(idProduto: number, idLista: number) {
    if (this.authService.logado() == true) {
      this.produtoService.adicionaItemListaDeDesejos(idProduto, idLista).subscribe(() => {
        alert('Produto adicionado a lista de desejos!');
        this.findAllByProdutos();
      })
    } else {
      alert("voce precisa estar logado para adicionar ao carirnho")
      this.router.navigate(['/login'])
    }
  }

  adicionaItemCarrinho(idProduto: number, idCarrinho: number) {
    if (this.authService.logado() == true) {
      this.produtoService.adicionaItemCarrinho(idProduto, idCarrinho).subscribe(() => {
        alert('Produto adicionado ao carrinho!');
        this.findAllByProdutos();
      })
    } else {
      alert("voce precisa estar logado para adicionar ao carirnho")
      this.router.navigate(['/login'])
    }
  }

}

