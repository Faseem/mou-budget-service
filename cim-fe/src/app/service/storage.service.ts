import {Injectable, OnInit} from '@angular/core';

import {classToPlain, Expose, plainToClass, Type} from "class-transformer";
//import {Offer} from "./offer";
//import {OfferItem} from "./offer-item";
//import {Customer} from "./customer";
//import {Location} from "./location";
import * as _ from "underscore";
//import {SubscriberAddress} from "./SubscriberAddress";
//import {CoreService} from "../../../shared/core.service";
import { User } from "app/dashboard/shared/dto/user";
/* import { Customer } from "app/dashboard/shared/dto/customer";
import { SubscriberAddress } from "app/dashboard/shared/dto/SubscriberAddress";
import { Offer } from "app/dashboard/shared/dto/offer"; */
import { CoreService } from "app/shared/core.service";

@Injectable()
export class StorageService implements OnInit {

  /* @Expose({name: 'customer'})
  @Type(() => Customer)
  private _customer: Customer;

  @Expose({name: 'subscriberAddress'})
  private _subscriberAddress: SubscriberAddress;

  

  @Expose({name: 'offerList'})
  @Type(() => Offer)
  private _offerList: Offer[]; */

  @Expose({name: 'user'})
  @Type(() => User)
  private _user: User;

  @Expose({name: 'salesPerson'})
  @Type(() => User)
  private _salesPerson: User;

  @Expose({name: 'location'})
  @Type(() => Location)
  private _location: Location;

  /* @Expose({name: 'paymentType'})
  private _paymentType: string = 'CASH_ORDER';

  @Expose({name: 'orderId'})
  private _orderId: string;

  @Expose({name: 'starNomineeNumber'})
  private _starNomineeNumber: number;

  @Expose({name: 'tokenNumber'})
  private _tokenNumber: number;

  @Expose({name: 'postalCodes'})
  private _postalCodes: any;

  @Expose({name: 'nationalities'})
  private _nationalities: any;

  @Expose({name: 'cirs'})
  private _cirs: any [];

  @Expose({name: 'documents'})
  private _documents: any[]

  @Expose({name: 'status'})
  private _status: string;

  @Expose({name: 'creditApproveRemark'})
  private _creditApproveRemark: string;

  @Expose({name: 'orderRemark'})
  private _orderRemark: string;


  @Expose({name: 'searchType'})
  private _searchType: string;

  @Expose({name: 'searchValue'})
  private _searchValue: string; */

  constructor(private coreService: CoreService) {
  }

  ngOnInit(): void {
    this.resetAll();
  }

  /* getCustomer(): Customer {
    // if (_.isUndefined(this._customer) || _.isNull(this._customer)) {
    //   this._customer = new Customer();
    // }
    return this._customer;
  }

  isCustomerUndefined() {
    return _.isUndefined(this._customer);
  }

  


  getOfferList(): Array<Offer> {
    if (_.isUndefined(this._offerList) || _.isNull(this._offerList)) {
      this._offerList = new Array<Offer>();
    }
    return this._offerList;
  }
 */
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

  getLocationCode(): Location {
    return this._location;
  }

  /* getSubscriberAddress(): SubscriberAddress {
    // if (_.isUndefined(this._subscriberAddress) || _.isNull(this._subscriberAddress)) {
    //   this._subscriberAddress = new SubscriberAddress();
    // }
    return this._subscriberAddress;
  }

  getSalesPerson(): User {
    return this._salesPerson;
  }

  getPaymentType(): string {
    return this._paymentType;
  }

  getOrderId(): string {
    return this._orderId;
  }

  getStarNomineeNumber(): number {
    return this._starNomineeNumber;
  }

  getTokenNumber(): number {
    return this._tokenNumber;
  }

  getCirs(): any [] {
    return this._cirs;
  }

  getDocuments(): any[] {
    return this._documents;
  }

  getStatus(): string {
    return this._status;
  }

  getCreditApproveRemark(): string {
    return this._creditApproveRemark;
  }

  getOrderRemark(): string {
    return this._orderRemark;
  }


  getSearchType(): string {
    return this._searchType;
  }

  getSearchValue(): string {
    return this._searchValue;
  }


  setCustomer(customer: Customer): StorageService {
    this._customer = customer;
    return this;
  }

  setLocation(location: Location): StorageService {
    this._location = location;
    return this;
  }

  setOffers(offersJson: any): StorageService {
    this._offerList = plainToClass(Offer, offersJson as Object[]);
    return this;
  }

  //Cached LOV Items
  setPostalCodes(postCodes: any) {
    this._postalCodes = postCodes
  }

  getPostalCodes(): any {
    return this._postalCodes
  }

  setNationalities(ationalities: any) {
    this._nationalities = ationalities
  }

  getNationalities(): any {
    return this._nationalities
  }


  setOfferList(offerList: Offer[]): StorageService {
    this._offerList = offerList;
    return this;
  }

  addToOffers(offer: Offer): StorageService {
    this._offerList.push(offer);
    return this;
  } */

  /* addListToOffersaddListToOffersaddListToOffers(offers: Array<Offer>): StorageService {
    let clonedOffers = [];
    offers.forEach(o => {
      let cloneOffer = jQuery.extend(true, {}, o);
      clonedOffers.push(cloneOffer);
    })
    let offerList = _.union(this._offerList, clonedOffers);
    this._offerList = offerList;
    return this;
  }

  removeFromOfferList(index: number) {
    this._offerList.splice(index, 1)
  } */

  setUser(user: User): StorageService {
    this._user = user;
    return this;
  }

  /* setSubscriberAddress(subscriberAddress: SubscriberAddress): StorageService {
    this._subscriberAddress = subscriberAddress;
    return this;
  } */

  setSalesPerson(salesPerson: User): StorageService {
    this._salesPerson = salesPerson;
    return this;
  }

  /* setPaymentType(paymentType: string): StorageService {
    this._paymentType = paymentType;
    return this;
  }

  setOrderId(orderId: string): StorageService {
    this._orderId = orderId;
    return this;
  }

  setStarNomineeNumber(value: number): StorageService {
    this._starNomineeNumber = value;
    return this;
  }

  setTokenNumber(value: number): StorageService {
    this._tokenNumber = value;
    return this;
  } */

  /* setCirs(cirs: any[]): StorageService {
    this._cirs = cirs;
    return this;
  }

  setDocuments(documents: any[]): StorageService {
    this._documents = documents;
    return this;
  }

  setStatus(status: string): StorageService {
    this._status = status;
    return this;
  }

  setCreditApproveRemark(remark: string): StorageService {
    this._creditApproveRemark = remark;
    return this;
  }

  setOrderRemark(remark: string): StorageService {
    this._orderRemark = remark;
    return this;
  }


  setSearchType(searchType: string): StorageService {
    this._searchType = searchType;
    return this;
  }

  setSearchValue(searchValue: string): StorageService {
    this._searchValue = searchValue;
    return this;
  }


  clearCustomer() {
    this._customer = new Customer();
  }

  clearLocation() {
    this._location = new Location();
  }

  clearSubscriberAddress() {
    this._subscriberAddress = new SubscriberAddress();
  } */

  clearSalesPerson() {
    this._salesPerson = undefined;
  }

  toJson(): any {
    return classToPlain(this);
  }

  /**
   * This method use to get offer from a provided offer code.
   * Usage - get the relevent offer items for the perticular
   * @param tempOfferCode
   */
  /* getOfferByOfferCode(tempOfferCode: String): Offer {
    for (let i = 0; i < this._offerList.length; i++) {
      var tempOffer: Offer = this._offerList.indexOf[i];
      if (tempOffer.offerCode == tempOfferCode) {
        return tempOffer;
      }
    }
    return null;
  } */

  /* getCart(): any {
    let salesPerson = this.coreService.getSalesPerson();
    if (!_.isUndefined(salesPerson)) {
      this.setSalesPerson(salesPerson);
    }
    let cart = {};
    cart['location'] = this.getLocation();
    cart['subscriberAddress'] = this.getSubscriberAddress();
    cart['offerList'] = this.getOfferList();
    cart['customer'] = this.getCustomer();
    if (this._salesPerson != null) {
      cart['salesPersonCode'] = this.getSalesPerson().loginUserName;
    }
    cart['paymentType'] = this.getPaymentType();
    cart['startNomineeNumber'] = this._starNomineeNumber;
    cart['tokenNumber'] = this._tokenNumber;
    cart['orderRemark'] = this._orderRemark;
    cart['creditApproveRemark'] = this._creditApproveRemark;
    cart['cartStatus'] = this._status;
    return cart;
  } */

  /* removeOfferFromCart(offer: Offer) {

  } */

   resetAll() {
    /* this._customer = undefined;
    this._location = new Location();
    this._subscriberAddress = new SubscriberAddress();
    this._offerList = new Array<Offer>(); */
    this._user = new User();
    /* this._paymentType = 'CASH_ORDER';
    this._salesPerson = undefined;
    this._orderId = undefined;
    this._starNomineeNumber = undefined;
    this._tokenNumber = undefined;

    this._searchType = undefined;
    this._searchValue = undefined; */
  } 

  /* resetPaymentType() {
    this._paymentType = 'CASH_ORDER';
  } */

}
