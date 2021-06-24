import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
//import { ModalModule } from 'ngx-bootstrap/modal';

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
       
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    //ModalModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
