package lk.dialog.mou.db.repository;

import lk.dialog.mou.domain.Budget;

import java.util.List;

/**
 * Created by Aux072 on 19/09/2018.
 */
public interface BudgetRepository {
    Budget addBudgetType(Budget budget);
    List<Budget> getBudgetsForMouAgreementBudgetTypeId(Long mouAgreementBudgetTypeId);
}
