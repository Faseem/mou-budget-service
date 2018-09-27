import {Expose, serialize} from "class-transformer";
import { PrNumberListDetail } from "app/dashboard/shared/dto/prNumberListDetail";

export class NetworkDetail {

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

  constructor(networkDetail: any) {
    if(!networkDetail){
      return;
    }
    this.setIdType(networkDetail.idType);
    this.setBusSegment(networkDetail.busSegment);
    this.setContractNo(networkDetail.contractNo);
    this.setCreatedDate(networkDetail.createdDate);
    this.setIdNumber(networkDetail.idNumber);
    this.setConyNumber(networkDetail.conyNumber);
  }

  setIdType(idType){
    this.idType = idType;
  }

  setConyNumber(conyNumber){
    this.conyNumber = conyNumber;
  }

  setCreatedDate(createdDate){
    this.createdDate = createdDate;
  }

  setContractNo(contractNo){
    this.contractNo = contractNo;
  }

  setBusSegment(busSegment){
    this.busSegment = busSegment;
  }

  setIdNumber(idNumber){
    this.idNumber = idNumber;
  }


  toJSon(): any {
    return serialize(this);
  }
}
