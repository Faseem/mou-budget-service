package lk.dialog.mou.web.controller;

import lk.dialog.mou.aspect.annotations.EnableAudit;
import lk.dialog.mou.domain.*;
import lk.dialog.mou.web.service.mOUAgreementBudgetType.MOUAgreementBudgetTypeService;
import lk.dialog.mou.web.service.mou.MOUAgreementService;
import lk.dialog.mou.web.util.APIResponse;
import lk.dialog.mou.web.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Aux072 on 19/09/2018.
 */
@RestController
@RequestMapping(value = "/customer")
public class MOUAgreementBudgetTypeController {

    @Autowired
    private MOUAgreementBudgetTypeService mouAgreementBudgetTypeService;

    @Autowired
    BudgetTypeController budgetTypeController;

    @Autowired
    ReleaseFrequencyController releaseFrequencyController;

    @Autowired
    MOUAgreementService mouAgreementService;

    @Autowired
    BudgetController budgetController;

    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

    @EnableAudit(description = "saveMouAgreementBudgetTypes")
    @PostMapping(value = "/saveMouAgreementBudgetTypes")
    ResponseEntity<APIResponse> saveMouAgreementBudgetTypes(@RequestBody List<MOUAgreementBudgetType> mouAgreementBudgetTypes,
                                                             @RequestHeader(value = "user") String user) {
        List<MOUAgreementBudgetType> mouAgreementBudgetTypesSaved = new ArrayList<>();
        //List<MOUAgreementBudgetType> mouAgreementBudgetTypeResponsesSaved = new ArrayList<>();
        MOUAgreementBudgetType mouAgreementBudgetTypeSaved = null;
       // MOUAgreementBudgetTypeResp mouAgreementBudgetTypeRespSaved = null;
        if(mouAgreementBudgetTypes!=null && mouAgreementBudgetTypes.size()>0){
            mouAgreementBudgetTypeSaved = new MOUAgreementBudgetType();
            //mouAgreementBudgetTypeRespSaved = new MOUAgreementBudgetTypeResp();
            for(MOUAgreementBudgetType mouAgreementBudgetType : mouAgreementBudgetTypes){
                //First step need to check BudgetTypeAvailability available or not and need to save BudgetType
                BudgetType budgetType = mouAgreementBudgetType.getBudgetType();
                BudgetType budgetTypeSaved = (BudgetType) budgetTypeController.getBudgetTypeExists(budgetType, user).getBody().getData();
                if(budgetTypeSaved == null){
                    budgetTypeSaved = (BudgetType) budgetTypeController.saveBudgetType(budgetType, user).getBody().getData();
                }
                mouAgreementBudgetType.setBudgetType(budgetTypeSaved);
                //mouAgreementBudgetTypeRespSaved.setBudgetType(budgetTypeSaved);

                //First step need to check BudgetTypeAvailability available or not and need to save BudgetType
                ReleaseFrequency releaseFrequency = mouAgreementBudgetType.getReleaseFrequency();
                ReleaseFrequency releaseFrequencySaved = (ReleaseFrequency) releaseFrequencyController.getReleaseFrequencyExists(releaseFrequency, user).getBody().getData();
                if(releaseFrequencySaved == null){
                    releaseFrequencySaved = (ReleaseFrequency) releaseFrequencyController.saveReleaseFrequency(releaseFrequency, user).getBody().getData();
                }
                mouAgreementBudgetType.setReleaseFrequency(releaseFrequencySaved);

                mouAgreementBudgetTypeSaved = mouAgreementBudgetTypeService.addMOUAgreementBudgetType(mouAgreementBudgetType);

                List<Budget> budgetListSaved = saveBudgetsToTheMOUAgreementBudgetType(mouAgreementBudgetTypeSaved, user);

                mouAgreementBudgetTypeSaved.setBudgetList(budgetListSaved);
                mouAgreementBudgetTypesSaved.add(mouAgreementBudgetTypeSaved);
            }
        }
        return ResponseBuilder.build(ResponseBuilder.success(mouAgreementBudgetTypesSaved));
    }

    @EnableAudit(description = "getBudgetsForMOUAgreement")
    @PostMapping(value = "/getBudgetsForMOUAgreement")
    ResponseEntity<APIResponse> getBudgetsForMOUAgreementByMouAgreement(@RequestBody MOUAgreement mouAgreement,
                                                            @RequestHeader(value = "user") String user) {
        List<MOUAgreementBudgetType> mouAgreementBudgetTypesSaved = new ArrayList<>();
        mouAgreementBudgetTypesSaved =  mouAgreementBudgetTypeService.getMOUAgreementBudgetTypeByAgreementId(mouAgreement.getAgreementKey());
        if(null != mouAgreementBudgetTypesSaved && mouAgreementBudgetTypesSaved.size()>0){
            for(MOUAgreementBudgetType mouAgreementBudgetType : mouAgreementBudgetTypesSaved){
                List<Budget> budgetList = (List<Budget>) budgetController.getBudgetsForMouAgreementBudgetType(mouAgreementBudgetType, user).getBody().getData();
                mouAgreementBudgetType.setBudgetList(budgetList);
            }
        }
        return ResponseBuilder.build(ResponseBuilder.success(mouAgreementBudgetTypesSaved));
    }

    private List<Budget> saveBudgetsToTheMOUAgreementBudgetType(MOUAgreementBudgetType mouAgreementBudgetTypeSaved, String user) {
        List<Budget> budgetList = getBudgetList(mouAgreementBudgetTypeSaved);
        List<Budget> budgetListSaved = (List<Budget>) budgetController.saveBudgetType(budgetList, user).getBody().getData();
        return budgetListSaved;
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
