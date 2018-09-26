import { Directive, ElementRef, Input, OnInit } from "@angular/core";
import { AuthService } from "../shared/services/auth/auth.service";

@Directive({
    selector: '[hasPermission]'
})
export class HasPermissionDirective implements OnInit {
    @Input('hasPermission') permissions: Array<string>;

    perms: [string] = null;

    constructor(private _el: ElementRef,
        private authService: AuthService) { }

    ngOnInit() {

        this.perms = JSON.parse(this.authService.getPermissons());

        if(undefined == this.perms){
            this.perms = [''];
        }

        //Single permission
        if (this.permissions && this.permissions.length == 1) {
            if (this.perms.indexOf(this.permissions[0]) === -1) {
                this._el.nativeElement.remove();
            }
        }

        //Multiple permission
        if (this.permissions && this.permissions.length > 1) {
            if (!this.permissions.every(permission => this.perms.includes(permission))) {
                this._el.nativeElement.remove();
            }
        }
    }
}
