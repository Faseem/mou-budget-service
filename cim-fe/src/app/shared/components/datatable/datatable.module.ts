import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DatatableComponent } from './datatable.component';
import { DataTableModule, SharedModule, GrowlModule } from "primeng/primeng";

@NgModule({
  imports: [
    CommonModule, DataTableModule, SharedModule, GrowlModule, RouterModule
  ],
  declarations: [DatatableComponent],
  exports: [DatatableComponent]
})
export class DatatableModule { }
