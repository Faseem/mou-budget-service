import { Validators, AbstractControl } from '@angular/forms';

import * as _ from 'underscore';
import * as _s from 'underscore.string';
import { Validator } from 'app/shared/utils/validation-utils';
import { concat } from 'rxjs/observable/concat';
import { ConfigConstants } from 'app/config/config.constants';

export class ConfigurationValidation {

    static isDate()    {
        return Validators.pattern(ConfigConstants.DATE_FORMAT_REG_TXT);        
    }

    static isBigDecimal()    {
        return Validators.pattern('^\d*\.?\d*$');        
    }

    // static isNumber(){
    //     return Validators.pattern('^\d*$')
    // }

    static isNumber() {
        return (control: AbstractControl): {[key: string]: boolean} => {
          if (!control.value || 0 === control.value.length) {
            return null;
          }
     
          if (!isNaN(control.value)) {
            return null;
          }
          return {"valueIsNumber": true};
        };
    }

    static isLength(length: number) {
        return (control: AbstractControl): {[key: string]: boolean} => {
          if (!control.value || 0 === control.value.length) {
            return null;
          }
     
          if (_.isNumber(Number(control.value)) && control.value.length <= length) {
            return null;
          }
          return {"dataLength": true};
        };
    }

    static isMaxLength(length: number) {
      return (control: AbstractControl): {[key: string]: boolean} => {
        if (!control.value || 0 === control.value.length) {
          return null;
        }
   
        if (_.isNumber(Number(control.value)) && control.value.length <= length) {
          return null;
        }
        return {"dataFixLength": true};
      };
  }

}