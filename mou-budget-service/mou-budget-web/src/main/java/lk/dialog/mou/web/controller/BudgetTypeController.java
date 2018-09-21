package lk.dialog.mou.web.controller;

import lk.dialog.mou.aspect.annotations.EnableAudit;
import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.web.service.budgetType.BudgetTypeService;
import lk.dialog.mou.web.service.releaseFrequency.ReleaseFrequencyService;
import lk.dialog.mou.web.util.APIResponse;
import lk.dialog.mou.web.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Aux072 on 19/09/2018.
 */
@RestController
@RequestMapping(value = "/budgetType")
public class BudgetTypeController {
    @Autowired
    private BudgetTypeService budgetTypeService;

    @EnableAudit(description = "Saving BudgetType")
    @PostMapping(value = "/saveBudgetType")
    ResponseEntity<APIResponse> saveBudgetType(@RequestBody BudgetType budgetType, @RequestHeader(value = "user") String user) {
        return ResponseBuilder.build(ResponseBuilder.success(budgetTypeService.addBudgetType(budgetType)));
    }

    @EnableAudit(description = "getBudgetTypeExists")
    @GetMapping(value = "/saveBudgetType")
    ResponseEntity<APIResponse> getBudgetTypeExists(@RequestBody BudgetType budgetType,  @RequestHeader(value = "user") String user) {
        return ResponseBuilder.build(ResponseBuilder.success(budgetTypeService.getBudgetTypeByKeys(budgetType)));
    }
}
