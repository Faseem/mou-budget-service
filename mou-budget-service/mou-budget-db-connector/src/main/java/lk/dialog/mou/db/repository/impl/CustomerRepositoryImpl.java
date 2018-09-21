package lk.dialog.mou.db.repository.impl;

import lk.dialog.mou.db.repository.CustomerRepository;
import lk.dialog.mou.db.repository.impl.mappers.CustomerMapper;
import lk.dialog.mou.domain.Customer;
import lk.dialog.mou.domain.MOUAgreement;
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
public class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    @Qualifier("mou-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("mou-jdbc")
    private JdbcTemplate jdbcTemplate;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Customer getCustomerByKeys(Customer customer) {
        String query = "SELECT * FROM MOU.MOU_CUSTOMER WHERE ID_TYPE=:ID_TYPE AND ID_NUMBER=:ID_NUMBER";
        Map<String, Object> params = new HashMap<>();
        params.put("ID_TYPE", customer.getIdType());
        params.put("ID_NUMBER", customer.getIdNumber());

        namedParameterJdbcTemplate.query(query, params,new CustomerMapper());
        List<Customer> customerList = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            Customer customerReturned = new Customer();
            customerReturned.setCustomerId(rs.getLong("CUSTOMER_ID"));
            customerReturned.setCreatedSysUser(rs.getLong("CREATED_SYS_USER"));
            customerReturned.setCreatedDate(rs.getDate("CREATED_DATE"));
            customerReturned.setCreatedUser(rs.getString("CREATED_USER"));
            //customerReturned.setEndDate(rs.getDate("END_DATE"));
            //customerReturned.setStartDate(rs.getDate("START_DATE"));
            customerReturned.setSector(rs.getString("SECTOR"));
            customerReturned.setSegment(rs.getString("SEGMENT"));
            customerReturned.setIdNumber(rs.getString("ID_NUMBER"));
            customerReturned.setIdType(rs.getString("ID_TYPE"));
            customerReturned.setCxName(rs.getString("CX_NAME"));
            customerReturned.setAdLine1(rs.getString("AD_LINE1"));
            customerReturned.setAdLine2(rs.getString("AD_LINE2"));
            customerReturned.setAdLine3(rs.getString("AD_LINE3"));
            return customerReturned;
        });
        return customerList.size() > 0 ? customerList.get(0) : null;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        Map<String,Object> parameters = new HashMap<>();
        //parameters.put("CUSTOMER_ID", customer.getCustomerId());
        parameters.put("CREATED_SYS_USER", customer.getCreatedSysUser());
        parameters.put("CREATED_DATE", customer.getCreatedDate());
        parameters.put("CREATED_USER", customer.getCreatedUser());
        //parameters.put("END_DATE", customer.getEndDate());
        //parameters.put("START_DATE", customer.getStartDate());
        parameters.put("SEGMENT", customer.getSegment());
        parameters.put("SECTOR", customer.getSector());
        parameters.put("ID_NUMBER", customer.getIdNumber());
        parameters.put("ID_TYPE", customer.getIdType());
        parameters.put("CX_NAME", customer.getCxName());
        parameters.put("AD_LINE1", customer.getAdLine1());
        parameters.put("AD_LINE2", customer.getAdLine2());
        parameters.put("AD_LINE3", customer.getAdLine3());

        namedParameterJdbcTemplate.update("INSERT INTO MOU.MOU_CUSTOMER (" +
                        "CREATED_SYS_USER," +
                        "CREATED_DATE," +
                        "CREATED_USER," +
                        "SECTOR," +
                        "SEGMENT," +
                        "ID_NUMBER," +
                        "ID_TYPE," +
                        "CX_NAME," +
                        "AD_LINE1," +
                        "AD_LINE2," +
                        "AD_LINE3)" +
                        " VALUES(:CREATED_SYS_USER,:CREATED_DATE, :CREATED_USER, :SECTOR, :SEGMENT, " +
                        ":ID_NUMBER, :ID_TYPE, :CX_NAME, :AD_LINE1, :AD_LINE2, :AD_LINE3)",
                parameters);

        return getCustomerByKeys(customer);
    }
}
