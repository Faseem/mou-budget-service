package lk.dialog.mou.web.service.customeMOUAgreement;

import lk.dialog.mou.db.repository.CustomerMOUAgreementRepository;
import lk.dialog.mou.domain.CustomerMOUAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aux072 on 18/09/2018.
 */@Service
public class CustomerMOUAgreementServiceImpl implements CustomerMOUAgreementService{

    @Autowired
    CustomerMOUAgreementRepository customerMOUAgreementRepository;

    @Override
    public CustomerMOUAgreement addCustomerMOUAgreement(CustomerMOUAgreement customerMOUAgreement) {
        return customerMOUAgreementRepository.addCustomerMOUAgreement(customerMOUAgreement);
    }

    @Override
    public  CustomerMOUAgreement getCustomerMOUAgreementByKeys(CustomerMOUAgreement customerMOUAgreement){
        return customerMOUAgreementRepository.getCustomerMOUAgreementByKeys(customerMOUAgreement);
    }
}
