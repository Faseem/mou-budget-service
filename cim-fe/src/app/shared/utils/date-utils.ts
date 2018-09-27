import {Injectable} from "@angular/core";
import {DatePipe} from "@angular/common";
import {IMyDateModel, INgxMyDpOptions} from "ngx-mydatepicker";
import { retry } from "rxjs/operator/retry";

@Injectable()
export class DateUtils {

  constructor() {}

  format(date: Date, format?: string): string {
    if (date) {
      if (!format) {
        format = 'yyyy/MM/dd';
      }
      return new DatePipe('LK').transform(date, format);
    }
    throw new Error(`Date is null.`);
  }

  formatNGXDatePickerDate(datePickerObject: any, format?: string): string {
    if (!format) {
      format = 'yyyy/MM/dd';
    }
    if (datePickerObject && datePickerObject.jsdate) {
      return new DatePipe('LK').transform(new Date(datePickerObject.jsdate), format);
    } else if (datePickerObject && datePickerObject.date) {
      let date = new Date(datePickerObject.date.year, datePickerObject.date.month - 1, datePickerObject.date.day);
      return new DatePipe('LK').transform(date, format);
    }
    throw new Error(`Date picker object is null.`);
  }

  nGXDatePickerToDate(datePickerObject: any){
    if (datePickerObject && datePickerObject.jsdate) {
      return new Date(datePickerObject.jsdate);
    } else if (datePickerObject && datePickerObject.date) {
      return new Date(datePickerObject.date.year, datePickerObject.date.month, datePickerObject.date.day);
    }
  }

  toDate(dateString: string): Date {
    if (dateString) {
      return new Date(dateString);
    }
    throw new Error(`Date string is null.`);
  }

  getYear(date: Date): number {
    if (date) {
      let format = 'yyyy';
      return Number(new DatePipe('LK').transform(date, format));
    }
    throw Error(`Date is null.`);
  }

  getMonth(date: Date): number {
    if (date) {
      let format = 'MM';
      return Number(new DatePipe('LK').transform(date, format));
    }
    throw Error(`Date is null.`);
  }

  getMonthInShortName(date: Date): string {
    if (date) {
      let format = 'MMM';
      return new DatePipe('LK').transform(date, format)
    }
    throw Error(`Date is null.`);
  }

  getMonthInName(date: Date): string {
    if (date) {
      let format = 'MMMM';
      return new DatePipe('LK').transform(date, format)
    }
    throw Error(`Date is null.`);
  }

  getDay(date: Date): number {
    if (date) {
      let format = 'dd';
      return Number(new DatePipe('LK').transform(date, format));
    }
    throw Error(`Date is null.`);
  }

  getYearByDateString(dateString: string): number {
    if (dateString) {
      let format = 'yyyy';
      return Number(new DatePipe('LK').transform(new Date(dateString), format));
    }
    throw Error(`Date string is null.`);
  }

  getMonthByDateString(dateString: string): number {
    if (dateString) {
      let format = 'MM';
      return Number(new DatePipe('LK').transform(new Date(dateString), format));
    }
    throw Error(`Date string is null.`);
  }

  getMonthInShortNameByDateString(dateString: string): string {
    if (dateString) {
      let format = 'MMM';
      return new DatePipe('LK').transform(new Date(dateString), format);
    }
    throw Error(`Date string is null.`);
  }

  getMonthInNameByDateString(dateString: string): string {
    if (dateString) {
      let format = 'MMMM';
      return new DatePipe('LK').transform(new Date(dateString), format);
    }
    throw Error(`Date string is null.`);
  }

  getDayByDateString(dateString: string): number {
    if (dateString) {
      let format = 'dd';
      return Number(new DatePipe('LK').transform(new Date(dateString), format));
    }
    throw Error(`Date string is null.`);
  }

  dateAdd(date: Date, count: number): Date {
    if (date) {
      return new Date(date.setDate(date.getDate() + parseInt(count.toString())));
    }
    throw Error(`Date is null.`);
  }

  dateSub(date: Date, count: number): Date {
    if (date) {
      return new Date(date.setDate(date.getDate() - parseInt(count.toString())));
    }
    throw Error(`Date is null.`);
  }

  getIMyDateModelObjFromDateObj(date: Date): IMyDateModel{
    let obj = <IMyDateModel>{
      date: {
        year: this.getYear(date),
        month: this.getMonth(date),
        day: this.getDay(date)
      }
    };
    return obj;
  }

  checkAlreadyConvertedIMyDateModel(object:any) : boolean {
    if( object.date === undefined ) {
      return false;
    } else  {
      return true;
    }
  }
}
