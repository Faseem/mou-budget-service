import {Injectable, OnInit} from '@angular/core';
import {classToPlain, Expose, plainToClass, Type} from "class-transformer";
import {Location} from "../dto/location";
import * as _ from "underscore";
import { CoreService } from "app/shared/core.service";
import { User } from "app/dashboard/shared/dto/user";

@Injectable()
export class StorageService implements OnInit {

  @Expose({name: 'user'})
  @Type(() => User)
  private _user: User;

  @Expose({name: 'location'})
  @Type(() => Location)
  private _location: Location;

  @Expose({name: 'salesPerson'})
  @Type(() => User)
  private _salesPerson: User;

  constructor(private coreService: CoreService) {
  }

  ngOnInit(): void {
    this.resetAll();
  }

  getUser(): User {
    if (_.isUndefined(this._user) || _.isNull(this._user)) {
      this._user = new User();
    }
    return this._user;
  }

  getLocation(): Location {
    if (_.isUndefined(this._location) || _.isNull(this._location)) {
      this._location = new Location();
    }
    return this._location;
  }

  getSalesPerson(): User {
    return this._salesPerson;
  }

  setUser(user: User): StorageService {
    this._user = user;
    return this;
  }

  setSalesPerson(salesPerson: User): StorageService {
    this._salesPerson = salesPerson;
    return this;
  }

  clearSalesPerson() {
    this._salesPerson = undefined;
  }

  toJson(): any {
    return classToPlain(this);
  }

  getCart(): any {
    let salesPerson = this.coreService.getSalesPerson();
    if (!_.isUndefined(salesPerson)) {
      this.setSalesPerson(salesPerson);
    }
    let cart = {};
    if (this._salesPerson != null) {
      cart['salesPersonCode'] = this.getSalesPerson().loginUserName;
    }
    return cart;
  }

  resetAll() {
    this._user = new User();
  }

  resetPaymentType() {
  }

}
