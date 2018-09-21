package lk.dialog.mou.web.service.budgetType;

import lk.dialog.mou.domain.BudgetType;

/**
 * Created by Aux072 on 18/09/2018.
 */
public interface BudgetTypeService {
    BudgetType getBudgetTypeByKeys(BudgetType budgetType);
    BudgetType addBudgetType(BudgetType budgetType);
}
