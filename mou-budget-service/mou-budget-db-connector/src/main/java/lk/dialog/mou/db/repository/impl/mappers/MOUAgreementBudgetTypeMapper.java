package lk.dialog.mou.db.repository.impl.mappers;

import lk.dialog.mou.domain.ReleaseFrequency;
import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.domain.MOUAgreementBudgetType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aux072 on 18/09/2018.
 */
public class MOUAgreementBudgetTypeMapper  implements RowMapper<MOUAgreementBudgetType> {
    @Override
    public MOUAgreementBudgetType mapRow(ResultSet resultSet, int i) throws SQLException {
        MOUAgreementBudgetType mouAgreementBudgetType = new MOUAgreementBudgetType();
        BudgetType budgetType = new BudgetType();
        budgetType.setBudgetTypeId(resultSet.getLong("BUDGET_TYPE_ID"));
        mouAgreementBudgetType.setBudgetType(budgetType);
        ReleaseFrequency releaseFrequency = new ReleaseFrequency();
        releaseFrequency.setFrequencyTypeId(resultSet.getLong("RELEASE_FREQUENCY_ID"));
        mouAgreementBudgetType.setReleaseFrequency(releaseFrequency);
        mouAgreementBudgetType.setMouAgreementId(resultSet.getLong("MOU_AGREEMENT_ID"));
        mouAgreementBudgetType.setMouAgreementBudgetTypeId(resultSet.getLong("MOU_AGREEMENT_BUDGET_TYPE_ID"));
        mouAgreementBudgetType.setAmount(resultSet.getDouble("AMOUNT"));
        return mouAgreementBudgetType;
    }
}