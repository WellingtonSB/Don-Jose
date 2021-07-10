import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CarrinhoComponent } from './logado/carrinho/carrinho.component';
import { ContaComponent } from './logado/conta-adm/conta.component';
import { PerfilClienteComponent } from './logado/conta-cliente/perfil-cliente/perfil-cliente.component';

import { DetalhesProdutosComponent } from './logado/detalhes-produtos/detalhes-produtos.component';
import { FinalizarPedidoComponent } from './logado/pagamento/finalizar-pedido/finalizar-pedido.component';
import { FinishComponent } from './logado/pagamento/finish/finish.component';
import { FormaPagamentoComponent } from './logado/pagamento/forma-pagamento/forma-pagamento.component';
import { PagamentoComponent } from './logado/pagamento/pagamento.component';
import { ProdutosComponent } from './logado/produtos/produtos.component';
import { ListaDeDesejos } from './model/ListaDeDesejos';
import { SingInComponent } from './sing-in/sing-in.component';
import { SingUpComponent } from './sing-up/sing-up.component';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: SingInComponent },
  { path: 'cadastrar', component: SingUpComponent },
  { path: 'products', component: ProdutosComponent },
  { path: 'listaDeDesejos', component: ListaDeDesejos },
  { path: 'carrinho', component: CarrinhoComponent },
  { path: 'perfil-cliente', component: PerfilClienteComponent },
  { path: 'produtos-detalhe/:id', component: DetalhesProdutosComponent },
  { path: 'conta', component: ContaComponent },
  { path: 'pagamento/:id', component: PagamentoComponent, },
  { path: 'forma-pagamento/:id', component: FormaPagamentoComponent },
  { path: 'finalizar-pagamento/:id', component: FinalizarPedidoComponent },
  { path: 'pagamento-realizado/:id', component: FinishComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
