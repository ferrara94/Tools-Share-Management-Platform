import { Component } from '@angular/core';
import { AuthenticationRequest } from '../../services/models';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../../services/services';
import { Router } from '@angular/router';
import { TokenService } from '../../services/token/token.service';


@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  authRequest: AuthenticationRequest = {
    email: '',
    password: ''
  };

  temp = "";

  errorMsg: Array<string> = [];

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private tokenService: TokenService
  ) {}

  login(){
    this.errorMsg = []; 
    this.authService.authenticate({
        body: this.authRequest
      }).subscribe({
        next: (response) => {
          this.tokenService.token = response.token as string;
            this.router.navigate(['tools']);
        },
        error: (err) => {
          console.log('error',err);
          if(err.error?.validationErrors){
            this.errorMsg = err.error.validationErrors;
          } else {
            this.errorMsg.push(err.error?.error);
            this.errorMsg.push(err.error?.businessErrorDescription);
          }
        }
      })
  }

  checkCredentials(){
    
  }

  register(){
    this.router.navigate(['register'])
  }
}
