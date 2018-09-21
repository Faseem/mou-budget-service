package lk.dialog.mou.db.repository;

import lk.dialog.mou.domain.BudgetType;

/**
 * Created by Aux072 on 18/09/2018.
 */
public interface BudgetTypeRepository {
    BudgetType getBudgetTypeById(Long budgetTypeId);
    BudgetType getBudgetTypeByKeys(BudgetType budgetType);
    BudgetType addBudgetType(BudgetType budgetType);
}
