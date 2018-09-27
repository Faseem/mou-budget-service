package lk.dialog.mou.web.controller;

import lk.dialog.mou.aspect.annotations.EnableAudit;
import lk.dialog.mou.domain.*;
import lk.dialog.mou.web.exception.AmountNotTallyException;
import lk.dialog.mou.web.service.budget.BudgetService;
import lk.dialog.mou.web.service.budgetType.BudgetTypeService;
import lk.dialog.mou.web.service.customeMOUAgreement.CustomerMOUAgreementService;
import lk.dialog.mou.web.service.customer.CustomerService;
import lk.dialog.mou.web.service.mOUAgreementBudgetType.MOUAgreementBudgetTypeService;
import lk.dialog.mou.web.service.mou.MOUAgreementService;
import lk.dialog.mou.web.service.releaseFrequency.ReleaseFrequencyService;
import lk.dialog.mou.web.service.user.UserService;
import lk.dialog.mou.web.util.APIResponse;
import lk.dialog.mou.web.util.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Aux072 on 19/09/2018.
 */
@RestController
@RequestMapping(value = "/api")
public class APIController {

    private static final Logger logger = LoggerFactory.getLogger("MOUAgreementController");

    /*@Autowired
    MOUAgreementController mouAgreementController;*/

    @Autowired
    MOUAgreementService mouAgreementService;


    /*@Autowired
    CustomerController customerController;*/

    @Autowired
    CustomerService customerService;

    /*@Autowired
    CustomerMOUAgreementController customerMOUAgreementController;*/

    @Autowired
    CustomerMOUAgreementService customerMOUAgreementService;

    /*@Autowired
    MOUAgreementBudgetTypeController mouAgreementBudgetTypeController;*/

    @Autowired
    MOUAgreementBudgetTypeService mouAgreementBudgetTypeService;

    /*@Autowired
    BudgetTypeController budgetTypeController;*/

    @Autowired
    BudgetTypeService budgetTypeService;

    @Autowired
    ReleaseFrequencyService releaseFrequencyService;

    @Autowired
    BudgetService budgetService;

    @Autowired
    UserService userService;

    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");


    @EnableAudit(description = "MOUAgreementController's addMOUAgreementReq ")
    @PostMapping(value = "/addMOUAgreementReq")
    public ResponseEntity<APIResponse> addMOUAgreementreq(@RequestBody List<MOUAgreement> mouAgreements, @RequestHeader(value = "user") String user){
        ResponseEntity<APIResponse> apiResponseResponseEntity = null;
        List<MOUAgreement> mouAgreementsResp = new ArrayList<>();
        try{
            for(MOUAgreement mouAgreement : mouAgreements){
                MOUAgreement mouAgreementSaved;
                boolean isMouAgreementFound = false;
                boolean isCustomerFound = false;

                List<Customer> customers = mouAgreement.getCustomers();
                List<MOUAgreementBudgetType> mouAgreementBudgetTypes = mouAgreement.getMouAgreementBudgetTypes();

                //save mou agreement
                List<MOUAgreementBudgetType> mouAgreementBudgetTypesSaved = null;
                mouAgreementSaved = mouAgreementService.getMOUAgreementByKeys(mouAgreement);
                if(mouAgreementSaved == null){

                    Long sysCreatedUserId = mouAgreement.getCreatedSysUser();
                    if(sysCreatedUserId == null){
                        User sysUser = userService.getUserByUserName(user);
                        if(sysUser != null){
                            mouAgreement.setCreatedSysUser(sysUser.getUserId());
                        }else{
                            User userAdded = userService.addUser(new User(user));
                            mouAgreement.setCreatedSysUser(userAdded.getUserId());
                        }
                    }

                    mouAgreementSaved = mouAgreementService.addMOUAgreement(mouAgreement);
                    //if the MOU Agreement is new one need to generate MOUAgreementBudgetTypes and the responsible BudgetList;
                    if(mouAgreementBudgetTypes!=null && mouAgreementBudgetTypes.size()>0){
                        Double totalFromBudgetTypes = mouAgreementBudgetTypes.stream()
                                .filter(a -> a != null && a.getAmount() != null)
                                .mapToDouble(MOUAgreementBudgetType::getAmount)
                                .sum();
                        if(totalFromBudgetTypes.doubleValue() == mouAgreementSaved.getAmount().doubleValue()){
                            mouAgreementBudgetTypesSaved = generateBudgetsForMOUAgreement(mouAgreementSaved, mouAgreementBudgetTypes, user);
                            mouAgreementSaved.setMouAgreementBudgetTypes(mouAgreementBudgetTypesSaved);
                        }else{
                            mouAgreementsResp.add(mouAgreementSaved);
                            throw new AmountNotTallyException("MOU Agreement's Total Amount Does not equalizing the budgets Amount");
                        }
                    }
                }else {
                    isMouAgreementFound = true;
                    mouAgreementBudgetTypesSaved = getBudgetsForMOUAgreement(mouAgreementSaved, user);
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
                    customerSaved = customerService.getCustomerByKeys(customer);
                    if(customerSaved == null){
                        Long sysCreatedUserId = customer.getCreatedSysUser();
                        if(sysCreatedUserId == null){
                            User sysUser = userService.getUserByUserName(user);
                            if(sysUser != null){
                                customer.setCreatedSysUser(sysUser.getUserId());
                                customer.setCreatedUser(sysUser.getUserName());
                            }else{
                                User userAdded = userService.addUser(new User(user));
                                customer.setCreatedSysUser(userAdded.getUserId());
                            }
                        }
                        Date customerCreatedDate = customer.getCreatedDate();
                        if(customerCreatedDate == null){
                            customer.setCreatedDate(new Date());
                        }
                        customerSaved = customerService.addCustomer(customer);
                    }else {
                        isCustomerFound = true;
                    }

                    customersSaved.add(customerSaved);

                    if(isCustomerFound && isMouAgreementFound){
                        //Both customer and Agreement is saved already so retrieve existing customer mou agreement
                        customerMOUAgreement.setCustomerId(customerSaved.getCustomerId());
                        customerMOUAgreement.setMouAgreementId(mouAgreementSaved.getAgreementKey());
                        customerMOUAgreementSaved = (CustomerMOUAgreement) customerMOUAgreementService
                                .getCustomerMOUAgreementByKeys(customerMOUAgreement);
                    }else {
                        //One of the entity customer or Agreement is new so save new customer mou agreement
                        customerMOUAgreement.setMouAgreementId(mouAgreementSaved.getAgreementKey());
                        customerMOUAgreement.setCustomerId(customerSaved.getCustomerId());
                        customerMOUAgreementSaved = (CustomerMOUAgreement) customerMOUAgreementService
                                .addCustomerMOUAgreement(customerMOUAgreement);
                    }
                    customerMOUAgreementsSaved.add(customerMOUAgreementSaved);
                }
                mouAgreementSaved.setCustomers(customersSaved);
                //mouAgreementSaved.setCustomerMOUAgreements(customerMOUAgreementsSaved);

                mouAgreementsResp.add(mouAgreementSaved);
                apiResponseResponseEntity = ResponseBuilder.build(ResponseBuilder.success(mouAgreementsResp));
            }
        }catch (Exception e){
            apiResponseResponseEntity = ResponseBuilder.build(ResponseBuilder.failed(mouAgreementsResp, e));
        }

        return apiResponseResponseEntity;

    }

    private List<MOUAgreementBudgetType> getBudgetsForMOUAgreement(MOUAgreement mouAgreementSaved,String user) {
        List<MOUAgreementBudgetType> mouAgreementBudgetTypesSaved = mouAgreementBudgetTypeService
                .getMOUAgreementBudgetTypeByAgreementId(mouAgreementSaved.getAgreementKey());
        if(null != mouAgreementBudgetTypesSaved && mouAgreementBudgetTypesSaved.size()>0){
            for(MOUAgreementBudgetType mouAgreementBudgetType : mouAgreementBudgetTypesSaved){
                List<Budget> budgetList = budgetService.getBudgetsForMouAgreementBudgetTypeId(mouAgreementBudgetType.getMouAgreementBudgetTypeId());
                mouAgreementBudgetType.setBudgetList(budgetList);
            }
        }
        return mouAgreementBudgetTypesSaved;
    }

    private List<MOUAgreementBudgetType> generateBudgetsForMOUAgreement(MOUAgreement mouAgreementSaved, List<MOUAgreementBudgetType> mouAgreementBudgetTypes, String user) {
        //Saving budgetTypes
        List<MOUAgreementBudgetType> mouAgreementBudgetTypesAgreementIDAdded = new ArrayList<>();
        //MOUAgreementBudgetType mouAgreementBudgetTypeToAdd = new MOUAgreementBudgetType();


        List<MOUAgreementBudgetType> mouAgreementBudgetTypesSaved = new ArrayList<>();
        //List<MOUAgreementBudgetType> mouAgreementBudgetTypeResponsesSaved = new ArrayList<>();
        MOUAgreementBudgetType mouAgreementBudgetTypeSaved = null;
        // MOUAgreementBudgetTypeResp mouAgreementBudgetTypeRespSaved = null;


        if(mouAgreementBudgetTypes!=null && mouAgreementBudgetTypes.size()>0){
            for(MOUAgreementBudgetType mouAgreementBudgetType : mouAgreementBudgetTypes){
                mouAgreementBudgetType.setMouAgreementId(mouAgreementSaved.getAgreementKey());
                //mouAgreementBudgetTypesAgreementIDAdded.add(mouAgreementBudgetType);
                //First step need to check BudgetTypeAvailability available or not and need to save BudgetType
                BudgetType budgetType = mouAgreementBudgetType.getBudgetType();
                BudgetType budgetTypeSaved = budgetTypeService.getBudgetTypeByKeys(budgetType);
                if(budgetTypeSaved == null){
                    budgetTypeSaved = (BudgetType) budgetTypeService.addBudgetType(budgetType);
                }
                mouAgreementBudgetType.setBudgetType(budgetTypeSaved);
                //mouAgreementBudgetTypeRespSaved.setBudgetType(budgetTypeSaved);

                //First step need to check BudgetTypeAvailability available or not and need to save BudgetType
                ReleaseFrequency releaseFrequency = mouAgreementBudgetType.getReleaseFrequency();
                ReleaseFrequency releaseFrequencySaved = releaseFrequencyService.getReleaseFrequencyByKeys(releaseFrequency);
                if(releaseFrequencySaved == null){
                    releaseFrequencySaved = releaseFrequencyService.addReleaseFrequency(releaseFrequency);
                }
                mouAgreementBudgetType.setReleaseFrequency(releaseFrequencySaved);

                mouAgreementBudgetTypeSaved = mouAgreementBudgetTypeService.addMOUAgreementBudgetType(mouAgreementBudgetType);

                List<Budget> budgetList = getBudgetList(mouAgreementBudgetTypeSaved);
                List<Budget> budgetsSaved = new ArrayList<>();
                for(Budget budget : budgetList) {
                    Budget budgetSaved = budgetService.addBudgetType(budget);
                    budgetsSaved.add(budgetSaved);
                }

                mouAgreementBudgetTypeSaved.setBudgetList(budgetsSaved);
                mouAgreementBudgetTypesSaved.add(mouAgreementBudgetTypeSaved);
            }
        }


        return mouAgreementBudgetTypesSaved;
    }


    private List<Budget> getBudgetList(MOUAgreementBudgetType mouAgreementBudgetType) {
        MOUAgreement mouAgreement = mouAgreementService.getMOUAgreementByKey(mouAgreementBudgetType.getMouAgreementId());
        List<Budget> budgetList = new ArrayList<>();
        Date endDate = mouAgreement.getEndDate();
        Date startDate = mouAgreement.getStartDate();
        Double totalAmount = mouAgreement.getAmount();
        String frequency = mouAgreementBudgetType.getReleaseFrequency().getFrequencyCode();
        //Long cusId = mouAgreementBudgetType.getCustomerId();
        Long mouAgreementBudgetTypeId = mouAgreementBudgetType.getMouAgreementBudgetTypeId();
        Double distributedAmount = (frequency.equals("THREE_QUARTER")) ? totalAmount/3 :
                (frequency.equals("FOUR_QUARTER")) ? totalAmount/4 :
                        totalAmount/12;
        while(endDate.compareTo(startDate) > 0){
            Budget budget = new Budget();
            budget.setStartDate(startDate);
            //budget.setMouCustomerId(cusId);
            budget.setMouAgreementBudgetTypeId(mouAgreementBudgetTypeId);
            budget.setUsageAmount(0.00);
            budget.setActive(false);
            budget.setAvailableAmount(distributedAmount);
            budget.setBudgetAmount(distributedAmount);
            budget.setEndDate(getEndDate(startDate, frequency));
            startDate = getNextStartDate(budget.getEndDate());
            budgetList.add(budget);
        }
        return budgetList;
    }

    private Date getNextStartDate(Date startDate){
        Date parsedDate = null;
        Calendar myCal = Calendar.getInstance();
        myCal.setTime(startDate);
        myCal.add(Calendar.DATE, +1);
        Date dateToFormat = myCal.getTime();
        String date = sdFormat.format(dateToFormat);
        //Date date1 = new Date();
        //date1.setTime(myCal.getTime().getTime());
        try {
            parsedDate = sdFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    private Date getEndDate(Date startDate, String frequency){
        int monthToAdd = (frequency.equals("THREE_QUARTER")) ? 3 :
                (frequency.equals("FOUR_QUARTER")) ? 4 : 1;
        Calendar myCal = Calendar.getInstance();
        myCal.setTime(startDate);
        myCal.add(Calendar.MONTH, +monthToAdd);
        myCal.add(Calendar.DAY_OF_MONTH, -1);
        return myCal.getTime();
    }

}
