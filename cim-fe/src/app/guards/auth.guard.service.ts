import { Injectable } from "@angular/core";
import { AuthService } from "../shared/services/auth/auth.service";

@Injectable()
export class AuthGuardService {

  constructor(private authService: AuthService) { }

  public perms;

  public canAccess(area: string) {
    this.perms = JSON.parse(this.authService.getPermissons());

    if (undefined == this.perms) {
      this.perms = [''];
    }

    //Single permission
    if (area && (this.perms.indexOf(area) !== -1)) {
      return true;
    }

    return false;
  }
}
