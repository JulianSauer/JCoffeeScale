import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {CoffeeScale} from "./coffee-scale";
import {Injectable} from "@angular/core";
import "rxjs/add/operator/map";

@Injectable()
export class CoffeeScaleService {

  constructor(private http: Http) {
  }

  getAll(): Observable<CoffeeScale[]> {
    return this.http.get(`http://localhost:2222/scales`)
      .map(res => <CoffeeScale[]>res.json());
    //.map(res => res.json());
  }

}
