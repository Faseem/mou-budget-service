import { Component, OnInit } from '@angular/core';
import { KeycloakService } from '../../keycloak-service/keycloak.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthGuardService } from "app/guards/auth.guard.service";
import * as pkg from '../../../../package.json';

@Component({
  selector: 'denied-login',
  templateUrl: './denied-login.html',
})
export class DeniedLoginComponent implements OnInit {

  public image;
  public user;
  public appVersion;
  public message;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.appVersion = pkg['version'];
    this.image = sessionStorage.getItem('image');
    this.user = sessionStorage.getItem('user');
    if (this.getQueryParam('code') == 'OUTLET_ERROR') {
      this.message = "Hi " + this.user + ", We are unable see your outlet"
    } else {
      this.message = "Hi " + this.user + ", We are unable see your group"
    }
    setTimeout(() => {
      this.logout();
    }, 5000);
  }

  logout() {
    this.router.navigate(['/']).then((value) => {
      KeycloakService.keycloakAuth.logout();
    });
  }

  getQueryParam(key: string): string {
    return this.activatedRoute.snapshot.queryParams[key];
  }

}
