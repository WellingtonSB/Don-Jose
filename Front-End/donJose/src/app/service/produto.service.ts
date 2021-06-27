import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Produto } from '../model/Produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  endereco = environment.server + environment.port;

  autorizacao = {
    headers: new HttpHeaders().set('Authorization', localStorage.getItem('token') || '')

  }

  constructor(
    private http: HttpClient

  ) { }

  findAllByProdutos(): Observable<Produto[]> {

    return this.http.get<Produto[]>(`${this.endereco}/produtos`, this.autorizacao);
  }

  findByIdProduto(id: number): Observable<Produto> {

    return this.http.get<Produto>(`${this.endereco}/produtos/${id}`, this.autorizacao);
  }

  findAllByNomeProdutos(nome: string): Observable<Produto[]> {

    return this.http.get<Produto[]>(`${this.endereco}/produtos/nome/${nome}`, this.autorizacao);
  }

  findByPrecoGreater(preco:number):Observable<Produto[]>{
    return this.http.get<Produto[]>(`${this.endereco}/produtos/maiorPreco/${preco}`, this.autorizacao);
  }

  findByPrecoLess(preco:number):Observable<Produto[]>{
    return this.http.get<Produto[]>(`${this.endereco}/produtos/menorPreco/${preco}`, this.autorizacao);
  }

  postProduto(produto: Produto): Observable<Produto> {
    return this.http.post<Produto>(`${this.endereco}/produtos`, produto, this.autorizacao);
  }

  putProduto(produto: Produto): Observable<Produto> {

    return this.http.put<Produto>(`${this.endereco}/produtos`, produto, this.autorizacao);
  }

  compraProduto(idProduto: number, idPedido: number): Observable<Produto> {

    return this.http.put<Produto>(`${this.endereco}/produtos/produto_pedido/produtos/${idProduto}/pedidos/${idPedido}`, this.autorizacao);
  }

  adicionaItemCarrinho(idProduto: number, idCarrinho: number): Observable<Produto[]> {

    return this.http.put<Produto[]>(`${this.endereco}/produtos/produto_lista/produtos/${idProduto}/carrinho/${idCarrinho}`, this.autorizacao);
  }

  deleteProduto(id: number): Observable<Produto> {

    return this.http.delete<Produto>(`${this.endereco}/produtos/${id}`, this.autorizacao);
  }

}