import { Component } from '@angular/core';
import { MenuComponent } from "../../components/menu/menu.component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-main',
  imports: [MenuComponent, RouterModule],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

}
