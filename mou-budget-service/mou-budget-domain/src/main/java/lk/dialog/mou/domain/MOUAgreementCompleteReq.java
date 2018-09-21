package lk.dialog.mou.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Aux072 on 18/09/2018.
 */
@Data
public class MOUAgreementCompleteReq {
    private MOUAgreement mouAgreement;
    private Customer customer;
    private List<MOUAgreementBudgetType> mouAgreementBudgetTypes;
}
