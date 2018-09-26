import {Expose, serialize} from "class-transformer";
import { ConnectionDetailDto } from "app/dashboard/shared/dto/connectionDetailDto";

export class ContractDto {

  @Expose({name: 'idType'})
  idType: string;

  @Expose({name: 'conyNumber'})
  conyNumber: string;

  @Expose({name: 'createdDate'})
  createdDate: string;

  @Expose({name: 'contractNo'})
  contractNo: string;

  @Expose({name: 'busSegment'})
  busSegment: string;

  @Expose({name: 'idNumber'})
  idNumber: string;

  @Expose({name: 'connectionDetailDto'})
  connectionDetailDto: ConnectionDetailDto;

  @Expose({name: 'nonPayInvoiceCount'})
  nonPayInvoiceCount: number;

  constructor(contractDto:any) {
    if(!contractDto){
      return;
    }
    this.setBusSegment(contractDto.busSegment);
    this.setContractNo(contractDto.contractNo);
    this.setConyNumber(contractDto.conyNumber);
    this.setCreatedDate(contractDto.createdDate);
    this.setIdNumber(contractDto.idNumber);
    this.setIdType(contractDto.idType);
    this.setConnectionDetailDto(contractDto.idType);
    this.setNonPayInvoiceCount(contractDto.nonPayInvoiceCount);
  }
  setIdType(value: string) {
    this.idType = value;
  }

  setConyNumber(value: string) {
    this.conyNumber = value;
  }

  setCreatedDate(value: string) {
    this.createdDate = value;
  }

  setContractNo(value: string) {
    this.contractNo = value;
  }

  setBusSegment(value: string) {
    this.busSegment = value;
  }

  setIdNumber(value: string) {
    this.idNumber = value;
  }

  setConnectionDetailDto(value:any){
      this.connectionDetailDto = value;
  }

  setNonPayInvoiceCount(value:number){
    this.nonPayInvoiceCount = value; 
  }

  toJSon(): any {
    return serialize(this);
  }
}
