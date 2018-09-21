package lk.dialog.mou.db.repository;

import lk.dialog.mou.domain.MOUAgreement;

import java.util.List;
import java.util.Optional;

/**
 * Created by Aux072 on 13/09/2018.
 */
public interface MOUAgreementRepository {
    List<MOUAgreement> getMOUAgreement(String identificationType, String identificationNumber);
    Optional<MOUAgreement> getMOUAgreement(MOUAgreement mouAgreement);
    MOUAgreement addMOUAgreement(MOUAgreement mouAgreement);
    MOUAgreement getMOUAgreementByKeys(MOUAgreement mouAgreement);
    MOUAgreement getMOUAgreementByKey(Long mouAgreement);
}
