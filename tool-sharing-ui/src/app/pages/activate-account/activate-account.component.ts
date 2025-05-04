import { Component } from '@angular/core';
import { AuthenticationService } from '../../services/services';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CodeInputModule } from "angular-code-input"


@Component({
  selector: 'app-activate-account',
  imports: [CommonModule, CodeInputModule],
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.css'
})
export class ActivateAccountComponent {

  message: string = "";

  isActivationOkay = true;

  submitted: boolean = false; //activation code already submitted

  constructor(
      private authService: AuthenticationService,
      private router: Router,
    ) {}

    onCodeCompleted(activationToken: string) {
      this.confirmAccount(activationToken);
    }

    redirectToLogin(){
      this.router.navigate(['login']);
    }

    confirmAccount(token: string){
      this.authService.confirm({
        token
      }).subscribe({
        next: () => {
          this.message = "Your account has been succesfully activated.\nNow you can proceed to Login";
          this.submitted = true;
          this.isActivationOkay = true;
        },
        error: () => {
          this.message = "Token has been expired or invalid",
          this.submitted = true;
          this.isActivationOkay = false;
        }
      })
    }


}
