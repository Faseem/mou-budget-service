import {Injectable} from '@angular/core';
import { HttpUtils } from "app/shared/utils/http-utils";
import { CONFIGURATIONS, MIFE_ENDPOINTS, NETWORK_DETAIL_ENDPOINTS } from "app/config/constants.model";
/* import {HttpUtils} from "../shared/utils/http-utils";
import { CONFIGURATIONS, USER_ENDPOINTS, LOGIN_ENDPOINTS } from "../config/constants.model";
import {EnableLogging} from "../shared/utils/logger-utils"; */

@Injectable()
export class NetworkDetailService {

  constructor(private httpUtils: HttpUtils) { }

  getBrnNetworkDetails(inputKey: string, inputType: string): any {
    let url = CONFIGURATIONS.appRoot + NETWORK_DETAIL_ENDPOINTS.getBrnNetworkDetails
    .replace('{inputKey}', inputKey)
    .replace('{inputType}', inputType);
    return this.httpUtils.httpGet(url);
  }

  getConyNetworkDetails(inputKey: string, inputType: string): any {
    let url = CONFIGURATIONS.appRoot + NETWORK_DETAIL_ENDPOINTS.getConyNetworkDetails
    .replace('{inputKey}', inputKey)
    .replace('{inputType}', inputType);
    return this.httpUtils.httpGet(url);
  }

  getContractNetworkDetails(inputKey: string, inputType: string): any {
    let url = CONFIGURATIONS.appRoot + NETWORK_DETAIL_ENDPOINTS.getContractNetworkDetails
    .replace('{inputKey}', inputKey)
    .replace('{inputType}', inputType);
    return this.httpUtils.httpGet(url);
  }

}
