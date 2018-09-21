package lk.dialog.mou.db.repository.impl;

import lk.dialog.mou.db.repository.CustomerMOUAgreementRepository;
import lk.dialog.mou.db.repository.impl.mappers.CustomerMOUAgreementMapper;
import lk.dialog.mou.domain.CustomerMOUAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aux072 on 18/09/2018.
 */
@Repository
public class CustomerMOUAgreementRepositoryImpl implements CustomerMOUAgreementRepository{

    @Autowired
    @Qualifier("mou-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("mou-jdbc")
    private JdbcTemplate jdbcTemplate;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public CustomerMOUAgreement addCustomerMOUAgreement(CustomerMOUAgreement customerMOUAgreement) {
        Map<String,Object> parameters = new HashMap<>();
        //parameters.put("CUSTOMER_ID", customer.getCustomerId());
        parameters.put("MOU_CUSTOMER_ID", customerMOUAgreement.getCustomerId());
        parameters.put("MOU_AGREEMENT_KEY", customerMOUAgreement.getMouAgreementId());

        namedParameterJdbcTemplate.update("INSERT INTO MOU.CUSTOMER_MOU_AGREEMENT (" +
                        "MOU_CUSTOMER_ID," +
                        "MOU_AGREEMENT_KEY)" +
                        " VALUES(:MOU_CUSTOMER_ID,:MOU_AGREEMENT_KEY)",
                parameters);

        return getCustomerMOUAgreementByKeys(customerMOUAgreement);
    }

    public CustomerMOUAgreement getCustomerMOUAgreementByKeys(CustomerMOUAgreement customerMOUAgreement){
        String query = "SELECT * FROM MOU.CUSTOMER_MOU_AGREEMENT WHERE MOU_CUSTOMER_ID=:MOU_CUSTOMER_ID AND MOU_AGREEMENT_KEY=:MOU_AGREEMENT_KEY";
        Map<String, Object> params = new HashMap<>();
        params.put("MOU_CUSTOMER_ID", customerMOUAgreement.getCustomerId());
        params.put("MOU_AGREEMENT_KEY", customerMOUAgreement.getMouAgreementId());

        namedParameterJdbcTemplate.query(query, params,new CustomerMOUAgreementMapper());
        List<CustomerMOUAgreement> customerMOUAgreements = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            CustomerMOUAgreement customerReturned = new CustomerMOUAgreement();
            customerReturned.setCustomerId(rs.getLong("MOU_CUSTOMER_ID"));
            customerReturned.setMouAgreementId(rs.getLong("MOU_AGREEMENT_KEY"));
            customerReturned.setCustomerMOUAgreementId(rs.getLong("CUSTOMER_MOU_AGREEMENT_ID"));
            return customerReturned;
        });
        return customerMOUAgreements.size() > 0 ? customerMOUAgreements.get(0) : null;
    }
}
