package lk.dialog.mou.db.repository.impl.mappers;

import lk.dialog.mou.domain.MOUAgreement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aux072 on 13/09/2018.
 */
public class MOUAgreementMapper implements RowMapper<MOUAgreement> {
    @Override
    public MOUAgreement mapRow(ResultSet resultSet, int i) throws SQLException {
        MOUAgreement mouAgreement = new MOUAgreement();
        mouAgreement.setActive(resultSet.getBoolean("ACTIVE"));
        mouAgreement.setAgreementID(resultSet.getString("AGREEMENT_ID"));
        mouAgreement.setAgreementKey(resultSet.getLong("AGREEMENT_KEY"));
        //mouAgreement.setIdType(resultSet.getString("ID_TYPE"));
        mouAgreement.setCreatedSysUser(resultSet.getLong("CREATED_SYS_USER"));
        mouAgreement.setCreatedUser(resultSet.getString("CREATED_USER"));
        mouAgreement.setDescription(resultSet.getString("DESCRIPTION"));
        mouAgreement.setEndDate(resultSet.getDate("END_DATE"));
        mouAgreement.setCreatedDate(resultSet.getDate("CREATED_DATE"));
        mouAgreement.setStartDate(resultSet.getDate("START_DATE"));
        //mouAgreement.setIdNumber(resultSet.getString("ID_NUMBER"));
        //mouAgreement.setIdNumber(resultSet.getString("ID_NUMBER"));
        mouAgreement.setAmount(resultSet.getDouble("AMOUNT"));
        return mouAgreement;
    }
}
