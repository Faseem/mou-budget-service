package lk.dialog.mou.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Aux072 on 13/09/2018.
 */
@Data
public class MOUAgreement {

    private Long agreementKey;
    private Long createdSysUser;
    private String agreementID;
    private String idType;
    private String createdUser;
    private Date endDate;
    private Date startDate;
    private Date createdDate;
    private String idNumber;
    private String description;
    private Boolean active;
    private Double amount;
    private List<MOUAgreementBudgetType> mouAgreementBudgetTypes;
    private List<Customer> customers;
    //private List<CustomerMOUAgreement> customerMOUAgreements;

}
