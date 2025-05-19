import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { KeycloakService } from '../../../../services/keycloak/keycloak.service';


@Component({
  selector: 'app-menu',
  imports: [RouterModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit {

  ngOnInit(): void {
    
  }

  constructor(
    private keycloakService: KeycloakService
  ) {

  }

  async logout(){
    this.keycloakService.logout();
  }

  /* 
    logout(){
      localStorage.removeItem('token');
      window.location.reload();
    } 
  */
}
