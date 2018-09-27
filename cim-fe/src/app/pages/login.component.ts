import { Component } from '@angular/core';
import {AuthService} from '../shared/services/auth/auth.service';
import {Router} from "@angular/router";

@Component({
  templateUrl: 'login.component.html'
})
export class LoginComponent {
  username: string;
  password: string;
  constructor(private authService: AuthService, private router: Router) { }

  login() {
    /* if (this.authService.login(this.username, this.password)) {
        this.router.navigate(['/']);
    } */
  }

}
