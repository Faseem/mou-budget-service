import {Component, Input, EventEmitter, Output, ViewEncapsulation} from "@angular/core";
import {LazyLoadEvent} from 'primeng/components/common/api';
import {ConfirmationService} from 'primeng/primeng';
import {DatatableCommon} from './datatable.model';
import {EventData} from "../../models/filterdata.model";
import {ColumnData, ColumnType} from "../../models/columndata.model";


@Component({
  moduleId: module.id,
  selector: 'data-table',
  templateUrl: 'datatable.component.html',
  styleUrls: ['datatable.component.scss'],
  providers: [ConfirmationService]
})

export class DatatableComponent {

  name = '';
  needLazyLoad = true;
  eventData: EventData;
  columnType = ColumnType;

  private _data: any[];

  @Input()
  set data(value: any[]) {
    this._data = value;
    this.end = this.start + value.length;
  }

  get data() {
    return this._data;
  }

  @Input() defaultSortField: string;
  @Input() cols: ColumnData[];
  @Input() totalRecords: number;
  @Input() showPaginator: boolean;

  @Output() loadDataTableLazy = new EventEmitter<LazyLoadEvent>();
  @Output() rowClick = new EventEmitter<any>();
  @Output() buttonClick = new EventEmitter<EventData>();

  start: number;
  end: number;

  constructor(private _confirmationService: ConfirmationService) {
    DatatableCommon.component = this;
    this.showPaginator = true;
  }

  // trigger when lazy loading the datatable
  onLoadDataTableLazy(event: LazyLoadEvent) {
    if (this.needLazyLoad) {
      this.start = event.first;
      this.loadDataTableLazy.emit(event);
    }
  }

  // trigger when click on template buttons on datatable
  onButtonClick(name: string, eventdata: any) {
    this.eventData = new EventData();
    this.eventData.actionName = name;
    this.eventData.eventData = eventdata;
    this.buttonClick.emit(this.eventData);
  }

  onRowClick(event: any) {
    this.rowClick.emit(event);
  }
}
