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
    public ResponseEntity<APIResponse> addMOUAgreementreq(@RequestBody List<MOUAgreement> mouAgreements, @RequestHeader(value = "user") String user){
        List<MOUAgreement> mouAgreementsResp = new ArrayList<>();
        for(MOUAgreement mouAgreement : mouAgreements){
            MOUAgreement mouAgreementSaved;
            boolean isMouAgreementFound = false;
            boolean isCustomerFound = false;

            List<Customer> customers = mouAgreement.getCustomers();
            List<MOUAgreementBudgetType> mouAgreementBudgetTypes = mouAgreement.getMouAgreementBudgetTypes();

            //save mou agreement
            List<MOUAgreementBudgetType> mouAgreementBudgetTypesSaved = null;
            mouAgreementSaved = (MOUAgreement) mouAgreementController.getMOUAgreementExists(mouAgreement, user).getBody().getData();
            if(mouAgreementSaved == null){
                mouAgreementSaved = mouAgreementController.addMOUAgreement(mouAgreement, user).getBody();
                //if the MOU Agreement is new one need to generate MOUAgreementBudgetTypes and the responsible BudgetList;
                mouAgreementBudgetTypesSaved= generateBudgetsForMOUAgreement(mouAgreementSaved, mouAgreementBudgetTypes, user);
                mouAgreementSaved.setMouAgreementBudgetTypes(mouAgreementBudgetTypesSaved);
            }else{
                isMouAgreementFound = true;
                mouAgreementBudgetTypesSaved= getBudgetsForMOUAgreement(mouAgreementSaved, user);
                mouAgreementSaved.setMouAgreementBudgetTypes(mouAgreementBudgetTypesSaved);
            }

            //save customer
            Customer customerSaved = null;
            List<Customer> customersSaved = new ArrayList<>();
            List<CustomerMOUAgreement> customerMOUAgreementsSaved = new ArrayList<>();
            for(Customer customer: customers){
                //Second step need to check Customer available or not and need to save Customer
                CustomerMOUAgreement customerMOUAgreementSaved = null;
                CustomerMOUAgreement customerMOUAgreement = new CustomerMOUAgreement();
                customerSaved = (Customer) customerController.getCustomerByKeys(customer, user).getBody().getData();
                if(customerSaved == null){
                    customerSaved = (Customer) customerController.saveCustomer(customer, user).getBody().getData();
                }else {
                    isCustomerFound = true;
                }

                customersSaved.add(customerSaved);

                if(isCustomerFound && isMouAgreementFound){
                    //Both customer and Agreement is saved already so retrieve existing customer mou agreement
                    customerMOUAgreement.setCustomerId(customerSaved.getCustomerId());
                    customerMOUAgreement.setMouAgreementId(mouAgreementSaved.getAgreementKey());
                    customerMOUAgreementSaved = (CustomerMOUAgreement) customerMOUAgreementController
                            .getCustomerMOUAgreementByKeys(customerMOUAgreement, user).getBody().getData();
                }else {
                    //One of the entity customer or Agreement is new so save new customer mou agreement
                    customerMOUAgreement.setMouAgreementId(mouAgreementSaved.getAgreementKey());
                    customerMOUAgreement.setCustomerId(customerSaved.getCustomerId());
                    customerMOUAgreementSaved = (CustomerMOUAgreement) customerMOUAgreementController
                            .saveCustomerMOUAgeement(customerMOUAgreement, user).getBody().getData();
                }
                customerMOUAgreementsSaved.add(customerMOUAgreementSaved);
            }
            mouAgreementSaved.setCustomers(customersSaved);
            //mouAgreementSaved.setCustomerMOUAgreements(customerMOUAgreementsSaved);

            mouAgreementsResp.add(mouAgreementSaved);
        }

        return ResponseBuilder.build(ResponseBuilder.success(mouAgreementsResp));


        /*boolean isMouAgreementFound = false;
        boolean isCustomerFound = false;
        //logger.info("Add MOUAgreement");
        MOUAgreementCompleteReq mouAgreementCompleteReqSaved = new MOUAgreementCompleteReq();

        List<MOUAgreement> mouAgreements = mouAgreementCompleteReq.getMouAgreements();
        List<Customer> customers = mouAgreementCompleteReq.getCustomers();
        List<MOUAgreementBudgetType> mouAgreementBudgetTypes = mouAgreementCompleteReq.getMouAgreementBudgetTypes();

        //saving customer mou agreement

        List<MOUAgreement> mouAgreementsSaved = new ArrayList<>();
        List<Customer> customersSaved = new ArrayList<>();
        List<CustomerMOUAgreement> customerMOUAgreementsSaved = new ArrayList<>();
        List<List<MOUAgreementBudgetType>> mouAgreementBudgetTypeForAgreements = new ArrayList<>();

        for(MOUAgreement mouAgreement : mouAgreements){
            //First step need to check MOU agreement available or not and need to save mouAgreement
            MOUAgreement mouAgreementSaved = null;
            List<MOUAgreementBudgetType> mouAgreementBudgetTypesSaved = null;
            mouAgreementSaved = (MOUAgreement) mouAgreementController.getMOUAgreementExists(mouAgreement, user).getBody().getData();
            if(mouAgreementSaved == null){
                mouAgreementSaved = mouAgreementController.addMOUAgreement(mouAgreement, user).getBody();
                //if the MOU Agreement is new one need to generate MOUAgreementBudgetTypes and the responsible BudgetList;
                mouAgreementBudgetTypesSaved= generateBudgetsForMOUAgreement(mouAgreementSaved, mouAgreementBudgetTypes, user);
            }else{
                isMouAgreementFound = true;
            }
            mouAgreementsSaved.add(mouAgreement);

            Customer customerSaved = null;
            for(Customer customer: customers){
                //Second step need to check Customer available or not and need to save Customer
                CustomerMOUAgreement customerMOUAgreementSaved = null;
                CustomerMOUAgreement customerMOUAgreement = new CustomerMOUAgreement();
                customerSaved = (Customer) customerController.getCustomerByKeys(customer, user).getBody().getData();
                if(customerSaved == null){
                    customerSaved = (Customer) customerController.saveCustomer(customer, user).getBody().getData();
                }else {
                    isCustomerFound = true;
                }

                customersSaved.add(customerSaved);

                if(isCustomerFound && isMouAgreementFound){
                    //Both customer and Agreement is saved already so retrieve existing customer mou agreement
                    customerMOUAgreement.setCustomerId(customerSaved.getCustomerId());
                    customerMOUAgreement.setMouAgreementId(mouAgreementSaved.getAgreementKey());
                    customerMOUAgreementSaved = (CustomerMOUAgreement) customerMOUAgreementController
                            .getCustomerMOUAgreementByKeys(customerMOUAgreement, user).getBody().getData();
                }else {
                    //One of the entity customer or Agreement is new so save new customer mou agreement
                    customerMOUAgreement.setMouAgreementId(mouAgreementSaved.getAgreementKey());
                    customerMOUAgreement.setCustomerId(customerSaved.getCustomerId());
                    customerMOUAgreementSaved = (CustomerMOUAgreement) customerMOUAgreementController
                            .saveCustomerMOUAgeement(customerMOUAgreement, user).getBody().getData();
                }
                customerMOUAgreementsSaved.add(customerMOUAgreementSaved);
            }
            mouAgreementBudgetTypeForAgreements.add(mouAgreementBudgetTypesSaved);
        }*/


    }

    private List<MOUAgreementBudgetType> getBudgetsForMOUAgreement(MOUAgreement mouAgreementSaved,String user) {
        List<MOUAgreementBudgetType> mouAgreementBudgetTypesSaved = (List<MOUAgreementBudgetType>) mouAgreementBudgetTypeController
                .getBudgetsForMOUAgreementByMouAgreement(mouAgreementSaved, user).getBody().getData();
        return mouAgreementBudgetTypesSaved;
    }

    private List<MOUAgreementBudgetType> generateBudgetsForMOUAgreement(MOUAgreement mouAgreementSaved, List<MOUAgreementBudgetType> mouAgreementBudgetTypes, String user) {
        //Saving budgetTypes
        List<MOUAgreementBudgetType> mouAgreementBudgetTypesAgreementIDAdded = new ArrayList<>();
        //MOUAgreementBudgetType mouAgreementBudgetTypeToAdd = new MOUAgreementBudgetType();
        for(MOUAgreementBudgetType mouAgreementBudgetType : mouAgreementBudgetTypes){
            mouAgreementBudgetType.setMouAgreementId(mouAgreementSaved.getAgreementKey());
            mouAgreementBudgetTypesAgreementIDAdded.add(mouAgreementBudgetType);
        }

        List<MOUAgreementBudgetType> mouAgreementBudgetTypesSaved = (List<MOUAgreementBudgetType>) mouAgreementBudgetTypeController
                .saveMouAgreementBudgetTypes(mouAgreementBudgetTypesAgreementIDAdded, user).getBody().getData();
        return mouAgreementBudgetTypesSaved;
    }
}
