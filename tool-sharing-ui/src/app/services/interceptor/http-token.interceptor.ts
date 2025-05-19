import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { KeycloakService } from '../keycloak/keycloak.service';

export const httpTokenInterceptor: HttpInterceptorFn = (req, next) => {
  //const tokenService = inject(TokenService);
  //const token = tokenService.token ?? '';

  const keycloakService = inject(KeycloakService);
  const token = keycloakService.Keycloak.token;

  // Log to verify the interceptor is being called
  console.log('Interceptor called');
  if (token) {
    console.log('Token:', token);
  }

  if (token) {
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
    return next(authReq);
  }

  return next(req);
};
