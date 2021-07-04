import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(
    public authService: AuthService
  ) { }

  ngOnInit(){
  }

  menuCliente(){
    let ok:boolean = false
    if(localStorage.getItem('token') != null) {
      ok = true
    }
    return ok
  }

}
