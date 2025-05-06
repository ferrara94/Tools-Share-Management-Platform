import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(@Inject(PLATFORM_ID) private platformId: Object) { }

  set token(token: string) {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('token', token);
    }
  }

  get token(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem('token');
    }
    return null;
  }

  isTokenNotValid(): boolean {
    return !this.isTokenValid();
  }

  private isTokenValid(): boolean{
    const token: string = this.token as string;
    if(!token){
      return false;
    }

    //DECODE THE TOKEN
    const jwtService: JwtHelperService = new JwtHelperService();
    const isTokenExpired: boolean = jwtService.isTokenExpired(token);
    if(isTokenExpired){
      localStorage.clear();
      return false;
    }
    return true;
  }
}
