/**
 * Created by roshane on 6/23/17.
 */
import {Injectable} from '@angular/core';
import {Validator} from "./validation-utils";

@Injectable()
export class StringUtils {

  constructor(private _validator: Validator) {}

  isNullOrEmpty(input: string): boolean {
    if (this._validator.isNotNull(input)) {
      input = input.trim();
      return input == '';
    }
    return true;
  }

  hasText(input: string): boolean {
    var isEmpty = this.isNullOrEmpty(input);
    return (!isEmpty && input.trim().length > 0 );
  }

  static hasPattern(value: string, pattern: string){
    return String(value).match(pattern);
  }

}
