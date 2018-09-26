import { appConfig } from "environments/environment";
import { concat } from "rxjs/observable/concat";

/**
 * Application configurations
 *
 * @type {{appRoot: string}}
 */
export const CONFIGURATIONS = {
  appRoot: appConfig.apiRoot,
  permissionRoot: appConfig.permissionRoot,
  permissionUrl: "/admin-portal/api/admin-portal/authorization/permissions/${username}"
};
/**
 * Api end points for Users
 *
 * @type {{getCurrentUser: string}}
 */
export const USER_ENDPOINTS = {
  getCurrentUser: '/users/current',
  getCreditLimit: '/users/{profileId}/credit-limit',
  getSalesPersons: '/users/sales-persons',
  searchSalesPersons: '/users'
};

/**
 * Api end points for Users
 *
 * @type {{getCurrentUser: string}}
 */
export const MIFE_ENDPOINTS = {
  getPRForBRNumber: '/api/prDetails/{brNumber}',
  getNumberDetails: '/api/prNumberDetails/{brNumber}/{accountStatus}',
  getNetworkDetails: '/api/networkStayDetails/{inputKey}/{inputType}',
};

export const NETWORK_DETAIL_ENDPOINTS = {
  getBrnNetworkDetails: '/networkDetail/getBrnNetworkDetails/{inputKey}/{inputType}',
  getConyNetworkDetails: '/networkDetail/getConyNetworkDetails/{inputKey}/{inputType}',
  getContractNetworkDetails: '/networkDetail/getContractNetworkDetails/{inputKey}/{inputType}',
};

/**
 * Api end points for Users
 *
 * @type {{getCurrentUser: string}}
 */
export const LOGIN_ENDPOINTS = {
  getCurrentUser: '/login/current',
  getCreditLimit: '/users/{profileId}/credit-limit',
  getSalesPersons: '/users/sales-persons',
  searchSalesPersons: '/users'
};


export const REGEX_PATTERNS = {
  NIC_PATTERN: '[0-9]{12}|[0-9]{9}[xXvV]{1}',
  START_NOMINEE: '7[7|6][0-9]{7}',
  CONTACT_NUMBER: '[0-9]{9}',
  MOBILE_NUMBER: '7[0-9]{8}',
  EMAIL: '[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}',
  EMAIL_EXTENDED: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
  DIGIT_ONLY:/^\d+$/
}

export const REGEX_STRINGS = {
  EMAIL_EXTENDED: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
  DIGIT_ONLY:/^\d+$/,
  MOBILE_NUMBER: /^7[0-9]{8}$/,
  START_NOMINEE: /^7[7|6][0-9]{7}$/,
  CONTACT_NUMBER: /^[0-9]{9}$/,
  ALL_NUMBERS: /^[0-9]*$/
}
