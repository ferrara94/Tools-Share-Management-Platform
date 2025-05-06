import { HttpInterceptorFn } from '@angular/common/http';
import { TokenService } from '../token/token.service';
import { inject } from '@angular/core';

export const httpTokenInterceptor: HttpInterceptorFn = (req, next) => {
  const tokenService = inject(TokenService);
  const token = tokenService.token ?? '';

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
