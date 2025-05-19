import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { KeycloakService } from '../keycloak/keycloak.service';

export const authGuard: CanActivateFn = (route, state) => {
  const keycloakService = inject(KeycloakService);
  const router = inject(Router);
  if(keycloakService.Keycloak?.isTokenExpired()){
    router.navigate(['login']);
    return false;
  }
  return true;
};


/* 
  JWT CUSTOM IMPLEMENTATION

  export const authGuard: CanActivateFn = (route, state) => {
    const tokenService = inject(TokenService);
    const router = inject(Router);
    if(tokenService.isTokenNotValid()){
      router.navigate(['login']);
      return false;
    }
    return true;
  }; 
*/
