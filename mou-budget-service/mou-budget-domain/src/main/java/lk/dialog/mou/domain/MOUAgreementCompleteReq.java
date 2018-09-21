package lk.dialog.mou.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Aux072 on 18/09/2018.
 */
@Data
public class MOUAgreementCompleteReq {
    private List<MOUAgreement> mouAgreements;
    /*private List<Customer> customers;
    private List<MOUAgreementBudgetType> mouAgreementBudgetTypes;*/
}
