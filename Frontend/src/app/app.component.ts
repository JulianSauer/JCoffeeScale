import {Component} from "@angular/core";
import {CoffeeScaleService} from "./coffee-scale.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [CoffeeScaleService]
})
export class AppComponent {
  title = 'Coffee Scales';
}
