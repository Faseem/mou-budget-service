import {Expose, serialize} from "class-transformer";
import { PrNumberListDetail } from "app/dashboard/shared/dto/prNumberListDetail";

export class PRNumberDetails {

  @Expose({name: 'pr_bill_cycle'})
  prBillCycle: string;

  @Expose({name: 'productType'})
  productType: string;

  @Expose({name: 'prNumber'})
  prNumber: string;

  @Expose({name: 'list'})
  prNumberListDetail: PrNumberListDetail;

  
  constructor(prNumberDetail: any) {
    if(!prNumberDetail){
      return;
    }
    this.setPrBillCycle(prNumberDetail.prBillCycle);
    this.setPproductType(prNumberDetail.productType);
    this.setPrNumber(prNumberDetail.prNumber);
    this.setPrNumberListDetail(prNumberDetail.list);
  }

  toJSon(): any {
    return serialize(this);
  }

  setPrBillCycle(value: string){
    this.prBillCycle = value;
  }

  setPrNumber(value: string){
    this.prNumber = value;
  }

  setPproductType(value: string){
    this.productType = value;
  }

  setPrNumberListDetail(value: any){
    this.prNumberListDetail = value;
  }
}
