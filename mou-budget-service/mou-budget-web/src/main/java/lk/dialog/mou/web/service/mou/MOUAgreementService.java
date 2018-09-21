package lk.dialog.mou.web.service.mou;

import lk.dialog.mou.domain.MOUAgreement;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by Aux072 on 14/09/2018.
 */
public interface MOUAgreementService {
    public MOUAgreement addMOUAgreement(MOUAgreement mouAgreement);
    public MOUAgreement getMOUAgreement(MOUAgreement mouAgreement);
    public MOUAgreement getMOUAgreementByKeys(MOUAgreement mouAgreement);
    public MOUAgreement getMOUAgreementByKey(Long mouAgreementId);
}
