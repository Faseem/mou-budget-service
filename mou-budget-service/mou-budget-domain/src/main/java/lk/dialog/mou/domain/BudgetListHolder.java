package lk.dialog.mou.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Aux072 on 19/09/2018.
 */
@Data
public class BudgetListHolder {
    private List<Budget> budgetList;
    private Long mouAgreementBudgetTypeId;
    private Double TotalBudget;
    private Date startDate;
    private Date endDate;
    private Customer customer;
    private String frequency;
}
