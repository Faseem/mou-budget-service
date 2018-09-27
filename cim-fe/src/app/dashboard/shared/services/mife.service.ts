import {Injectable} from '@angular/core';
import { HttpUtils } from "app/shared/utils/http-utils";
import { CONFIGURATIONS, MIFE_ENDPOINTS } from "app/config/constants.model";
/* import {HttpUtils} from "../shared/utils/http-utils";
import { CONFIGURATIONS, USER_ENDPOINTS, LOGIN_ENDPOINTS } from "../config/constants.model";
import {EnableLogging} from "../shared/utils/logger-utils"; */

@Injectable()
export class MifeService {

  constructor(private httpUtils: HttpUtils) { }

  /**
   * get current user details
   * @returns {any} user details
   */

  getPRForBRNumber(brNumber: string): any {
    let url = CONFIGURATIONS.appRoot + MIFE_ENDPOINTS.getPRForBRNumber.replace('{brNumber}', brNumber);
    return this.httpUtils.httpGet(url);
  }

  getNumberDetailsForBRN(brNumber: string, accountStatus: string): any {
    let url = CONFIGURATIONS.appRoot + MIFE_ENDPOINTS.getNumberDetails
    .replace('{brNumber}', brNumber)
    .replace('{accountStatus}', accountStatus);
    return this.httpUtils.httpGet(url);
  }

  getNetworkStayDetail(inputKey: string, inputType: string): any {
    let url = CONFIGURATIONS.appRoot + MIFE_ENDPOINTS.getNetworkDetails
    .replace('{inputKey}', inputKey)
    .replace('{inputType}', inputType);
    return this.httpUtils.httpGet(url);
  }
}
