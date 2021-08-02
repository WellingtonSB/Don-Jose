import { Injectable } from '@angular/core';

// Category Interface
export interface ICategory {
  id: number,
  name: string,
  image: string,
}

// Product Interface
export interface IProduct {
  id: number,
  name: string,
  price: number,
  image: string,
}

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }

  getCategories() {
    let categories = [];

    let cat1: ICategory = {
      id: 1,
      name: 'Bolos',
      image: 'https://i.imgur.com/Byw3qc4.jpg'
    }
    let cat2: ICategory = {
      id: 2,
      name: 'Tortas',
      image: 'https://i.imgur.com/SRVJppe.jpg'
    }
    let cat3: ICategory = {
      id: 3,
      name: 'Muffin',
      image: 'https://i.imgur.com/fjOkHV7.jpg'
    }

    categories.push(cat1, cat2, cat3);

    return categories;
  }

  getFeaturedProducts() {
    let products = [];

    let prod1: IProduct = {
      id: 1,
      name: 'Torta Ferreiro',
      price: 29.99,
      image: 'https://i.imgur.com/24PmyOP.png'
    }
    let prod2: IProduct = {
      id: 2,
      name: 'Bolo de brigadeiro',
      price: 29.99,
      image: 'https://i.imgur.com/oCc0OAV.png'
    }
    let prod3: IProduct = {
      id: 3,
      name: 'Donuts fantasia',
      price: 29.99,
      image: 'https://i.imgur.com/g2yfeEF.png'
    }

    products.push(prod1, prod2, prod3);

    return products;
  }

  getAll() {
    let products = [];

    let prod1: IProduct = {
      id: 1,
      name: 'Torta Ferreiro',
      price: 29.99,
      image: 'https://i.imgur.com/24PmyOP.png'
    }
    let prod2: IProduct = {
      id: 2,
      name: 'Bolo de brigadeiro',
      price: 29.99,
      image: 'https://i.imgur.com/oCc0OAV.png'
    }
    let prod3: IProduct = {
      id: 3,
      name: 'Donuts fantasia',
      price: 29.99,
      image: 'https://i.imgur.com/g2yfeEF.png'
    }
    let prod4: IProduct = {
      id: 4,
      name: 'Torta Ferreiro',
      price: 29.99,
      image: 'https://i.imgur.com/24PmyOP.png'
    }
    let prod5: IProduct = {
      id: 5,
      name: 'Bolo de brigadeiro',
      price: 29.99,
      image: 'https://i.imgur.com/oCc0OAV.png'
    }
    let prod6: IProduct = {
      id: 6,
      name: 'Donuts fantasia',
      price: 29.99,
      image: 'https://i.imgur.com/g2yfeEF.png'
    }
    let prod7: IProduct = {
      id: 7,
      name: 'Torta Ferreiro',
      price: 29.99,
      image: 'https://i.imgur.com/24PmyOP.png'
    }
    let prod8: IProduct = {
      id: 8,
      name: 'Bolo de brigadeiro',
      price: 29.99,
      image: 'https://i.imgur.com/oCc0OAV.png'
    }
    let prod9: IProduct = {
      id: 9,
      name: 'Donuts fantasia',
      price: 29.99,
      image: 'https://i.imgur.com/g2yfeEF.png'
    }
    let prod10: IProduct = {
      id: 10,
      name: 'Torta Ferreiro',
      price: 29.99,
      image: 'https://i.imgur.com/24PmyOP.png'
    }
    let prod11: IProduct = {
      id: 11,
      name: 'Bolo de brigadeiro',
      price: 29.99,
      image: 'https://i.imgur.com/oCc0OAV.png'
    }
    let prod12: IProduct = {
      id: 12,
      name: 'Bolo de brigadeiro',
      price: 29.99,
      image: 'https://i.imgur.com/oCc0OAV.png'
    }
    let prod13: IProduct = {
      id: 13,
      name: 'Donuts fantasia',
      price: 29.99,
      image: 'https://i.imgur.com/g2yfeEF.png'
    }

    products.push(prod1, prod2, prod3,prod4,prod5,prod6,prod7,prod8,prod9,prod10,prod11,prod12,prod13);

    return products;
  }

  getBestSellProducts() {
    let products = [];

    let prod1: IProduct = {
      id: 1,
      name: 'Donuts de brigadeiro',
      price: 29.99,
      image: 'https://i.imgur.com/m0iAMol.png'
    }
    let prod2: IProduct = {
      id: 2,
      name: 'Bolo de ninho',
      price: 29.99,
      image: 'https://i.imgur.com/mWc3fbz.png'
    }
    let prod3: IProduct = {
      id: 1,
      name: 'Torta Chocolate',
      price: 29.99,
      image: 'https://i.imgur.com/uGVKmXR.png'
    }

    products.push(prod1, prod2, prod3);

    return products;
  }
}
