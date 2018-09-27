package lk.dialog.mou.db.repository.impl;

import lk.dialog.mou.db.repository.BudgetRepository;
import lk.dialog.mou.db.repository.impl.mappers.BudgetMapper;
import lk.dialog.mou.domain.Budget;
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
 * Created by Aux072 on 19/09/2018.
 */
@Repository
public class BudgetRepositoryImpl implements BudgetRepository {

    @Autowired
    @Qualifier("mou-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("mou-jdbc")
    private JdbcTemplate jdbcTemplate;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public Budget addBudgetType(Budget budget) {
        Map<String,Object> parameters = new HashMap<>();
        //parameters.put("BUDGET_ID", budget.getBudgetId());
        parameters.put("MOU_AGREEMENT_BUDGET_TYPE_ID", budget.getMouAgreementBudgetTypeId());
        //parameters.put("MOU_CUSTOMER_ID", budget.getMouCustomerId());
        parameters.put("BUDGET_AMOUNT", budget.getBudgetAmount());
        parameters.put("BUDGET_START_DATE", dateFormatter.format(budget.getStartDate()));
        parameters.put("SUB_PERIOD_CODE", budget.getSubPeriodCode());
        parameters.put("SUB_PERIOD_CODE_DESC", budget.getSubPeriodCodeDesc());
        parameters.put("BUDGET_END_DATE", dateFormatter.format(budget.getEndDate()));
        parameters.put("USAGE_AMOUNT", budget.getUsageAmount());
        parameters.put("AVAILABLE_AMOUNT", budget.getAvailableAmount());
        parameters.put("ACTIVE", budget.getActive());

        namedParameterJdbcTemplate.update("INSERT INTO MOU.BUDGET (" +
                        "MOU_AGREEMENT_BUDGET_TYPE_ID," +
                        //"MOU_CUSTOMER_ID," +
                        "BUDGET_AMOUNT," +
                        "BUDGET_START_DATE," +
                        "SUB_PERIOD_CODE," +
                        "SUB_PERIOD_CODE_DESC," +
                        "BUDGET_END_DATE," +
                        "USAGE_AMOUNT," +
                        "AVAILABLE_AMOUNT," +
                        "ACTIVE)" +
                        " VALUES(:MOU_AGREEMENT_BUDGET_TYPE_ID, :BUDGET_AMOUNT, :BUDGET_START_DATE," +
                        " :SUB_PERIOD_CODE, :SUB_PERIOD_CODE_DESC, :BUDGET_END_DATE, :USAGE_AMOUNT, :AVAILABLE_AMOUNT, :ACTIVE)",
                parameters);
        return getBudgetByKeys(budget);
    }

    @Override
    public List<Budget> getBudgetsForMouAgreementBudgetTypeId(Long mouAgreementBudgetTypeId) {
        String query = "SELECT * FROM MOU.BUDGET WHERE MOU_AGREEMENT_BUDGET_TYPE_ID=:MOU_AGREEMENT_BUDGET_TYPE_ID";

        Map<String, Object> params = new HashMap<>();
        params.put("MOU_AGREEMENT_BUDGET_TYPE_ID", mouAgreementBudgetTypeId);

        namedParameterJdbcTemplate.query(query, params,new BudgetMapper());
        List<Budget> budgetList = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            Budget budgetReturned = new Budget();
            budgetReturned.setBudgetId(rs.getLong("BUDGET_ID"));
            budgetReturned.setMouAgreementBudgetTypeId(rs.getLong("MOU_AGREEMENT_BUDGET_TYPE_ID"));
            //budgetReturned.setMouCustomerId(rs.getLong("MOU_CUSTOMER_ID"));
            budgetReturned.setBudgetAmount(rs.getDouble("BUDGET_AMOUNT"));
            budgetReturned.setStartDate(rs.getDate("BUDGET_START_DATE"));
            budgetReturned.setSubPeriodCode(rs.getString("SUB_PERIOD_CODE"));
            budgetReturned.setSubPeriodCodeDesc(rs.getString("SUB_PERIOD_CODE_DESC"));
            budgetReturned.setEndDate(rs.getDate("BUDGET_END_DATE"));
            budgetReturned.setUsageAmount(rs.getDouble("USAGE_AMOUNT"));
            budgetReturned.setAvailableAmount(rs.getDouble("AVAILABLE_AMOUNT"));
            budgetReturned.setActive(rs.getBoolean("ACTIVE"));
            return budgetReturned;
        });
        return budgetList.size() > 0 ? budgetList : null;
    }

    public Budget getBudgetByKeys(Budget budget) {
        String query = "SELECT * FROM MOU.BUDGET WHERE MOU_AGREEMENT_BUDGET_TYPE_ID=:MOU_AGREEMENT_BUDGET_TYPE_ID" +
                //" AND MOU_CUSTOMER_ID=:MOU_CUSTOMER_ID"+
                " AND BUDGET_AMOUNT=:BUDGET_AMOUNT"+
                " AND BUDGET_START_DATE=:BUDGET_START_DATE"+
                //" AND SUB_PERIOD_CODE=:SUB_PERIOD_CODE"+
                //" AND SUB_PERIOD_CODE_DESC=:SUB_PERIOD_CODE_DESC"+
                " AND BUDGET_END_DATE=:BUDGET_END_DATE"+
                " AND USAGE_AMOUNT=:USAGE_AMOUNT"+
                " AND AVAILABLE_AMOUNT=:AVAILABLE_AMOUNT"+
                " AND ACTIVE=:ACTIVE";

        Map<String, Object> params = new HashMap<>();
        params.put("MOU_AGREEMENT_BUDGET_TYPE_ID", budget.getMouAgreementBudgetTypeId());
        //params.put("MOU_CUSTOMER_ID", budget.getMouCustomerId());
        params.put("BUDGET_AMOUNT", budget.getBudgetAmount());
        params.put("BUDGET_START_DATE", dateFormatter.format(budget.getStartDate()));
        //params.put("SUB_PERIOD_CODE", budget.getSubPeriodCode());
        //params.put("SUB_PERIOD_CODE_DESC", budget.getSubPeriodCodeDesc());
        params.put("BUDGET_END_DATE", dateFormatter.format(budget.getEndDate()));
        params.put("USAGE_AMOUNT", budget.getUsageAmount());
        params.put("AVAILABLE_AMOUNT", budget.getAvailableAmount());
        params.put("ACTIVE", budget.getActive());

        namedParameterJdbcTemplate.query(query, params,new BudgetMapper());
        List<Budget> budgetList = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            Budget budgetReturned = new Budget();
            budgetReturned.setBudgetId(rs.getLong("BUDGET_ID"));
            budgetReturned.setMouAgreementBudgetTypeId(rs.getLong("MOU_AGREEMENT_BUDGET_TYPE_ID"));
            //budgetReturned.setMouCustomerId(rs.getLong("MOU_CUSTOMER_ID"));
            budgetReturned.setBudgetAmount(rs.getDouble("BUDGET_AMOUNT"));
            budgetReturned.setStartDate(rs.getDate("BUDGET_START_DATE"));
            budgetReturned.setSubPeriodCode(rs.getString("SUB_PERIOD_CODE"));
            budgetReturned.setSubPeriodCodeDesc(rs.getString("SUB_PERIOD_CODE_DESC"));
            budgetReturned.setEndDate(rs.getDate("BUDGET_END_DATE"));
            budgetReturned.setUsageAmount(rs.getDouble("USAGE_AMOUNT"));
            budgetReturned.setAvailableAmount(rs.getDouble("AVAILABLE_AMOUNT"));
            budgetReturned.setActive(rs.getBoolean("ACTIVE"));
            return budgetReturned;
        });
        return budgetList.size() > 0 ? budgetList.get(0) : null;
    }


}
