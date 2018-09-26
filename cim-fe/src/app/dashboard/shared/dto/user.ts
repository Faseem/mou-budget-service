import {Expose, serialize, Type} from "class-transformer";

export class User {

  @Expose({name: 'addressLine1'})
  addressLine1: string;

  @Expose({name: 'addressLine2'})
  addressLine2: string;

  @Expose({name: 'addressLine3'})
  addressLine3: string;

  @Expose({name: 'agentCode'})
  agentCode: string;

  @Expose({name: 'agentType'})
  agentType: string;

  @Expose({name: 'branchName'})
  branchName: string;

  @Expose({name: 'brightStarMerchantCode'})
  brightStarMerchantCode: string;

  @Expose({name: 'ccbsBranchCode'})
  ccbsBranchCode: string;

  @Expose({name: 'createdBy'})
  createdBy: string;

  @Type(() => Date)
  @Expose({name: 'createdDate'})
  createdDate: Date;

  @Expose({name: 'currentlyActive'})
  currentlyActive: string;

  @Expose({name: 'dataEntryOperator'})
  dataEntryOperator: string;

  @Expose({name: 'dealerAddressId'})
  dealerAddressId: string;

  @Expose({name: 'dealerProfileId'})
  dealerProfileId: string;

  @Expose({name: 'dealerType'})
  dealerType: string;

  @Expose({name: 'dmsAgentCode'})
  dmsAgentCode: string;

  @Expose({name: 'identificationNumber'})
  identificationNumber: string;

  @Expose({name: 'identificationType'})
  identificationType: string;

  @Expose({name: 'landLine'})
  landLine: string;

  @Expose({name: 'locationCode'})
  locationCode: string;

  @Expose({name: 'loginUserName'})
  loginUserName: string;

  @Expose({name: 'mobile'})
  mobile: string;

  @Expose({name: 'name'})
  name: string;

  @Expose({name: 'outletCode'})
  outletCode: string;

  @Expose({name: 'outletId'})
  outletId: string;

  @Expose({name: 'outletName'})
  outletName: string;

  @Expose({name: 'outletType'})
  outletType: string;

  @Expose({name: 'paymentMode'})
  paymentMode: string;

  @Expose({name: 'paymentType'})
  paymentType: string;

  @Expose({name: 'pmsLocationId'})
  pmsLocationId: string;

  @Expose({name: 'pmsProfileId'})
  pmsProfileId: string;

  @Expose({name: 'postalCode'})
  postalCode: string;

  @Expose({name: 'regionCode'})
  regionCode: string;

  @Expose({name: 'sapMerchantCode'})
  sapMerchantCode: string;

  @Expose({name: 'segmentationCode'})
  segmentationCode: string;

  @Expose({name: 'status'})
  status: string;

  @Expose({name: 'supervisorCode'})
  supervisorCode: string;

  @Expose({name: 'supervisorEmailAddress'})
  supervisorEmailAddress: string;

  @Expose({name: 'userDescription'})
  userDescription: string;

  @Expose({name: 'userEmailAddress'})
  userEmailAddress: string;

  @Expose({name: 'userType'})
  userType: string;

  constructor() {
  }

  setAddressLine1(value: string): User {
    this.addressLine1 = value;
    return this;
  }

  setAddressLine2(value: string): User {
    this.addressLine2 = value;
    return this;
  }

  setAddressLine3(value: string): User {
    this.addressLine3 = value;
    return this;
  }

  setAgentCode(value: string): User {
    this.agentCode = value;
    return this;
  }

  setAgentType(value: string): User {
    this.agentType = value;
    return this;
  }

  setBranchName(value: string): User {
    this.branchName = value;
    return this;
  }

  setBrightStarMerchantCode(value: string): User {
    this.brightStarMerchantCode = value;
    return this;
  }

  setCcbsBranchCode(value: string): User {
    this.ccbsBranchCode = value;
    return this;
  }

  setCreatedBy(value: string): User {
    this.createdBy = value;
    return this;
  }

  setCreatedDate(value: Date): User {
    this.createdDate = value;
    return this;
  }

  setCurrentlyActive(value: string): User {
    this.currentlyActive = value;
    return this;
  }

  setDataEntryOperator(value: string): User {
    this.dataEntryOperator = value;
    return this;
  }

  setDealerAddressId(value: string): User {
    this.dealerAddressId = value;
    return this;
  }

  setDealerProfileId(value: string): User {
    this.dealerProfileId = value;
    return this;
  }

  setDealerType(value: string): User {
    this.dealerType = value;
    return this;
  }

  setDmsAgentCode(value: string): User {
    this.dmsAgentCode = value;
    return this;
  }

  setIdentificationNumber(value: string): User {
    this.identificationNumber = value;
    return this;
  }

  setIdentificationType(value: string): User {
    this.identificationType = value;
    return this;
  }

  setLandLine(value: string): User {
    this.landLine = value;
    return this;
  }

  setLocationCode(value: string): User {
    this.locationCode = value;
    return this;
  }

  setLoginUserName(value: string): User {
    this.loginUserName = value;
    return this;
  }

  setMobile(value: string): User {
    this.mobile = value;
    return this;
  }

  setName(value: string): User {
    this.name = value;
    return this;
  }

  setOutletCode(value: string): User {
    this.outletCode = value;
    return this;
  }

  setOutletId(value: string): User {
    this.outletId = value;
    return this;
  }

  setOutletName(value: string): User {
    this.outletName = value;
    return this;
  }

  setOutletType(value: string): User {
    this.outletType = value;
    return this;
  }

  setPaymentMode(value: string): User {
    this.paymentMode = value;
    return this;
  }

  setPaymentType(value: string): User {
    this.paymentType = value;
    return this;
  }

  setPmsLocationId(value: string): User {
    this.pmsLocationId = value;
    return this;
  }

  setPmsProfileId(value: string): User {
    this.pmsProfileId = value;
    return this;
  }

  setPostalCode(value: string): User {
    this.postalCode = value;
    return this;
  }

  setRegionCode(value: string): User {
    this.regionCode = value;
    return this;
  }

  setSapMerchantCode(value: string): User {
    this.sapMerchantCode = value;
    return this;
  }

  setSegmentationCode(value: string): User {
    this.segmentationCode = value;
    return this;
  }

  setStatus(value: string): User {
    this.status = value;
    return this;
  }

  setSupervisorCode(value: string): User {
    this.supervisorCode = value;
    return this;
  }

  setSupervisorEmailAddress(value: string): User {
    this.supervisorEmailAddress = value;
    return this;
  }

  setUserDescription(value: string): User {
    this.userDescription = value;
    return this;
  }

  setUserEmailAddress(value: string): User {
    this.userEmailAddress = value;
    return this;
  }

  setUserType(value: string): User {
    this.userType = value;
    return this;
  }

  toJson(): any {
    return serialize(this);
  }
}
