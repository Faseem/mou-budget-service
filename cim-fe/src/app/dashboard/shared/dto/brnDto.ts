import {Expose, serialize} from "class-transformer";
import { PrNumberListDetail } from "app/dashboard/shared/dto/prNumberListDetail";
import { ContractDto } from "app/dashboard/shared/dto/contractDto";
import { OutstandingBalanceDto } from "app/dashboard/shared/dto/outstandingBalanceDto";
import { ConnectionDetailDto } from "app/dashboard/shared/dto/connectionDetailDto";

export class BrnDto {

  @Expose({name: 'contractDtoList'})
  contractDtoList: Array<ContractDto>;

  @Expose({name: 'outstandingBalanceDtos'})
  outstandingBalanceDtos: Array<OutstandingBalanceDto>;

  @Expose({name: 'connectionDetailDto'})
  connectionDetailDto: ConnectionDetailDto;

  @Expose({name: 'nonPayInvoiceCount'})
  nonPayInvoiceCount: number;

  @Expose({name: 'brnNo'})
  brnNo: string;

  
  constructor(brnDto: any) {
    if(!brnDto){
      return;
    }
    this.setContractDtoList(brnDto.contractDtoList);
    this.setOutstandingBalanceDtos(brnDto.outstandingBalanceDtos);
    this.setConnectionDetailDto(brnDto.connectionDetailDto);
    this.setNonPayInvoiceCount(brnDto.nonPayInvoiceCount);
    this.setBrnNo(brnDto.brnNo);
  }

  toJSon(): any {
    return serialize(this);
  }

  setContractDtoList(value: any){
    this.contractDtoList = value;
  }

  setOutstandingBalanceDtos(value: any){
    this.outstandingBalanceDtos = value;
  }

  setConnectionDetailDto(value: any){
    this.connectionDetailDto = value;
  }

  setNonPayInvoiceCount(value: any){
    this.nonPayInvoiceCount = value;
  }

  setBrnNo(value: any){
    this.brnNo = value;
  }
}
