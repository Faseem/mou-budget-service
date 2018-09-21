package lk.dialog.mou.db.repository.impl.mappers;

import lk.dialog.mou.domain.Budget;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aux072 on 19/09/2018.
 */
public class BudgetMapper implements RowMapper<Budget> {
    @Override
    public Budget mapRow(ResultSet resultSet, int i) throws SQLException {
        Budget budget = new Budget();
        budget.setBudgetId(resultSet.getLong("BUDGET_ID"));
        budget.setMouAgreementBudgetTypeId(resultSet.getLong("MOU_AGREEMENT_BUDGET_TYPE_ID"));
        budget.setMouCustomerId(resultSet.getLong("MOU_CUSTOMER_ID"));
        budget.setBudgetAmount(resultSet.getDouble("BUDGET_AMOUNT"));
        budget.setStartDate(resultSet.getDate("BUDGET_START_DATE"));
        budget.setSubPeriodCode(resultSet.getString("SUB_PERIOD_CODE"));
        budget.setSubPeriodCodeDesc(resultSet.getString("SUB_PERIOD_CODE_DESC"));
        budget.setEndDate(resultSet.getDate("BUDGET_END_DATE"));
        budget.setUsageAmount(resultSet.getDouble("USAGE_AMOUNT"));
        budget.setAvailableAmount(resultSet.getDouble("AVAILABLE_AMOUNT"));
        budget.setActive(resultSet.getBoolean("ACTIVE"));
        return budget;
    }
}
