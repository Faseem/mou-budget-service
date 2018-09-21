package lk.dialog.mou.web.controller;

import lk.dialog.mou.aspect.annotations.EnableAudit;
import lk.dialog.mou.domain.*;
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

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aux072 on 14/09/2018.
 */
@RestController
@RequestMapping(value = "/mouAgreement")
public class MOUAgreementController {
    private static final Logger logger = LoggerFactory.getLogger("MOUAgreementController");

    @Autowired
    private MOUAgreementService mouAgreementService;

    @Autowired
    CustomerController customerController;

    @Autowired
    private UserService userService;


    @EnableAudit(description = "MOUAgreementController's addMOUAgreement ")
    @PostMapping(value = "/addMOUAgreement")
    public ResponseEntity<MOUAgreement> addMOUAgreement(@RequestBody MOUAgreement mouAgreement,
                                                        @RequestHeader(value = "user") String user){
        logger.info("Add MOUAgreement");
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
        //MOUAgreement mouAgreementToRet = mouAgreementService.addMOUAgreement(mouAgreement);
        return ResponseEntity.ok(mouAgreementService.addMOUAgreement(mouAgreement));
        //return mouAgreementToRet;
    }

    @EnableAudit(description = "MOUAgreementController's addMOUAgreement ")
    @GetMapping(value = "/getMOUAgreementExists")
    ResponseEntity<APIResponse> getMOUAgreementExists(@RequestBody MOUAgreement mouAgreement, @RequestHeader(value = "user") String user){
        return ResponseBuilder.build(ResponseBuilder.success(mouAgreementService.getMOUAgreementByKeys(mouAgreement)));
    }

    @EnableAudit(description = "MOUAgreementController's getMOUAgreementByKey ")
    @GetMapping(value = "/getMOUAgreementByKey")
    ResponseEntity<APIResponse> getMOUAgreementByKey(@PathParam("mouAgreementId") Long mouAgreementId, @RequestHeader(value = "user") String user){
        return ResponseBuilder.build(ResponseBuilder.success(mouAgreementService.getMOUAgreementByKey(mouAgreementId)));
    }


}
