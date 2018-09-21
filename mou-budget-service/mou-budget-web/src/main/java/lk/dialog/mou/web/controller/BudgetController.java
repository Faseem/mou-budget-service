package lk.dialog.mou.web.controller;

import lk.dialog.mou.aspect.annotations.EnableAudit;
import lk.dialog.mou.domain.Budget;
import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.web.service.budget.BudgetService;
import lk.dialog.mou.web.util.APIResponse;
import lk.dialog.mou.web.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aux072 on 19/09/2018.
 */
@RestController
@RequestMapping(value = "/budget")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @EnableAudit(description = "Saving Budgets")
    @PostMapping(value = "/saveBudgets")
    ResponseEntity<APIResponse> saveBudgetType(@RequestBody List<Budget> budgets, @RequestHeader(value = "user") String user) {
        List<Budget> budgetsSaved = new ArrayList<>();
        for(Budget budget : budgets){
            Budget budgetSaved = budgetService.addBudgetType(budget);
            budgetsSaved.add(budgetSaved);
        }
        return ResponseBuilder.build(ResponseBuilder.success(budgetsSaved));
    }

}
