import {Expose, serialize} from "class-transformer";
import { PrNumberListDetail } from "app/dashboard/shared/dto/prNumberListDetail";
import { ContractDto } from "app/dashboard/shared/dto/contractDto";
import { OutstandingBalanceDto } from "app/dashboard/shared/dto/outstandingBalanceDto";
import { ConnectionDetailDto } from "app/dashboard/shared/dto/connectionDetailDto";
import { BrnDto } from "app/dashboard/shared/dto/brnDto";

export class ConyDto {

  @Expose({name: 'brnDtos'})
  brnDtos: Array<BrnDto>;

  @Expose({name: 'outstandingBalanceDto'})
  outstandingBalanceDto: number;

  @Expose({name: 'connectionDetailDto'})
  connectionDetailDto: ConnectionDetailDto;

  @Expose({name: 'nonPayInvoiceCount'})
  nonPayInvoiceCount: number;

  @Expose({name: 'conyNo'})
  conyNo: string;

  
  constructor(conyDto: any) {
    if(!conyDto){
      return;
    }
    this.setBrnDtos(conyDto.brnDtos);
    this.setOutstandingBalanceDto(conyDto.outstandingBalanceDto);
    this.setConnectionDetailDto(conyDto.connectionDetailDto);
    this.setNonPayInvoiceCount(conyDto.nonPayInvoiceCount);
    this.setConyNo(conyDto.conyNo);
  }

  toJSon(): any {
    return serialize(this);
  }

  setBrnDtos(value: any){
    this.brnDtos = value;
  }

  setOutstandingBalanceDto(value: any){
    this.outstandingBalanceDto = value;
  }

  setConnectionDetailDto(value: any){
    this.connectionDetailDto = value;
  }

  setNonPayInvoiceCount(value: any){
    this.nonPayInvoiceCount = value;
  }

  setConyNo(value: any){
    this.conyNo = value;
  }
}
