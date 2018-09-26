import {Directive, ElementRef, Input} from '@angular/core';
import {AuthGuardService} from './auth.guard.service';

@Directive({
  selector: '[authGuard]',
})
export class AuthGuardDirective {
  @Input() accessParam: AccessParam

  constructor(private element: ElementRef, private authGuardService: AuthGuardService) {
    if (!authGuardService.canAccess(this.accessParam.area)) {
      if (this.accessParam.hide) {
        element.nativeElement.remove();
      } else {
        element.nativeElement.disable();
      }
    }
  }
}

interface AccessParam {
  area: string,
  hide: boolean
}
