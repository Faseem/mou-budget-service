import { Injectable } from '@angular/core';
//import { ApiService } from './api.service';
import { Http } from '@angular/http';
import { StorageServicee } from './storage.service';
import { tokenNotExpired } from 'angular2-jwt';
import { KeycloakService } from "../../../keycloak-service/keycloak.service";
import { CONFIGURATIONS } from "../../../config/constants.model";
import { Router } from "@angular/router";
import { HttpUtils } from 'app/shared/utils/http-utils';

@Injectable()
export class AuthService {
  public token: string;

  constructor(private http: Http,
    private router: Router,
    private storageService: StorageServicee,
    private httpUtils: HttpUtils) {
    // set token if saved in local storage
    this.token = storageService.getAccessToken();
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    this.token = null;
    this.clearSession();
    this.router.navigate(['/']).then((value) => {
      this.storageService.clearStorage();
      KeycloakService.keycloakAuth.logout();
    });
  }

  getPermissions(username: string) {
    let url = CONFIGURATIONS.permissionRoot + CONFIGURATIONS.permissionUrl
      .replace("${username}", username);
    sessionStorage.setItem('user', username);
    return this.httpUtils.httpGet(url);
  }

  saveSession(credentials: any): boolean {

    if (credentials) {
      sessionStorage.setItem('permissions', window.btoa(JSON.stringify(credentials.permissions)));
      sessionStorage.setItem('role', credentials.role);
      return true;
    } else {
      return false;
    }
  }

  getPermissons() {

    let permissions = sessionStorage.getItem('permissions');
    if (!permissions) {
      return null;
    } else {
      let data = window.atob(permissions);
      return data;
    }

  }

  clearSession() {
    sessionStorage.removeItem('permissions');
    sessionStorage.removeItem('role');
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('image');
  }
}
