package lk.dialog.mou.web.service.mou.impl;

import lk.dialog.mou.db.repository.MOUAgreementRepository;
import lk.dialog.mou.domain.MOUAgreement;
import lk.dialog.mou.web.service.mou.MOUAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aux072 on 14/09/2018.
 */
@Service
public class MOUAgreementServiceImpl implements MOUAgreementService {

    @Autowired
    MOUAgreementRepository mouAgreementRepository;

    @Override
    public MOUAgreement addMOUAgreement(MOUAgreement mouAgreement) {
        return mouAgreementRepository.addMOUAgreement(mouAgreement);
    }

    @Override
    public MOUAgreement getMOUAgreement(MOUAgreement mouAgreement) {
        return mouAgreementRepository.getMOUAgreementByKeys(mouAgreement);
    }

    @Override
    public MOUAgreement getMOUAgreementByKeys(MOUAgreement mouAgreement) {
        return mouAgreementRepository.getMOUAgreementByKeys(mouAgreement);
    }

    @Override
    public MOUAgreement getMOUAgreementByKey(Long mouAgreementId) {
        return mouAgreementRepository.getMOUAgreementByKey(mouAgreementId);
    }
}
