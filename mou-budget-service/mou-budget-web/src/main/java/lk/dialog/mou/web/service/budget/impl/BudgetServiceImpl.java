package lk.dialog.mou.web.service.budget.impl;

import lk.dialog.mou.db.repository.BudgetRepository;
import lk.dialog.mou.domain.Budget;
import lk.dialog.mou.web.service.budget.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aux072 on 19/09/2018.
 */
@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public Budget addBudgetType(Budget budget) {
        return budgetRepository.addBudgetType(budget);
    }
}
