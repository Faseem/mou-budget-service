import {Injectable} from '@angular/core';
import {toASCII} from 'punycode';

import * as _ from "underscore";

@Injectable()
export class CoreService {

  public getUser(): any {
    return this.getFromLocalStorate('currentUser');
  }

  public setUser(user: any) {
    this.storeInLocalStorage('currentUser', user);
  }

  public getSalesPerson(): any {
    return this.getFromLocalStorate('salesPerson');
  }

  public setSalesPerson(salesPerson: any) {
    this.storeInLocalStorage('salesPerson', salesPerson);
  }

  private storeInLocalStorage(key:string, obj: any) {
    localStorage.setItem(key, JSON.stringify(obj));
  }

  private getFromLocalStorate(key: string): any{
    let value = localStorage.getItem(key);
    if(!_.isUndefined(value) && _.isObject(value)){
      return JSON.parse(value);
    }
    return undefined;
  }
}
