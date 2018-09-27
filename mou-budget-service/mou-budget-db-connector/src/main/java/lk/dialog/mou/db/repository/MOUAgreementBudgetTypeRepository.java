package lk.dialog.mou.db.repository;

import lk.dialog.mou.domain.MOUAgreementBudgetType;

import java.util.List;

/**
 * Created by Aux072 on 18/09/2018.
 */
public interface MOUAgreementBudgetTypeRepository {
    MOUAgreementBudgetType addMOUAgreementBudgetType(MOUAgreementBudgetType mouAgreementBudgetType);

    List<MOUAgreementBudgetType> getMOUAgreementBudgetTypeByAgreementId(Long agreementKey);
}
