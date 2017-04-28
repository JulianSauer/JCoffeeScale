import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppComponent} from "./app.component";
import {CoffeeScaleService} from "./coffee-scale.service";
import {CoffeeScaleListComponent} from "./coffee-scale-list.component";

@NgModule({
  declarations: [
    AppComponent,
    CoffeeScaleListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [CoffeeScaleService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
