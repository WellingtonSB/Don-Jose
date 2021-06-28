import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CarrinhoComponent } from './logado/carrinho/carrinho.component';
import { ContaComponent } from './logado/conta/conta.component';

import { DetalhesProdutosComponent } from './logado/detalhes-produtos/detalhes-produtos.component';
import { ProdutosComponent } from './logado/produtos/produtos.component';
import { SingInComponent } from './sing-in/sing-in.component';
import { SingUpComponent } from './sing-up/sing-up.component';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: SingInComponent },
  { path: 'cadastrar', component: SingUpComponent },
  { path: 'products', component: ProdutosComponent },
  { path: 'carrinho', component: CarrinhoComponent },
  { path: 'produtos-detalhe/:id', component: DetalhesProdutosComponent },
  { path: 'conta', component: ContaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
