import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-menu',
  imports: [RouterModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit {

  ngOnInit(): void {
    
  }

  logout(){
    localStorage.removeItem('token');
    window.location.reload();
  }
}
