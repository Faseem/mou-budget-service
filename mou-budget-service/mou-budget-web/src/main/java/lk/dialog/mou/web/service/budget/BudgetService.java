package lk.dialog.mou.web.service.budget;

import lk.dialog.mou.domain.Budget;

import java.util.List;

/**
 * Created by Aux072 on 19/09/2018.
 */
public interface BudgetService {
    Budget addBudgetType(Budget budget);
    List<Budget> getBudgetsForMouAgreementBudgetTypeId(Long ouAgreementBudgetTypeId);
}
