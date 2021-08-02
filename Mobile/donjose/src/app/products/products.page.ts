import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.page.html',
  styleUrls: ['./products.page.scss'],
})
export class ProductsPage implements OnInit {

  public allProducts = [];



  constructor(
    private data: DataService,
  ) { }

  ngOnInit() {
    this.allProducts = this.data.getAll();
  }

}