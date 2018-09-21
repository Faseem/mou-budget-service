package lk.dialog.mou.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by Aux072 on 18/09/2018.
 */
@Data
public class Customer {
    private Long customerId;
    private Long createdSysUser;
    private String idType;
    private String idNumber;
    /*private Date endDate;
    private Date startDate;*/
    private String cxName;
    private String createdUser;
    private Date createdDate;
    private String adLine3;
    private String adLine2;
    private String adLine1;
    private String sector;
    private String segment;
}
