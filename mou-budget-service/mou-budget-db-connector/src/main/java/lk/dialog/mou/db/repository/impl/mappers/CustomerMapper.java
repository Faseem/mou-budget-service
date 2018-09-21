package lk.dialog.mou.db.repository.impl.mappers;

import lk.dialog.mou.domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aux072 on 18/09/2018.
 */
public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
        customer.setCreatedSysUser(resultSet.getLong("CREATED_SYS_USER"));
        customer.setIdType(resultSet.getString("ID_TYPE"));
        customer.setIdNumber(resultSet.getString("ID_NUMBER"));
        customer.setSegment(resultSet.getString("SEGMENT"));
        customer.setSector(resultSet.getString("SECTOR"));
        customer.setCxName(resultSet.getString("CX_NAME"));
        customer.setCreatedUser(resultSet.getString("CREATED_USER"));
        customer.setCreatedDate(resultSet.getDate("CREATED_DATE"));
        customer.setAdLine3(resultSet.getString("AD_LINE3"));
        customer.setAdLine2(resultSet.getString("AD_LINE2"));
        customer.setAdLine1(resultSet.getString("AD_LINE1"));
        return customer;
    }
}
