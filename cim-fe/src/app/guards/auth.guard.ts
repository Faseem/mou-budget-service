import {Injectable} from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {AuthGuardService} from './auth.guard.service';
import {AuthService} from "../shared/services/auth/auth.service";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authGuardService: AuthGuardService, private jwtService: AuthService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return true;
    // if (this.jwtService.loggedIn()) {
    //   const data = <any>(route.routeConfig.data);
    //   if (data.area) {
    //     const canAccess = this.authGuardService.canAccess(data.area);

    //     if (!canAccess) {
    //       this.router.navigate(['/404']);
    //     }
    //     return canAccess;
    //   } else {
    //     return true;
    //   }
    // } else {
    //   this.router.navigate(['/login']);
    //   return false;
    // }
  }
}
