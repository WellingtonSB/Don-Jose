import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from '../model/Cliente';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-sing-up',
  templateUrl: './sing-up.component.html',
  styleUrls: ['./sing-up.component.css']
})
export class SingUpComponent implements OnInit {

  cliente:Cliente= new Cliente
  confirmarSenha:string
  
  constructor(
    private authService: AuthService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    window.scroll(0, 0)
  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value
  }

  cadastrar(){
    if(this.cliente.senha != this.confirmarSenha){
      alert('As senhas devem ser iguais!')
    }else{
      this.authService.cadastrar(this.cliente).subscribe((resp:Cliente)=>{
        this.cliente = resp
        this.router.navigate(['/login'])
      })
    }
  }


}
