import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Cliente } from '../model/Cliente';
import { ClienteLogin } from '../model/ClienteLogin';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public endereco = environment.server + environment.port;

  autorizacao = {
    headers: new HttpHeaders().set('Authorization', localStorage.getItem('token') || '')

  }

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }


  findByIdCliente(id: number): Observable<Cliente> {

    return this.http.get<Cliente>(`${this.endereco}/clientes/${id}`, this.autorizacao);
  }

  entrar(clienteLogin: ClienteLogin): Observable<ClienteLogin> {

    return this.http.post<ClienteLogin>(`${this.endereco}/clientes/logar`, clienteLogin);
  }

  cadastrar(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.endereco}/clientes/cadastrar`, cliente);
  }

  /* DELOGA DA SESSAO */
  logOut() {
    environment.id = 0;
    environment.nome = '';
    environment.email = '';
    environment.senha = '';
    environment.foto = '';
    environment.tipo = '';
    environment.token = '';
    environment.pedidos = 0;
    environment.carrinho = 0;

    /* ARMAZENA O TOKEN DO USUARIO NO LOCAL STORAGE */
    localStorage.removeItem('token');
    this.router.navigate(['/home']);

  }
}
