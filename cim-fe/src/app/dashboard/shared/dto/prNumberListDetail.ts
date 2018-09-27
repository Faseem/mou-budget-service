import {Expose, serialize} from "class-transformer";

export class PrNumberListDetail {

  @Expose({name: 'mobile'})
  mobile: string;

  @Expose({name: 'billCycle'})
  billCycle: string;

  @Expose({name: 'mainPkgId'})
  main_pkg_id: string;

  @Expose({name: 'contractId'})
  contract_id: string;

  @Expose({name: 'accountStatus'})
  account_status: string;

  @Expose({name: 'accountNo'})
  account_no: string;

  
  constructor() {
  }

  toJSon(): any {
    return serialize(this);
  }
}
