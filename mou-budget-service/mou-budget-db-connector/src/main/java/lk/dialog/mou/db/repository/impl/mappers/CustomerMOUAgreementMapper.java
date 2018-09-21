package lk.dialog.mou.db.repository.impl.mappers;

import lk.dialog.mou.domain.CustomerMOUAgreement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aux072 on 18/09/2018.
 */
public class CustomerMOUAgreementMapper implements RowMapper<CustomerMOUAgreement> {
    @Override
    public CustomerMOUAgreement mapRow(ResultSet resultSet, int i) throws SQLException {
        CustomerMOUAgreement customerMOUAgreement = new CustomerMOUAgreement();
        customerMOUAgreement.setCustomerId(resultSet.getLong("MOU_CUSTOMER_ID"));
        customerMOUAgreement.setCustomerMOUAgreementId(resultSet.getLong("CUSTOMER_MOU_AGREEMENT_ID"));
        customerMOUAgreement.setMouAgreementId(resultSet.getLong("MOU_AGREEMENT_KEY"));
        return customerMOUAgreement;
    }
}