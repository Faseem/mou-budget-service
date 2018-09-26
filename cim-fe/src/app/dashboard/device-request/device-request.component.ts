import { Component, OnInit } from '@angular/core';
import { NotificationUtils } from "app/shared/utils/notification-utils";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import { MifeService } from "app/dashboard/shared/services/mife.service";

import * as _ from "underscore";
import * as _s from "underscore.string";
import { PRNumberDetails } from "app/dashboard/shared/dto/pRNumberDetails";

@Component({
  selector: 'device-request',
  templateUrl: './device-request.component.html',
  styleUrls: ['./device-request.component.scss']
})
export class DeviceRequestComponent implements OnInit {

  //brNumber: string;
  brForm: FormGroup;

  //objects
  pRNumberDetailsList: Array<PRNumberDetails> = [];


  //table
  offerFocusIndex: number = 0;
  filteredOfferList: any[] = [];
  selectedOfferIndex: number;

  constructor(
    private notificationUtils: NotificationUtils,
    private formBuilder: FormBuilder,
    private mifeService: MifeService
  ) { }

  ngOnInit() {
    this.notificationUtils.hideMainLoading();
    this.brForm = this.formBuilder.group({
      brNumber: ['', [Validators.required]],
    });
  }

  onBrNumberSubmit(){
    this.notificationUtils.showMainLoading();
    let brNumber = this.brForm.controls['brNumber'].value;
    let accountStatus = 'C,B,T,D'
    if (_s.isBlank(brNumber)) {
      return;
    }
    this.mifeService.getNumberDetailsForBRN(brNumber, accountStatus)
      .subscribe(
        res => {
          if(!_.isNull(res)){
            if(res.statusCode == 200){
              this.notificationUtils.hideMainLoading();
              if(res.data){
                res.data.forEach(prNumberDetail=>{
                  prNumberDetail = new PRNumberDetails(prNumberDetail);
                  this.pRNumberDetailsList.push(prNumberDetail);
                });
                this.notificationUtils.hideMainLoading();
              }else{
                this.notificationUtils.showSadMessage("Could Not Find any PR Number Details");
                this.notificationUtils.hideMainLoading();
              }
            }else{
              this.notificationUtils.showErrorMessage("Exception While Retrieving the PR Number Details");
              this.notificationUtils.hideMainLoading();
            }
          }else{
            this.notificationUtils.hideMainLoading();
          }
        },
        error => {
          this.notificationUtils.hideMainLoading();
        });
  }

  onPrNumberDetailListKeydown(event) {
    if ((this.offerFocusIndex < this.filteredOfferList.length) && event.key == "ArrowDown") {
      let index: number = this.offerFocusIndex;
      this.offerFocusIndex = index + 1;
    }
    if (this.offerFocusIndex != 0 && event.key == "ArrowUp") {
      let index: number = this.offerFocusIndex;
      this.offerFocusIndex = index - 1;
    }
     /* if (event.key == "Enter") {
       this.elementEventsUtils.clickElement('offerSelectBtn' + this.offerFocusIndex);
      }
      if (event.key == " ") {
        if ($('#offerItemListTbody').length) {
          this.elementEventsUtils.focusToElement('offerItemConfigBtn0');
        }
     } */
  }

  onPrNumberDetailClicked(prNumberDetailSelected: any, i: number){
    //console.log("Clicked : "+offer, i);
    //this.selectedOfferIndex = i;
    (this.filteredOfferList.indexOf(prNumberDetailSelected) == -1) ? this.filteredOfferList.push(prNumberDetailSelected) : this.filteredOfferList.splice(this.filteredOfferList.indexOf(prNumberDetailSelected), 1);

  }

  isPrNumberDetailSelected(prNumberDetail): boolean{
    let toRet: boolean = false;
    this.filteredOfferList.indexOf(prNumberDetail) == -1 ? toRet = false : toRet = true;
    return toRet;
  }

}
