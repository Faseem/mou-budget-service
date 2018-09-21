package lk.dialog.mou.web.service.mOUAgreementBudgetType;

import lk.dialog.mou.domain.MOUAgreementBudgetType;

import java.util.List;

/**
 * Created by Aux072 on 18/09/2018.
 */
public interface MOUAgreementBudgetTypeService {
    MOUAgreementBudgetType addMOUAgreementBudgetType(MOUAgreementBudgetType mouAgreementBudgetType);

    List<MOUAgreementBudgetType> getMOUAgreementBudgetTypeByAgreementId(Long agreementKey);
}
