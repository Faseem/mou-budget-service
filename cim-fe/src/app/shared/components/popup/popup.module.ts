import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PopupComponent } from './popup.component';
import {ModalModule} from "ngx-bootstrap";

@NgModule({
  imports: [
    CommonModule,
    ModalModule.forRoot()
  ],
  declarations: [PopupComponent],
  exports: [PopupComponent]
})
export class PopupModule { }
