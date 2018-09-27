package lk.dialog.mou.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aux072 on 21/09/2018.
 */
public class MOUAgreementCompleteResp {
    List<MOUAgreement> mouAgreementsSaved = new ArrayList<>();
    List<Customer> customersSaved = new ArrayList<>();
    List<CustomerMOUAgreement> customerMOUAgreementsSaved = new ArrayList<>();
    List<List<MOUAgreementBudgetType>> mouAgreementBudgetTypeForAgreements = new ArrayList<>();
}
