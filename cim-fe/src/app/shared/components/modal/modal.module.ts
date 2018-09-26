import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalComponent } from "./modal.component";
import { ConfirmationModalComponent } from './confirmation.modal.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [ModalComponent, ConfirmationModalComponent],
  exports: [ModalComponent, ConfirmationModalComponent]
})
export class ModalModule { }
