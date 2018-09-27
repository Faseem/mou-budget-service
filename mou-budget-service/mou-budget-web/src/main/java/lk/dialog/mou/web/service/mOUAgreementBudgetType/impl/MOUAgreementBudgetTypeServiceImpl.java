package lk.dialog.mou.web.service.mOUAgreementBudgetType.impl;

import lk.dialog.mou.db.repository.MOUAgreementBudgetTypeRepository;
import lk.dialog.mou.domain.MOUAgreementBudgetType;
import lk.dialog.mou.web.service.mOUAgreementBudgetType.MOUAgreementBudgetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<MOUAgreementBudgetType> getMOUAgreementBudgetTypeByAgreementId(Long agreementKey) {
        return mouAgreementBudgetTypeRepository.getMOUAgreementBudgetTypeByAgreementId(agreementKey);
    }
}
