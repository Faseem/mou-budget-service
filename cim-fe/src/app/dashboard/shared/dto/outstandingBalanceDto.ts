import {Expose, serialize} from "class-transformer";

export class OutstandingBalanceDto {

  @Expose({name: 'contractNo'})
  contractNo: string;

  @Expose({name: 'balance'})
  balance: number;

  constructor(outstandingBalanceDto:any) {
    if(!outstandingBalanceDto){
      return;
    }
    this.setBalance(outstandingBalanceDto.balance);
    this.setContractNo(outstandingBalanceDto.contractNo);
  }

  setBalance(value: number) {
    this.balance = value;
  }

  setContractNo(value: string) {
    this.contractNo = value;
  }

  toJSon(): any {
    return serialize(this);
  }
}
