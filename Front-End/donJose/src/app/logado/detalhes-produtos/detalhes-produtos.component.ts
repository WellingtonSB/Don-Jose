import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/app/model/Categoria';
import { ListaDeDesejos } from 'src/app/model/ListaDeDesejos';
import { Produto } from 'src/app/model/Produto';
import { AuthService } from 'src/app/service/auth.service';
import { CategoriaService } from 'src/app/service/categoria.service';
import { ProdutoService } from 'src/app/service/produto.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-detalhes-produtos',
  templateUrl: './detalhes-produtos.component.html',
  styleUrls: ['./detalhes-produtos.component.css']
})
export class DetalhesProdutosComponent implements OnInit {

  produto: Produto = new Produto();
  listaDeProdutos: Produto[];
  idProduto: number

  idListaDeDesejos = environment.listaDeDesejos;
  idPedido = environment.pedidos;

  categoria: Categoria = new Categoria();
  listaDeCategoria: Categoria[];
  idCategoria: number;

  listaDeDesejos: ListaDeDesejos = new ListaDeDesejos();

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
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




