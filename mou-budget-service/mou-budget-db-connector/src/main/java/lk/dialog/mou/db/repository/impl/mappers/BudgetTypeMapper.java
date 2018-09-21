package lk.dialog.mou.db.repository.impl.mappers;

import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.domain.CustomerMOUAgreement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aux072 on 18/09/2018.
 */
public class BudgetTypeMapper  implements RowMapper<BudgetType> {
    @Override
    public BudgetType mapRow(ResultSet resultSet, int i) throws SQLException {
        BudgetType budgetType = new BudgetType();
        budgetType.setBudgetTypeId(resultSet.getLong("BUDGET_TYPE_ID"));
        budgetType.setBudgetName(resultSet.getString("BUDGET_NAME"));
        budgetType.setDescription(resultSet.getString("DESCRIPTION"));
        return budgetType;
    }
}
