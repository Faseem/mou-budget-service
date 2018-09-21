package lk.dialog.mou.web.service.mOUAgreementBudgetType.impl;

import lk.dialog.mou.db.repository.MOUAgreementBudgetTypeRepository;
import lk.dialog.mou.domain.MOUAgreementBudgetType;
import lk.dialog.mou.web.service.mOUAgreementBudgetType.MOUAgreementBudgetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aux072 on 18/09/2018.
 */
@Service
public class MOUAgreementBudgetTypeServiceImpl implements MOUAgreementBudgetTypeService {

    @Autowired
    MOUAgreementBudgetTypeRepository mouAgreementBudgetTypeRepository;

    @Override
    public MOUAgreementBudgetType addMOUAgreementBudgetType(MOUAgreementBudgetType mouAgreementBudgetType) {
        return mouAgreementBudgetTypeRepository.addMOUAgreementBudgetType(mouAgreementBudgetType);
    }
}
