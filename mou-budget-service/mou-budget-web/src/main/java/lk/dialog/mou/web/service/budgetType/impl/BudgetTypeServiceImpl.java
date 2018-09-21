package lk.dialog.mou.web.service.budgetType.impl;

import lk.dialog.mou.db.repository.BudgetTypeRepository;
import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.web.service.budgetType.BudgetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aux072 on 18/09/2018.
 */
@Service
public class BudgetTypeServiceImpl implements BudgetTypeService{

    @Autowired
    BudgetTypeRepository budgetTypeRepository;

    @Override
    public BudgetType getBudgetTypeByKeys(BudgetType budgetType) {
        return budgetTypeRepository.getBudgetTypeByKeys(budgetType);
    }

    @Override
    public BudgetType addBudgetType(BudgetType budgetType) {
        return budgetTypeRepository.addBudgetType(budgetType);
    }
}
