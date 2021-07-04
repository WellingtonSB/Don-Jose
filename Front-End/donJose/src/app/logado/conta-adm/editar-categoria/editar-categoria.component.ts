import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Categoria } from 'src/app/model/Categoria';
import { Produto } from 'src/app/model/Produto';
import { AuthService } from 'src/app/service/auth.service';
import { CategoriaService } from 'src/app/service/categoria.service';
import { ProdutoService } from 'src/app/service/produto.service';

@Component({
  selector: 'app-editar-categoria',
  templateUrl: './editar-categoria.component.html',
  styleUrls: ['./editar-categoria.component.css']
})
export class EditarCategoriaComponent implements OnInit {

  categoria:Categoria= new Categoria();
  listaDeCategorias:Categoria[];

  produtosDaCategoria:number = 0;
  
  addCat:boolean =false
  editar:boolean =false

  constructor(
    private router: Router,
    private categoriaService: CategoriaService,
    private authService: AuthService
  ) { }

  ngOnInit(){
    this.findAllCategorias();
  }

  cadastrarCategoria(){
    this.addCat = true
  }


  findAllCategorias(){
    this.categoriaService.findAllCategorias().subscribe((resp:Categoria[])=>{
      this.listaDeCategorias = resp;
 
    })
  }

    /* TRAZ UM ITEM ESPECIFICO DE CATEGORIA INFORMANDO COMO PARAMETRO UM ID */
    findByIdCategoria(id: number) {
      this.categoriaService.findByIdCategoria(id).subscribe((resp: Categoria) => {
        this.categoria = resp;
        this.editar =true;
      })
    }

  postCategoria() {
    this.categoriaService.postCategoria(this.categoria).subscribe((resp: Categoria) => {
      this.categoria = resp;
      alert(`Categoria: ${this.categoria.nome} cadastrada com sucesso!`);

      this.categoria = new Categoria();
      this.addCat = false
      this.findAllCategorias();

    }, erro => {
      if(erro.status == 500) {
        console.log(`Erro: ${erro.status}, algum dado esta sendo inserido incorretamente.`);

      }else if(erro.status >= 400 && erro.status < 500){
        console.log(`Erro: ${erro.status}, acesso nao autorizado, verifique seu login.`);

      }

    })

  }

  /* ATUALIZA UM DADO DE CATEGORIA NA BASE DE DADOS POR MEIO DO ID */
  putCategoria() {
    this.categoriaService.putCategoria(this.categoria).subscribe((resp: Categoria) => {
      this.categoria = resp;

      alert(`Categoria: ${this.categoria.nome} atualizada com sucesso!`);
      this.editar =false
      this.categoria = new Categoria();
      this.findAllCategorias();

    }, erro => {
      if(erro.status == 500) {
        console.log(`Erro: ${erro.status}, algum dado esta sendo inserido incorretamente.`)

      }else if(erro.status >= 400 && erro.status < 500){
        console.log(`Erro: ${erro.status}, acesso nao autorizado, verifique seu login.`)

      }

    })

  }

  deleteCategoria(id: number) {
    this.categoriaService.deleteCategoria(id).subscribe(() => {
      alert('Categoria excluida com sucesso!');

      this.findAllCategorias();

    })

  }

}
