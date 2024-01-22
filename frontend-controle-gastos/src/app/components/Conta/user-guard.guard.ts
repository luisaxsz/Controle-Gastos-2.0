import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginComponent } from './login/login.component';
import { AuthServiceService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuardGuard implements CanActivate {

  constructor(
    private router : Router,
    private authService: AuthServiceService
  ){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.authService.isAutenticado){
        return true;
      }else{
        alert("Se autentique primeiro")
        this.router.navigate(['login'])
        return false;
      }
  }
}
