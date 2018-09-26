import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GrowlComponent } from './growl.component';
import { GrowlModule as ngGrowlModule } from 'primeng/primeng';

@NgModule({
  imports: [
    CommonModule,
    ngGrowlModule
  ],
  declarations: [GrowlComponent],
  exports: [GrowlComponent]
})
export class GrowlModule { }
