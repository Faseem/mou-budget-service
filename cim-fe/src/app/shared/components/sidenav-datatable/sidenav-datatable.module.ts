import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SidenavDatatableComponent} from "./sidenav-datatable.component";
import {DatatableModule} from "../datatable/datatable.module";

@NgModule({
  imports: [
    CommonModule,
    DatatableModule
  ],
  declarations: [SidenavDatatableComponent],
  exports: [SidenavDatatableComponent]
})
export class SidenavDatatableModule { }
