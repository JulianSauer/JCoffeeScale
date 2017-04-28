import {Component, OnInit} from "@angular/core";
import {CoffeeScale} from "./coffee-scale";
import {CoffeeScaleService} from "./coffee-scale.service";

@Component({
  selector: 'app-scale-list',
  templateUrl: './coffee-scale-list.component.html',
  styles: []
})

export class CoffeeScaleListComponent implements OnInit {

  scales: CoffeeScale[] = [];

  constructor(private coffeeScaleService: CoffeeScaleService) {
  }

  ngOnInit(): void {
    this.coffeeScaleService.getAll().subscribe(s => this.scales = <CoffeeScale[]>s);
  }

}
