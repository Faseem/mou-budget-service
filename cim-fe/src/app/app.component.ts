import { Component } from '@angular/core';
import { AuthService } from './shared/services/auth/auth.service';
import { Router } from '@angular/router';
import { KeycloakService } from "../app/keycloak-service/keycloak.service";

@Component({
  // tslint:disable-next-line
  selector: 'body',
  template: '<router-outlet></router-outlet>'
})
export class AppComponent {

  constructor(
    private authService: AuthService,
    private router: Router,
    private keycloakService: KeycloakService) {

     this.keycloakService.loadUserProfile()
      .success(res => {
        this.getPermissions(res.username);
        sessionStorage.setItem('user', res.username)
        sessionStorage.setItem('image', res.attributes["userImage"])
      })
      .error(error => {
        console.error(error);
      }); 
  }

  getPermissions(username) {
    this.authService.getPermissions(username).subscribe(
      res => {
        //Grouped Login
        if (res.data && res.data.role) {
          this.authService.saveSession(res.data);
          return;
        }
        //Roting to denied login page if logged user doesn't have group
        this.router.navigate(['/denied-login'])

      },
      error => {
        this.router.navigate(['/denied-login'])
      });
  }

}
