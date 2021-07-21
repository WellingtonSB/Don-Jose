import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderModule } from 'ngx-order-pipe';
import { HomeComponent } from './home/home.component';
import { SingUpComponent } from './sing-up/sing-up.component';
import { SingInComponent } from './sing-in/sing-in.component';
import { MenuComponent } from './logado/menu/menu.component';
import { ProdutosComponent } from './logado/produtos/produtos.component';
import { CarrinhoComponent } from './logado/carrinho/carrinho.component';
import { DetalhesProdutosComponent } from './logado/detalhes-produtos/detalhes-produtos.component';
import { ContaComponent } from './logado/conta-adm/conta.component';
import { PerfilComponent } from './logado/conta-adm/perfil/perfil.component';
import { EditarCategoriaComponent } from './logado/conta-adm/editar-categoria/editar-categoria.component';
import { EditarProdutoComponent } from './logado/conta-adm/editar-produto/editar-produto.component';
import { PromocaoComponent } from './logado/conta-adm/promocao/promocao.component';
import { HomeDashComponent } from './logado/conta-adm/home-dash/home-dash.component';
import { TransacoesComponent } from './logado/conta-adm/transacoes/transacoes.component';
import { FooterComponent } from './footer/footer.component';
import { PagamentoComponent } from './logado/pagamento/pagamento.component';
import { FormaPagamentoComponent } from './logado/pagamento/forma-pagamento/forma-pagamento.component';
import { FinalizarPedidoComponent } from './logado/pagamento/finalizar-pedido/finalizar-pedido.component';
import { FinishComponent } from './logado/pagamento/finish/finish.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { ListaDeDesejosComponent } from './logado/conta-cliente/lista-de-desejos/lista-de-desejos.component';
import { PerfilClienteComponent } from './logado/conta-cliente/perfil-cliente/perfil-cliente.component';




@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SingUpComponent,
    SingInComponent,
    MenuComponent,
    ProdutosComponent,
    CarrinhoComponent,
    DetalhesProdutosComponent,
    ContaComponent,
    PerfilComponent,
    EditarCategoriaComponent,
    EditarProdutoComponent,
    PromocaoComponent,
    HomeDashComponent,
    TransacoesComponent,
    FooterComponent,
    PagamentoComponent,
    FormaPagamentoComponent,
    FinalizarPedidoComponent,
    FinishComponent,
    ListaDeDesejosComponent,
    PerfilClienteComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    OrderModule
  ],
  providers: [
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
