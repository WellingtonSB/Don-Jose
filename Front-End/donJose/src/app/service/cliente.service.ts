import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Carrinho } from '../model/Carrinho';
import { Cliente } from '../model/Cliente';
import { Produto } from '../model/Produto';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  api = environment.server + environment.port;

  autorizacao = {
    headers: new HttpHeaders().set('Authorization', localStorage.getItem('token') || '')
  }

  constructor(
    private http: HttpClient

  ) { }

  findAllClientes(): Observable<Cliente[]> {

    return this.http.get<Cliente[]>(`${this.api}/clientes`, this.autorizacao);
  }

  findByIdListaDeDesejos(id: number): Observable<Carrinho> {

    return this.http.get<Carrinho>(`${this.api}/carrinho/${id}`, this.autorizacao);
  }

  findAllByProdutosListaDeDesejos(id: number): Observable<Produto[]> {

    return this.http.get<Produto[]>(`${this.api}/carrinho/carrinho/${id}`, this.autorizacao);
  }

  findAllByListaDeDesejos(): Observable<Carrinho[]> {

    return this.http.get<Carrinho[]>(`${this.api}/carrinho`, this.autorizacao);
  }

  removerItemListaDeDesejos(idProduto: number, idCarrinho: number): Observable<Produto[]> {

    return this.http.delete<Produto[]>(`${this.api}/carrinho/produto_lista/carrinho/${idProduto}/carrinho/${idCarrinho}`, this.autorizacao);
  }

}