package lk.dialog.mou.db.repository.impl;

import lk.dialog.mou.db.repository.BudgetTypeRepository;
import lk.dialog.mou.db.repository.MOUAgreementBudgetTypeRepository;
import lk.dialog.mou.db.repository.ReleaseFrequencyRepository;
import lk.dialog.mou.db.repository.impl.mappers.MOUAgreementBudgetTypeMapper;
import lk.dialog.mou.domain.ReleaseFrequency;
import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.domain.MOUAgreementBudgetType;
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
public class MOUAgreementBudgetTypeRepositoryImpl implements MOUAgreementBudgetTypeRepository {
    @Autowired
    @Qualifier("mou-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("mou-jdbc")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BudgetTypeRepository budgetTypeRepository;

    @Autowired
    private ReleaseFrequencyRepository releaseFrequencyRepository;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public MOUAgreementBudgetType addMOUAgreementBudgetType(MOUAgreementBudgetType mouAgreementBudgetType) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("MOU_AGREEMENT_ID", mouAgreementBudgetType.getMouAgreementId());
        parameters.put("AMOUNT", mouAgreementBudgetType.getAmount());
        parameters.put("BUDGET_TYPE_ID", mouAgreementBudgetType.getBudgetType().getBudgetTypeId());
        parameters.put("RELEASE_FREQUENCY_ID", mouAgreementBudgetType.getReleaseFrequency().getFrequencyTypeId());
        //parameters.put("CUSTOMER_AGREEMENT_ID", mouAgreementBudgetType.getCustomerMouAgreementId());
        //parameters.put("CUSTOMER_ID", mouAgreementBudgetType.getCustomerId());
        //parameters.put("RELEASE_FREQUENCY_ID", mouAgreementBudgetType.getReleaseFrequency().getFrequencyTypeId());

        namedParameterJdbcTemplate.update("INSERT INTO MOU.MOU_AGREEMENT_BUDGET_TYPE (" +
                        "MOU_AGREEMENT_ID," +
                        //"CUSTOMER_AGREEMENT_ID," +
                        //"CUSTOMER_ID," +
                        "AMOUNT," +
                        "BUDGET_TYPE_ID," +
                        "RELEASE_FREQUENCY_ID)" +
                        " VALUES(:MOU_AGREEMENT_ID, :AMOUNT, :BUDGET_TYPE_ID, :RELEASE_FREQUENCY_ID)",
                parameters);

        return getMOUAgreementBudgetTypeByKeys(mouAgreementBudgetType);
    }

    @Override
    public List<MOUAgreementBudgetType> getMOUAgreementBudgetTypeByAgreementId(Long agreementKey) {
        String query = "SELECT * FROM MOU.MOU_AGREEMENT_BUDGET_TYPE WHERE MOU_AGREEMENT_ID=:MOU_AGREEMENT_ID";
        Map<String, Object> params = new HashMap<>();
        params.put("MOU_AGREEMENT_ID", agreementKey);
        //params.put("CUSTOMER_ID", mouAgreementBudgetType.getCustomerId());
        //params.put("CUSTOMER_AGREEMENT_ID", mouAgreementBudgetType.getCustomerMouAgreementId());
        //params.put("BUDGET_TYPE_ID", mouAgreementBudgetType.getBudgetType().getBudgetTypeId());
        //params.put("AMOUNT", mouAgreementBudgetType.getAmount());
        //params.put("RELEASE_FREQUENCY_ID", mouAgreementBudgetType.getReleaseFrequency().getFrequencyTypeId());

        namedParameterJdbcTemplate.query(query, params,new MOUAgreementBudgetTypeMapper());
        List<MOUAgreementBudgetType> mouAgreementBudgetTypes = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            MOUAgreementBudgetType mouAgreementBudgetTypeReturned = new MOUAgreementBudgetType();
            mouAgreementBudgetTypeReturned.setMouAgreementBudgetTypeId(rs.getLong("MOU_AGREEMENT_BUDGET_TYPE_ID"));
            mouAgreementBudgetTypeReturned.setMouAgreementId(rs.getLong("MOU_AGREEMENT_ID"));
            //mouAgreementBudgetTypeReturned.setCustomerId(rs.getLong("CUSTOMER_ID"));
            //mouAgreementBudgetTypeReturned.setCustomerMouAgreementId(rs.getLong("CUSTOMER_AGREEMENT_ID"));
            //BudgetType budgetType = budgetTypeRepository.getBudgetTypeById(rs.getLong("BUDGET_TYPE_ID"));
            mouAgreementBudgetTypeReturned.setBudgetType(budgetTypeRepository.getBudgetTypeById(rs.getLong("BUDGET_TYPE_ID")));
            ReleaseFrequency releaseFrequency = releaseFrequencyRepository.getReleaseFrequencyById(rs.getLong("RELEASE_FREQUENCY_ID"));
            mouAgreementBudgetTypeReturned.setReleaseFrequency(releaseFrequency);
            mouAgreementBudgetTypeReturned.setAmount(rs.getDouble("AMOUNT"));

            return mouAgreementBudgetTypeReturned;
        });
        return mouAgreementBudgetTypes.size() > 0 ? mouAgreementBudgetTypes : null;
    }

    MOUAgreementBudgetType getMOUAgreementBudgetTypeByKeys(MOUAgreementBudgetType mouAgreementBudgetType) {
        String query = "SELECT * FROM MOU.MOU_AGREEMENT_BUDGET_TYPE WHERE MOU_AGREEMENT_ID=:MOU_AGREEMENT_ID" +
                " AND BUDGET_TYPE_ID=:BUDGET_TYPE_ID" +
                //" AND CUSTOMER_ID=:CUSTOMER_ID" +
                //" AND CUSTOMER_AGREEMENT_ID=:CUSTOMER_AGREEMENT_ID" +
                " AND AMOUNT=:AMOUNT" +
                " AND RELEASE_FREQUENCY_ID=:RELEASE_FREQUENCY_ID";
        Map<String, Object> params = new HashMap<>();
        params.put("MOU_AGREEMENT_ID", mouAgreementBudgetType.getMouAgreementId());
        //params.put("CUSTOMER_ID", mouAgreementBudgetType.getCustomerId());
        //params.put("CUSTOMER_AGREEMENT_ID", mouAgreementBudgetType.getCustomerMouAgreementId());
        params.put("BUDGET_TYPE_ID", mouAgreementBudgetType.getBudgetType().getBudgetTypeId());
        params.put("AMOUNT", mouAgreementBudgetType.getAmount());
        params.put("RELEASE_FREQUENCY_ID", mouAgreementBudgetType.getReleaseFrequency().getFrequencyTypeId());

        namedParameterJdbcTemplate.query(query, params,new MOUAgreementBudgetTypeMapper());
        List<MOUAgreementBudgetType> mouAgreementBudgetTypes = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            MOUAgreementBudgetType mouAgreementBudgetTypeReturned = new MOUAgreementBudgetType();
            mouAgreementBudgetTypeReturned.setMouAgreementBudgetTypeId(rs.getLong("MOU_AGREEMENT_BUDGET_TYPE_ID"));
            mouAgreementBudgetTypeReturned.setMouAgreementId(rs.getLong("MOU_AGREEMENT_ID"));
            //mouAgreementBudgetTypeReturned.setCustomerId(rs.getLong("CUSTOMER_ID"));
            //mouAgreementBudgetTypeReturned.setCustomerMouAgreementId(rs.getLong("CUSTOMER_AGREEMENT_ID"));
            //BudgetType budgetType = budgetTypeRepository.getBudgetTypeById(rs.getLong("BUDGET_TYPE_ID"));
            mouAgreementBudgetTypeReturned.setBudgetType(budgetTypeRepository.getBudgetTypeById(rs.getLong("BUDGET_TYPE_ID")));
            ReleaseFrequency releaseFrequency = releaseFrequencyRepository.getReleaseFrequencyById(rs.getLong("RELEASE_FREQUENCY_ID"));
            mouAgreementBudgetTypeReturned.setReleaseFrequency(releaseFrequency);
            mouAgreementBudgetTypeReturned.setAmount(rs.getDouble("AMOUNT"));

            return mouAgreementBudgetTypeReturned;
        });
        return mouAgreementBudgetTypes.size() > 0 ? mouAgreementBudgetTypes.get(0) : null;
    }
}
