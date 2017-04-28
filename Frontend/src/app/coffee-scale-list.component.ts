import {Component, OnInit} from "@angular/core";
import {CoffeeScale} from "./coffee-scale";
import {CoffeeScaleService} from "./coffee-scale.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-scale-list',
  templateUrl: './coffee-scale-list.component.html',
  styleUrls: ['coffee-scale-list.component.css']
})

export class CoffeeScaleListComponent implements OnInit {

  scales: CoffeeScale[] = [];

  constructor(private coffeeScaleService: CoffeeScaleService) {
  }

  ngOnInit(): void {
    let timer = Observable.timer(1000, 2000);
    timer.subscribe(() =>
      this.coffeeScaleService.getAll().subscribe(s => this.scales = <CoffeeScale[]>s)
    );
  }

  getStyle(uid: String) {
    switch (this.getScale(uid).level) {
      case "NO_CAN":
        return "#7cb637";
      case "EMPTY":
        return "#8b1b2d";
      case "FULL":
        return "#4d8b39";
      case "OVERLOADED":
        return "#b99a23";
    }
  }

  getScale(uid: String): CoffeeScale {
    for (let scale of this.scales) {
      if (scale.uid == uid)
        return scale;
    }
  }

}
