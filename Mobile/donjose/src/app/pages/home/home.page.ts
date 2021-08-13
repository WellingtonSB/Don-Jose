import { CategoriaService } from './../../service/controller/categoria.service';
import { Component, OnInit } from '@angular/core';
import { DataService } from '../../data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  public categories = [];
  public featuredProducts = [];
  public bestSellProducts = [];

  constructor(
    private data: DataService,
    public categoriaService:CategoriaService
  ) { }

  ngOnInit() {
   /*   console.log(this.categoriaService.findAllCategorias().subscribe(resp=>{
      console.log(resp);
    }),error =>{
      console.log(error);
    }); */ 
    this.categories = this.data.getCategories();
    this.featuredProducts = this.data.getFeaturedProducts();
    this.bestSellProducts = this.data.getBestSellProducts();
  }

}
