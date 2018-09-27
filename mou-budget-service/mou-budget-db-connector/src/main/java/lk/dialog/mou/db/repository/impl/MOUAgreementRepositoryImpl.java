package lk.dialog.mou.db.repository.impl;

import lk.dialog.mou.db.repository.MOUAgreementRepository;
import lk.dialog.mou.db.repository.impl.mappers.MOUAgreementMapper;
import lk.dialog.mou.domain.MOUAgreement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Aux072 on 13/09/2018.
 */
@Repository
public class MOUAgreementRepositoryImpl implements MOUAgreementRepository {

    private static final Logger logger = LoggerFactory.getLogger(MOUAgreementRepositoryImpl.class);

    @Autowired
    @Qualifier("mou-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("mou-jdbc")
    private JdbcTemplate jdbcTemplate;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");

    private static final String OM_SCHEMA = "MOU";

    @Override
    public List<MOUAgreement> getMOUAgreement(String mouAgreementId, String identificationNumber) {
        String query = "SELECT AGREEMENT_KEY,\n" +
                "       CREATED_SYS_USER,\n" +
                "       AGREEMENT_ID,\n" +
                "       ID_TYPE,\n" +
                "       CREATED_DATE,\n" +
                "       CREATED_USER,\n" +
                "       END_DATE,\n" +
                "       START_DATE,\n" +
                "       ID_NUMBER,\n" +
                "       DESCRIPTION,\n" +
                "       ACTIVE,\n" +
                "FROM MOU_AGREEMENT\n" +
                "WHERE MOU_AGREEMENT_ID = :mouAgreementId";

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("mouAgreementBudgetTypeId", mouAgreementId);
        return namedParameterJdbcTemplate.query(query, parameters, new MOUAgreementMapper());
    }

    @Override
    public Optional<MOUAgreement> getMOUAgreement(MOUAgreement mouAgreement) {
        String query = "SELECT * FROM MOU.MOU_AGREEMENT WHERE CREATED_SYS_USER=:sysUserId" +
                " AND AGREEMENT_ID=:agreementId" +
               // " AND ID_TYPE=:idType" +
                " AND CREATED_DATE=:createdDate" +
                " AND CREATED_USER=:createdUser" +
                " AND END_DATE=:endDate" +
                " AND START_DATE=:startDate" +
               // " AND ID_NUMBER=:idNumber" +
                " AND DESCRIPTION=:description" +
                " AND ACTIVE=:active" +
                " AND AMOUNT=:amount";
        Map<String, Object> params = new HashMap<>();
        params.put("sysUserId", mouAgreement.getCreatedSysUser());
        params.put("agreementId", mouAgreement.getAgreementID());
        //params.put("idType", mouAgreement.getIdType());
        params.put("createdDate", mouAgreement.getCreatedDate());
        params.put("createdUser", mouAgreement.getCreatedUser());
        params.put("endDate", mouAgreement.getEndDate());
        params.put("startDate", mouAgreement.getStartDate());
        //params.put("idNumber", mouAgreement.getIdNumber());
        params.put("description", mouAgreement.getDescription());
        params.put("active", mouAgreement.getActive());
        params.put("amount", mouAgreement.getAmount());

        namedParameterJdbcTemplate.query(query, params,new MOUAgreementMapper());
        List<MOUAgreement> mouAgreementList = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            MOUAgreement mouAgreementReturned = new MOUAgreement();
            mouAgreementReturned.setCreatedSysUser(rs.getLong("CREATED_SYS_USER"));
            mouAgreementReturned.setAgreementID(rs.getString("AGREEMENT_ID"));
            mouAgreementReturned.setCreatedDate(rs.getDate("CREATED_DATE"));
            mouAgreementReturned.setCreatedUser(rs.getString("CREATED_USER"));
            mouAgreementReturned.setEndDate(rs.getDate("END_DATE"));
            mouAgreementReturned.setStartDate(rs.getDate("START_DATE"));
            //mouAgreementReturned.setIdNumber(rs.getString("ID_NUMBER"));
            mouAgreementReturned.setDescription(rs.getString("DESCRIPTION"));
            mouAgreementReturned.setActive(rs.getBoolean("ACTIVE"));
            mouAgreementReturned.setAmount(rs.getDouble("AMOUNT"));
            mouAgreementReturned.setAgreementKey(rs.getLong("AGREEMENT_KEY"));
            //mouAgreementReturned.setIdType(rs.getString("ID_TYPE"));
            return mouAgreementReturned;
        });
        return mouAgreementList.size() > 0 ? Optional.of(mouAgreementList.get(0)) : Optional.empty();
    }


    @Override
    public MOUAgreement addMOUAgreement(MOUAgreement mouAgreement) {

        Map<String,Object> parameters = new HashMap<>();
        parameters.put("CREATED_SYS_USER", mouAgreement.getCreatedSysUser());
        parameters.put("AGREEMENT_ID", mouAgreement.getAgreementID());
        //parameters.put("ID_TYPE", mouAgreement.getIdType());
        parameters.put("CREATED_DATE", mouAgreement.getCreatedDate());
        parameters.put("CREATED_USER", mouAgreement.getCreatedUser());
        parameters.put("END_DATE", mouAgreement.getEndDate());
        parameters.put("START_DATE", mouAgreement.getStartDate());
        //parameters.put("ID_NUMBER", mouAgreement.getIdNumber());
        parameters.put("DESCRIPTION", mouAgreement.getDescription());
        parameters.put("ACTIVE", mouAgreement.getActive());
        parameters.put("AMOUNT", mouAgreement.getAmount());

        namedParameterJdbcTemplate.update("INSERT INTO MOU.MOU_AGREEMENT (" +
                        "CREATED_SYS_USER," +
                        "AGREEMENT_ID," +
                        //"ID_TYPE," +
                        "CREATED_DATE," +
                        "CREATED_USER," +
                        "END_DATE," +
                        "START_DATE," +
                        //"ID_NUMBER," +
                        "DESCRIPTION," +
                        "ACTIVE," +
                        "AMOUNT)" +
                        " VALUES(:CREATED_SYS_USER,:AGREEMENT_ID, :CREATED_DATE, :CREATED_USER, " +
                        ":END_DATE, :START_DATE, :DESCRIPTION, :ACTIVE, :AMOUNT)",
                parameters);

        return getMOUAgreement(mouAgreement).get();
        /*Optional<MOUAgreement> mouAgreementList = getMOUAgreement(mouAgreement);
        return mouAgreementList.get();*/
    }

    @Override
    public MOUAgreement getMOUAgreementByKeys(MOUAgreement mouAgreement) {
        String query = "SELECT * FROM MOU.MOU_AGREEMENT WHERE AGREEMENT_ID=:agreementId";
        Map<String, Object> params = new HashMap<>();
        params.put("agreementId", mouAgreement.getAgreementID());
        //params.put("idType", mouAgreement.getIdType());
        //params.put("idNumber", mouAgreement.getIdNumber());

        namedParameterJdbcTemplate.query(query, params,new MOUAgreementMapper());
        List<MOUAgreement> mouAgreementList = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            MOUAgreement mouAgreementReturned = new MOUAgreement();
            mouAgreementReturned.setCreatedSysUser(rs.getLong("CREATED_SYS_USER"));
            mouAgreementReturned.setAgreementID(rs.getString("AGREEMENT_ID"));
            mouAgreementReturned.setCreatedDate(rs.getDate("CREATED_DATE"));
            mouAgreementReturned.setCreatedUser(rs.getString("CREATED_USER"));
            mouAgreementReturned.setEndDate(rs.getDate("END_DATE"));
            mouAgreementReturned.setStartDate(rs.getDate("START_DATE"));
            //mouAgreementReturned.setIdNumber(rs.getString("ID_NUMBER"));
            mouAgreementReturned.setDescription(rs.getString("DESCRIPTION"));
            mouAgreementReturned.setActive(rs.getBoolean("ACTIVE"));
            mouAgreementReturned.setAmount(rs.getDouble("AMOUNT"));
            mouAgreementReturned.setAgreementKey(rs.getLong("AGREEMENT_KEY"));
            //mouAgreementReturned.setIdType(rs.getString("ID_TYPE"));
            return mouAgreementReturned;
        });
        return mouAgreementList.size() > 0 ? mouAgreementList.get(0) : null;
    }

    @Override
    public MOUAgreement getMOUAgreementByKey(Long mouAgreementId) {
        String query = "SELECT * FROM MOU.MOU_AGREEMENT WHERE AGREEMENT_KEY=:AGREEMENT_KEY";
        Map<String, Object> params = new HashMap<>();
        params.put("AGREEMENT_KEY", mouAgreementId);

        namedParameterJdbcTemplate.query(query, params,new MOUAgreementMapper());
        List<MOUAgreement> mouAgreementList = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            MOUAgreement mouAgreementReturned = new MOUAgreement();
            mouAgreementReturned.setCreatedSysUser(rs.getLong("CREATED_SYS_USER"));
            mouAgreementReturned.setAgreementID(rs.getString("AGREEMENT_ID"));
            mouAgreementReturned.setCreatedDate(rs.getDate("CREATED_DATE"));
            mouAgreementReturned.setCreatedUser(rs.getString("CREATED_USER"));
            mouAgreementReturned.setEndDate(rs.getDate("END_DATE"));
            mouAgreementReturned.setStartDate(rs.getDate("START_DATE"));
            //mouAgreementReturned.setIdNumber(rs.getString("ID_NUMBER"));
            mouAgreementReturned.setDescription(rs.getString("DESCRIPTION"));
            mouAgreementReturned.setActive(rs.getBoolean("ACTIVE"));
            mouAgreementReturned.setAmount(rs.getDouble("AMOUNT"));
            mouAgreementReturned.setAgreementKey(rs.getLong("AGREEMENT_KEY"));
            //mouAgreementReturned.setIdType(rs.getString("ID_TYPE"));
            return mouAgreementReturned;
        });
        return mouAgreementList.size() > 0 ? mouAgreementList.get(0) : null;
    }
}
