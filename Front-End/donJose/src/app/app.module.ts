import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SingUpComponent } from './sing-up/sing-up.component';
import { SingInComponent } from './sing-in/sing-in.component';
import { MenuComponent } from './logado/menu/menu.component';
import { ProdutosComponent } from './logado/produtos/produtos.component';
import { CarrinhoComponent } from './logado/carrinho/carrinho.component';
import { DetalhesProdutosComponent } from './logado/detalhes-produtos/detalhes-produtos.component';
import { ContaComponent } from './logado/conta/conta.component';
import { PerfilComponent } from './logado/conta/perfil/perfil.component';
import { EditarCategoriaComponent } from './logado/conta/editar-categoria/editar-categoria.component';
import { EditarProdutoComponent } from './logado/conta/editar-produto/editar-produto.component';
import { PromocaoComponent } from './logado/conta/promocao/promocao.component';
import { HomeDashComponent } from './logado/conta/home-dash/home-dash.component';
import { TransacoesComponent } from './logado/conta/transacoes/transacoes.component';
import { FooterComponent } from './footer/footer.component';




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
    FooterComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
