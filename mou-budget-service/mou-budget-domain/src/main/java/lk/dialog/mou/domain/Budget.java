package lk.dialog.mou.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by Aux072 on 19/09/2018.
 */
@Data
public class Budget {
    private Long budgetId;
    private Long mouAgreementBudgetTypeId;
    //private Long mouCustomerId;
    private Double budgetAmount;
    private Date startDate;
    private String subPeriodCode;
    private String subPeriodCodeDesc;
    private Date endDate;
    private Double usageAmount;
    private Double availableAmount;
    private Boolean active;

}
