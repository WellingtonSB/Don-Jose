
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders} from "@angular/common/http";
import { environment } from "src/environments/environment.prod";
import { Observable } from "rxjs";
import { CategoriaDTO } from "src/app/model/categoriaDTO";

@Injectable({
    providedIn: 'root'
})

export class CategoriaService {

    /* constructor(public http: HttpClient){}
    
    findAllCategorias():Observable<CategoriaDTO[]>{
        return this.http.get<CategoriaDTO[]>(`${environment.baseUrl}/categorias`);
    } */



}