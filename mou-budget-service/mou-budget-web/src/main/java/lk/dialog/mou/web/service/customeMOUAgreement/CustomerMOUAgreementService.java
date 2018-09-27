package lk.dialog.mou.web.service.customeMOUAgreement;

import lk.dialog.mou.domain.CustomerMOUAgreement;

/**
 * Created by Aux072 on 18/09/2018.
 */
public interface CustomerMOUAgreementService {
    CustomerMOUAgreement addCustomerMOUAgreement(CustomerMOUAgreement customerMOUAgreement);
    CustomerMOUAgreement getCustomerMOUAgreementByKeys(CustomerMOUAgreement customerMOUAgreement);
}
