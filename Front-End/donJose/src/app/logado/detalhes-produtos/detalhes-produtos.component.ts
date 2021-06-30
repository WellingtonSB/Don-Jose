import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Carrinho } from 'src/app/model/Carrinho';
import { Categoria } from 'src/app/model/Categoria';
import { Produto } from 'src/app/model/Produto';
import { CategoriaService } from 'src/app/service/categoria.service';
import { ProdutoService } from 'src/app/service/produto.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-detalhes-produtos',
  templateUrl: './detalhes-produtos.component.html',
  styleUrls: ['./detalhes-produtos.component.css']
})
export class DetalhesProdutosComponent implements OnInit {

  produto: Produto = new Produto()
  idProduto: number
  listaDeProdutos: Produto[]

  categoria: Categoria = new Categoria()
  idCategoria: number
  listaDeCategoria: Categoria[]

  carrinho: Carrinho = new Carrinho()
  idCarrinho = environment.carrinho

  idPedido = environment.pedidos

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private categoriaService: CategoriaService,
    private produtoService: ProdutoService

  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    this.idProduto = this.route.snapshot.params['id'];
    this.findByIdProduto();
    this.findAllCategoria();
  }


  findByIdProduto() {
    this.produtoService.findByIdProduto(this.idProduto).subscribe((resp: Produto) => {
      this.produto = resp
    })
  }

  findAllByProdutos() {
    this.produtoService.findAllByProdutos().subscribe((resp: Produto[]) => {
      this.listaDeProdutos = resp;
    })
  }

  findAllCategoria() {
    this.categoriaService.findAllCategorias().subscribe((resp: Categoria[]) => {
      this.listaDeCategoria = resp;
      this.findAllByProdutos();
    })
  }

  comprarAgora(idProduto: number, idPedido: number) {
    if (localStorage.getItem('token') != null) {
      this.produtoService.compraProduto(idProduto, idPedido).subscribe(() => {
        this.router.navigate(['/carrinho'])
        this.findAllByProdutos();
      })
    } else {
      this.router.navigate(['/login'])
    }
  }

  adicionaItemCarrinho(idProduto: number, idCarrinho: number) {
    if (localStorage.getItem('token') != null) {
      this.produtoService.adicionaItemCarrinho(idProduto, idCarrinho).subscribe(() => {
        alert('Produto adicionado ao carrinho!');
        this.findAllByProdutos();
      })
    } else {
      this.router.navigate(['/login'])
    }
  }

  /*  att(){
     document.location.reload();
   } */

}




