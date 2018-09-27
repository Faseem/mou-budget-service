/**
 * Created by manoj on 6/29/17.
 */
import { Injectable } from '@angular/core';

@Injectable()
export class Validator {

  constructor() { }

  isNull(value: any): value is null {
    if (value) {
      return false;
    }
    return true;
  }

  isNotNull(value: any): boolean {
    if (value) {
      return true;
    }
    return false;
  }

  hasValue(value: number): boolean {
    return this.isNotNull(value);
  }

  isNumber(value: any): value is number {
    let numberRegex: RegExp = new RegExp('^[0-9]+(\.[0-9]+)?$');
    return this.isNotNull(value) && !isNaN(Number(value)) && numberRegex.test(value);
  }

  isNIC(value: string): boolean {
    let nicRegex: RegExp = new RegExp('^([0-9]{12})|([0-9]{9}[vVxX])$');
    return this.isNotNull(value) && (value.length == 10 || value.length == 12) && nicRegex.test(value);
  }

  isNewNIC(value: string): boolean {
    let nicRegex: RegExp = new RegExp('^[0-9]{12}$');
    return this.isNotNull(value) && value.length == 12 && nicRegex.test(value);
  }

  isOldNIC(value: string): boolean {
    let nicRegex: RegExp = new RegExp('^[0-9]{9}[vVxX]$');
    return this.isNotNull(value) && value.length == 10 && nicRegex.test(value);
  }

  isMobileNumber(value: any): boolean {
    return this.isNotNull(value) && this.isNumber(value) && Number.isInteger(value) && value.toString().length == 9;
  }
}
