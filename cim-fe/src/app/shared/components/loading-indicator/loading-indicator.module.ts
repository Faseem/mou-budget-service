import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoadingIndicatorComponent} from "./loading-indicator.component";
import {NetworkState} from "./network-state.service";

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    LoadingIndicatorComponent
  ],
  exports: [LoadingIndicatorComponent],
  providers: [NetworkState]
})
export class LoadingIndicatorModule { }
