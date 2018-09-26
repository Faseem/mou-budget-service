import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ColumnData} from "../../models/columndata.model";
import {EventData} from "../../models/filterdata.model";
import {LazyLoadEvent} from "primeng/primeng";
import {trigger, state, style, animate, transition, AnimationTriggerMetadata} from '@angular/animations';

@Component({
  selector: 'sidenav-datatable',
  templateUrl: './sidenav-datatable.component.html',
  styleUrls: ['./sidenav-datatable.component.scss']
})
export class SidenavDatatableComponent implements OnInit {

  @Input() data: any[];
  @Input() defaultSortField: string;
  @Input() cols: ColumnData[];
  @Input() totalRecords: number;
  @Input() showPaginator: boolean;

  @Output() loadDataTableLazy = new EventEmitter<LazyLoadEvent>();
  @Output() rowClick = new EventEmitter<any>();
  @Output() buttonClick = new EventEmitter<EventData>();

  @ViewChild('sideNav') sideNav: ElementRef;
  @ViewChild('table') table: ElementRef;

  constructor() {
  }

  ngOnInit() {
  }

  // trigger when lazy loading the datatable
  onLoadDataTableLazy(event: LazyLoadEvent) {
    this.loadDataTableLazy.emit(event);
  }

  // trigger when click on template buttons on datatable
  onRowClick(event: any) {
    this.rowClick.emit(event);
    this.openNav();
  }

  onButtonClick(event: EventData) {
    this.buttonClick.emit(event);
  }

  /* Set the width of the side navigation to 250px */
  openNav() {
    this.sideNav.nativeElement.style.transform = 'translate3d(0, 0, 0)';
    this.sideNav.nativeElement.className = "col-4 sidenav card";
    this.sideNav.nativeElement.style.display = "block";
    this.table.nativeElement.className = "col-8";
  }

  /* Set the width of the side navigation to 0 */
  closeNav() {
    this.sideNav.nativeElement.style.transform = 'translate3d(100%, 0, 0)';
    this.sideNav.nativeElement.style.display = "none";
    this.table.nativeElement.className = "col-12";
  }
}
