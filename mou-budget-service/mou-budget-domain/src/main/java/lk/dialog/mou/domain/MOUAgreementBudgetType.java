package lk.dialog.mou.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Aux072 on 18/09/2018.
 */
@Data
public class MOUAgreementBudgetType {
    private Long mouAgreementBudgetTypeId;
    private BudgetType budgetType;
    private ReleaseFrequency releaseFrequency;
    private Double amount;
    private Long mouAgreementId;
    private Long customerId;
    private Long customerMouAgreementId;
    private List<Budget> budgetList;
}
