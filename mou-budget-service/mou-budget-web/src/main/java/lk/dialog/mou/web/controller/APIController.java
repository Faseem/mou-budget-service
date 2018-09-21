package lk.dialog.mou.web.controller;

import lk.dialog.mou.aspect.annotations.EnableAudit;
import lk.dialog.mou.domain.*;
import lk.dialog.mou.web.exception.ApplicationException;
import lk.dialog.mou.web.exception.DuplicateEntryException;
import lk.dialog.mou.web.util.APIResponse;
import lk.dialog.mou.web.util.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aux072 on 19/09/2018.
 */
@RestController
@RequestMapping(value = "/api")
public class APIController {

    private static final Logger logger = LoggerFactory.getLogger("MOUAgreementController");

    @Autowired
    MOUAgreementController mouAgreementController;

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerMOUAgreementController customerMOUAgreementController;

    @Autowired
    MOUAgreementBudgetTypeController mouAgreementBudgetTypeController;

    @Autowired
    BudgetTypeController budgetTypeController;

    @Autowired
    BudgetController budgetController;

    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");


    @EnableAudit(description = "MOUAgreementController's addMOUAgreementReq ")
    @PostMapping(value = "/addMOUAgreementReq")
    public ResponseEntity<APIResponse> addMOUAgreementreq(@RequestBody MOUAgreementCompleteReq mouAgreementCompleteReq, @RequestHeader(value = "user") String user){
        boolean isMouAgreementFound = false;
        boolean isCustomerFound = false;
        //logger.info("Add MOUAgreement");
        MOUAgreementCompleteReq mouAgreementCompleteReqSaved = new MOUAgreementCompleteReq();

        MOUAgreement mouAgreement = mouAgreementCompleteReq.getMouAgreement();
        Customer customer = mouAgreementCompleteReq.getCustomer();
        List<MOUAgreementBudgetType> mouAgreementBudgetTypes = mouAgreementCompleteReq.getMouAgreementBudgetTypes();

        //First step need to check MOU agreement available or not and need to save mouAgreement
        MOUAgreement mouAgreementSaved = (MOUAgreement) mouAgreementController.getMOUAgreementExists(mouAgreement, user).getBody().getData();
        if(mouAgreementSaved == null){
            mouAgreementSaved = mouAgreementController.addMOUAgreement(mouAgreement, user).getBody();
        }else{
            isMouAgreementFound = true;
        }

        //Second step need to check Customer available or not and need to save Customer
        Customer customerSaved = (Customer) customerController.getCustomerByKeys(customer, user).getBody().getData();
        if(customerSaved == null){
            customerSaved = (Customer) customerController.saveCustomer(customer, user).getBody().getData();
        }else {
            isCustomerFound = true;
        }

        if(isCustomerFound && isMouAgreementFound){
            return ResponseBuilder.build(ResponseBuilder.failed(null, new DuplicateEntryException("MOUAgreement Already saved for the customer")));
        }

        //Save CustomerMouAgreement
        CustomerMOUAgreement customerMOUAgreement = new CustomerMOUAgreement();
        customerMOUAgreement.setMouAgreementId(mouAgreementSaved.getAgreementKey());
        customerMOUAgreement.setCustomerId(customerSaved.getCustomerId());
        CustomerMOUAgreement customerMOUAgreementSaved = (CustomerMOUAgreement) customerMOUAgreementController
                                                            .saveCustomerMOUAgeement(customerMOUAgreement, user).getBody().getData();

        //Saving budgetTypes
        List<MOUAgreementBudgetType> mouAgreementBudgetTypesAgreementIDAdded = new ArrayList<>();
        //MOUAgreementBudgetType mouAgreementBudgetTypeToAdd = new MOUAgreementBudgetType();
        for(MOUAgreementBudgetType mouAgreementBudgetType : mouAgreementBudgetTypes){
            mouAgreementBudgetType.setMouAgreementId(mouAgreementSaved.getAgreementKey());
            mouAgreementBudgetType.setCustomerId(customerSaved.getCustomerId());
            mouAgreementBudgetType.setCustomerMouAgreementId(customerMOUAgreementSaved.getCustomerMOUAgreementId());
            mouAgreementBudgetTypesAgreementIDAdded.add(mouAgreementBudgetType);
        }

        List<MOUAgreementBudgetType> mouAgreementBudgetTypesSaved = (List<MOUAgreementBudgetType>) mouAgreementBudgetTypeController
                                                                    .saveMouAgreementBudgetTypes(mouAgreementBudgetTypesAgreementIDAdded, user).getBody().getData();

        mouAgreementCompleteReqSaved.setCustomer(customerSaved);
        mouAgreementCompleteReqSaved.setMouAgreement(mouAgreementSaved);
        mouAgreementCompleteReqSaved.setMouAgreementBudgetTypes(mouAgreementBudgetTypesSaved);

        return ResponseBuilder.build(ResponseBuilder.success(mouAgreementCompleteReqSaved));

    }
}
