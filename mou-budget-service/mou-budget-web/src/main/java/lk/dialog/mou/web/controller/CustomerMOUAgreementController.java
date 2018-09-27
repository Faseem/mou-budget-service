package lk.dialog.mou.web.controller;

import lk.dialog.mou.aspect.annotations.EnableAudit;
import lk.dialog.mou.domain.Customer;
import lk.dialog.mou.domain.CustomerMOUAgreement;
import lk.dialog.mou.domain.MOUAgreement;
import lk.dialog.mou.web.service.customeMOUAgreement.CustomerMOUAgreementService;
import lk.dialog.mou.web.util.APIResponse;
import lk.dialog.mou.web.util.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Aux072 on 19/09/2018.
 */
@RestController
@RequestMapping(value = "/api")
public class CustomerMOUAgreementController {

    private static final Logger logger = LoggerFactory.getLogger("MOUAgreementController");

    @Autowired
    private CustomerMOUAgreementService customerMOUAgreementService;

    @EnableAudit(description = "saveCustomerMOUAgeement")
    @PostMapping(value = "/saveCustomerMOUAgeement")
    ResponseEntity<APIResponse> saveCustomerMOUAgeement(@RequestBody CustomerMOUAgreement customerMOUAgreement,
                                                 @RequestHeader(value = "user") String user){
        return ResponseBuilder
                .build(
                        ResponseBuilder
                                .success(customerMOUAgreementService.addCustomerMOUAgreement(customerMOUAgreement)));

    }

    @EnableAudit(description = "getCustomerMOUAgreementByKeys")
    @GetMapping(value = "/getCustomerMOUAgreementByKeys")
    ResponseEntity<APIResponse> getCustomerMOUAgreementByKeys(@RequestBody CustomerMOUAgreement customerMOUAgreement,
                                                        @RequestHeader(value = "user") String user){
        return ResponseBuilder
                .build(
                        ResponseBuilder
                                .success(customerMOUAgreementService.getCustomerMOUAgreementByKeys(customerMOUAgreement)));

    }

}
