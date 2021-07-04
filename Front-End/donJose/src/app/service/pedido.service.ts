import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Pedido } from '../model/Pedido';
import { Produto } from '../model/Produto';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  endereco = environment.server + environment.port;

  autorizacao = {
    headers: new HttpHeaders().set('Authorization', localStorage.getItem('token') || '')

  }

  constructor(
    private http: HttpClient

  ) { }

  findAllByPedidos(): Observable<Pedido[]> {

    return this.http.get<Pedido[]>(`${this.endereco}/pedidos`, this.autorizacao);
  }


  findByIdPedido(id: number): Observable<Pedido> {

    return this.http.get<Pedido>(`${this.endereco}/pedidos/${id}`, this.autorizacao);
  }

  findAllByNumeroPedido(numeroPedido:number):Observable<Pedido>{
    return this.http.get<Pedido>(`${this.endereco}/pedidos/numeroPedido/${numeroPedido}`, this.autorizacao)
  }
  

  findAllByProdutosPedidos(idPedido: number): Observable<Produto[]> {

    return this.http.get<Produto[]>(`${this.endereco}/pedidos/meuspedidos/${idPedido}`, this.autorizacao);
  }

  postPedido(pedido: Pedido): Observable<Pedido> { 

    return this.http.post<Pedido>(`${this.endereco}/pedidos`, pedido, this.autorizacao);
  }

  putPedido(pedido: Pedido): Observable<Pedido> {

    return this.http.put<Pedido>(`${this.endereco}/pedidos`, pedido, this.autorizacao);
  }

  deletePedido(id: number): Observable<Pedido> {

    return this.http.delete<Pedido>(`${this.endereco}/pedidos/${id}`, this.autorizacao);
  }

  putProduto(idProduto: number, idPedido: number): Observable<Pedido> {

    return this.http.delete<Pedido>(`${this.endereco}/pedidos/produto_pedido/produtos/${idProduto}/pedidos/${idPedido}`, this.autorizacao);
  }

  removerItemDoCarrinho(idProduto: number, idPedido: number): Observable<Pedido> {

    return this.http.delete<Pedido>(`${this.endereco}/pedidos/produto_pedido/produtos/${idProduto}/pedidos/${idPedido}`, this.autorizacao);
  }
  
}



