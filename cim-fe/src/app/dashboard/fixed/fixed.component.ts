import { Component, OnInit } from '@angular/core';
import * as _ from "underscore";
import { MifeService } from "app/dashboard/shared/services/mife.service";
import { NotificationUtils } from "app/shared/utils/notification-utils";
import { NetworkDetail } from "app/dashboard/shared/dto/networkDetail";
import { NetworkDetailService } from "app/dashboard/shared/services/networkDetail.service";
import { NetworkDetailRespDto } from "app/dashboard/shared/dto/networkDetailRespDto";
import { BrnDto } from "app/dashboard/shared/dto/brnDto";
import { ConnectionDetailDto } from "app/dashboard/shared/dto/connectionDetailDto";
import { ContractDto } from "app/dashboard/shared/dto/contractDto";

@Component({
  selector: 'fixed',
  templateUrl: './fixed.component.html',
  styleUrls: ['./fixed.component.scss']
})
export class FixedComponent implements OnInit {

  requestPage: string = 'Fixed-Request';

  //ref Objects
  networkDetails: Array<NetworkDetailRespDto> = [];
  brnDetails: Array<BrnDto> = [];
  
  //contract
  contractFocusIndex: number = 0;
  filteredContractList: any[] = [];
  selectedContractIndex: number;

  //brn
  brnFocusIndex: number = 0;
  filteredBrnList: any[] = [];
  selectedBrnIndex: number;

  //outstandingBalance
  outstandingBalanceDetailFocusIndex: number = 0;
  filteredoutstandingBalanceDetailList: any[] = [];
  selectedoutstandingBalanceDetailIndex: number;

  selectedBrnDetail: BrnDto;


  constructor(
    private mifeService: MifeService,
    private notificationUtils: NotificationUtils,
    private networkDetailService: NetworkDetailService,
  ) { }

  ngOnInit() {
  }

  brnChanged(search){
    if(!_.isNull(search.inputKey) && !_.isEmpty(search.inputKey) && 
      !_.isNull(search.inputType) && !_.isEmpty(search.inputType)){
      if(search.inputType == 'BRN'){
        this.loadBrnDetails(search);
      }else if(search.inputType == 'CONY'){
        this.loadConyDetails(search);
      }
    }
  }

  loadBrnDetails(search){
    this.notificationUtils.showMainLoading();
      this.networkDetailService.getBrnNetworkDetails(search.inputKey, search.inputType)
        .subscribe(
          res => {
            if(!_.isNull(res)){
              if(res.statusCode == 200){
                this.notificationUtils.hideMainLoading();
                if(res.data){
                  /* res.data.forEach(networkDetail=>{
                    networkDetail = new NetworkDetailRespDto(networkDetail);
                    this.networkDetails.push(networkDetail);
                  }); */
                  let brnDetail: BrnDto = new BrnDto(res.data);
                  this.brnDetails.push(brnDetail);
                  //this.selectedBrnDetail = this.brnDetails[0];

                  this.notificationUtils.hideMainLoading();
                }else{
                  this.notificationUtils.showSadMessage("Could Not Find any Details For Key : "+search.inputKey+ " Type : "+search.inputType);
                  this.notificationUtils.hideMainLoading();
                }
              }else{
                this.notificationUtils.showErrorMessage("Exception While Retrieving Details For Key : "+search.inputKey+ " Type : "+search.inputType)
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

  //brn
  onBrnDetailListKeydown(event) {
    if ((this.brnFocusIndex < this.filteredBrnList.length) && event.key == "ArrowDown") {
      let index: number = this.brnFocusIndex;
      this.brnFocusIndex = index + 1;
    }
    if (this.brnFocusIndex != 0 && event.key == "ArrowUp") {
      let index: number = this.brnFocusIndex;
      this.brnFocusIndex = index - 1;
    }
  }

  onBrnDetailClicked(brnDetailSelected: any, i: number){
    this.selectedBrnDetail = undefined;
    this.selectedBrnDetail = brnDetailSelected;
    (this.filteredBrnList.indexOf(brnDetailSelected) == -1) ? 
      this.filteredBrnList.push(brnDetailSelected) : 
      this.filteredBrnList.splice(this.filteredBrnList.indexOf(brnDetailSelected), 1);
  }

  isBrnDetailSelected(brnDetail): boolean{
    let toRet: boolean = false;
    this.filteredBrnList.indexOf(brnDetail) == -1 ? toRet = false : toRet = true;
    return toRet;
  }
//end brn

  //outstandingDetail
  onOutstandingBalanceDetailListKeydown(event) {
    if ((this.outstandingBalanceDetailFocusIndex < this.filteredoutstandingBalanceDetailList.length) && event.key == "ArrowDown") {
      let index: number = this.outstandingBalanceDetailFocusIndex;
      this.outstandingBalanceDetailFocusIndex = index + 1;
    }
    if (this.outstandingBalanceDetailFocusIndex != 0 && event.key == "ArrowUp") {
      let index: number = this.outstandingBalanceDetailFocusIndex;
      this.outstandingBalanceDetailFocusIndex = index - 1;
    }
  }

  onOutstandingBalanceDetailClicked(outstandingDetailSelected: any, i: number){
    //console.log("Clicked : "+offer, i);
    //this.selectedOfferIndex = i;
    (this.filteredoutstandingBalanceDetailList.indexOf(outstandingDetailSelected) == -1) ? 
      this.filteredoutstandingBalanceDetailList.push(outstandingDetailSelected) : 
      this.filteredoutstandingBalanceDetailList
      .splice(this.filteredoutstandingBalanceDetailList.indexOf(outstandingDetailSelected), 1);

  }

  isOutstandingBalanceDetailSelected(outstandingBalanceDetail): boolean{
    let toRet: boolean = false;
    this.filteredoutstandingBalanceDetailList.indexOf(outstandingBalanceDetail) == -1 ? toRet = false : toRet = true;
    return toRet;
  }
  //end out standing
 
  //contract
  onContractDetailListKeydown(event) {
    if ((this.contractFocusIndex < this.filteredContractList.length) && event.key == "ArrowDown") {
      let index: number = this.contractFocusIndex;
      this.contractFocusIndex = index + 1;
    }
    if (this.contractFocusIndex != 0 && event.key == "ArrowUp") {
      let index: number = this.contractFocusIndex;
      this.contractFocusIndex = index - 1;
    }
  }

  onContractDetailClicked(contractDetailSelected: any, i: number){
    //console.log("Clicked : "+offer, i);
    //this.selectedOfferIndex = i;
    (this.filteredContractList.indexOf(contractDetailSelected) == -1) ? 
      this.filteredContractList.push(contractDetailSelected) : 
      this.filteredContractList.splice(this.filteredContractList.indexOf(contractDetailSelected), 1);

  }

  isContractDetailSelected(contractDetail): boolean{
    let toRet: boolean = false;
    this.filteredContractList.indexOf(contractDetail) == -1 ? toRet = false : toRet = true;
    return toRet;
  }  
  // end contract

  //table values
  getOutstandingBalace(brnDetail: BrnDto){
    let amount: number = 0;
    brnDetail.outstandingBalanceDtos.forEach(ob => {
      amount += ob.balance;
    })
    return amount;
  }

  getPendingOutStandingBalance(brnDetail){
    let amount: number = 0;
    /* brnDetail.outstandingBalanceDtos.forEach(ob => {
      amount += ob.balance;
    })
    return amount; */
    return amount;
  }

  gettotalConnection(connectionDetail: ConnectionDetailDto){
    //let totalConnection: number =0;
    return connectionDetail.conn + connectionDetail.npmt + connectionDetail.pend;
  }

  getBalance(contractDetail: ContractDto, selectedBrnDetail: BrnDto): number{
    let balance: number = 0;
    selectedBrnDetail.outstandingBalanceDtos.forEach(os=>{
      if(os.contractNo == contractDetail.contractNo){
        balance = os.balance;
        return balance;
      }
    });
    return balance;
  }
//end fetch table data values

  loadConyDetails(search){
    this.notificationUtils.showMainLoading();
      this.networkDetailService.getConyNetworkDetails(search.inputKey, search.inputType)
        .subscribe(
          res => {
            if(!_.isNull(res)){
              if(res.statusCode == 200){
                this.notificationUtils.hideMainLoading();
                if(res.data){
                  /* res.data.forEach(networkDetail=>{
                    networkDetail = new NetworkDetailRespDto(networkDetail);
                    this.networkDetails.push(networkDetail);
                  }); */
                  let brnDetail: BrnDto = new BrnDto(res.data);
                  this.brnDetails.push(brnDetail);
                  //this.selectedBrnDetail = this.brnDetails[0];

                  this.notificationUtils.hideMainLoading();
                }else{
                  this.notificationUtils.showSadMessage("Could Not Find any Details For Key : "+search.inputKey+ " Type : "+search.inputType);
                  this.notificationUtils.hideMainLoading();
                }
              }else{
                this.notificationUtils.showErrorMessage("Exception While Retrieving Details For Key : "+search.inputKey+ " Type : "+search.inputType)
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
}
