import {Injectable} from '@angular/core';
import {HttpUtils} from "../shared/utils/http-utils";
import { CONFIGURATIONS, USER_ENDPOINTS, LOGIN_ENDPOINTS } from "../config/constants.model";
import {EnableLogging} from "../shared/utils/logger-utils";

@Injectable()
export class UserService {

  constructor(private httpUtils: HttpUtils) { }

  /**
   * get current user details
   * @returns {any} user details
   */

  getCurrentUser(): any {
    let url = CONFIGURATIONS.appRoot + LOGIN_ENDPOINTS.getCurrentUser;
    return this.httpUtils.httpGet(url);
  }

  getSalesPersons(): any {
    let url = CONFIGURATIONS.appRoot + USER_ENDPOINTS.getSalesPersons;
    return this.httpUtils.httpGet(url);
  }

  /* getPinAuthLogs(data): any {
    let url = CONFIGURATIONS.appRoot + CUSTOMER_ENDPOINTS.getPinAuthLogs;
    return this.httpUtils.httpPost(url,data);
  } */

  getCreditLimit(profileId: string): any {
    let url = CONFIGURATIONS.appRoot + USER_ENDPOINTS.getCreditLimit.replace('{profileId}', profileId);
    return this.httpUtils.httpGet(url);
  }
  
  getSalesPersonsOnSearch(data): any {
    let url = CONFIGURATIONS.appRoot + USER_ENDPOINTS.searchSalesPersons;
    return this.httpUtils.httpPost(url, data);
  }
}
