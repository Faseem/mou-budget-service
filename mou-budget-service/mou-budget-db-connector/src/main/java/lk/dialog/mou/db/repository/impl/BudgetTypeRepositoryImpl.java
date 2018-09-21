package lk.dialog.mou.db.repository.impl;

import lk.dialog.mou.db.repository.BudgetTypeRepository;
import lk.dialog.mou.db.repository.impl.mappers.BudgetTypeMapper;
import lk.dialog.mou.db.repository.impl.mappers.CustomerMapper;
import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.domain.Customer;
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
public class BudgetTypeRepositoryImpl implements BudgetTypeRepository {
    @Autowired
    @Qualifier("mou-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("mou-jdbc")
    private JdbcTemplate jdbcTemplate;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public BudgetType getBudgetTypeById(Long budgetTypeId) {
        String query = "SELECT * FROM MOU.BUDGET_TYPE WHERE BUDGET_TYPE_ID=:BUDGET_TYPE_ID";
        Map<String, Object> params = new HashMap<>();
        params.put("BUDGET_TYPE_ID", budgetTypeId);

        namedParameterJdbcTemplate.query(query, params,new BudgetTypeMapper());
        List<BudgetType> budgetTypeList = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            BudgetType budgetTypeReturned = new BudgetType();
            budgetTypeReturned.setBudgetName(rs.getString("BUDGET_NAME"));
            budgetTypeReturned.setDescription(rs.getString("DESCRIPTION"));
            budgetTypeReturned.setBudgetTypeId(rs.getLong("BUDGET_TYPE_ID"));
            return budgetTypeReturned;
        });
        return budgetTypeList.size() > 0 ? budgetTypeList.get(0) : null;
    }

    @Override
    public BudgetType getBudgetTypeByKeys(BudgetType budgetType) {
        String query = "SELECT * FROM MOU.BUDGET_TYPE WHERE BUDGET_NAME=:BUDGET_NAME AND DESCRIPTION=:DESCRIPTION";
        Map<String, Object> params = new HashMap<>();
        params.put("BUDGET_NAME", budgetType.getBudgetName());
        params.put("DESCRIPTION", budgetType.getDescription());

        namedParameterJdbcTemplate.query(query, params,new BudgetTypeMapper());
        List<BudgetType> budgetTypeList = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            BudgetType budgetTypeReturned = new BudgetType();
            budgetTypeReturned.setBudgetName(rs.getString("BUDGET_NAME"));
            budgetTypeReturned.setDescription(rs.getString("DESCRIPTION"));
            budgetTypeReturned.setBudgetTypeId(rs.getLong("BUDGET_TYPE_ID"));
            return budgetTypeReturned;
        });
        return budgetTypeList.size() > 0 ? budgetTypeList.get(0) : null;
    }

    @Override
    public BudgetType addBudgetType(BudgetType budgetType) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("BUDGET_NAME", budgetType.getBudgetName());
        parameters.put("DESCRIPTION", budgetType.getDescription());

        namedParameterJdbcTemplate.update("INSERT INTO MOU.BUDGET_TYPE (" +
                        "BUDGET_NAME," +
                        "DESCRIPTION)" +
                        " VALUES(:BUDGET_NAME, :DESCRIPTION)",
                parameters);

        return getBudgetTypeByKeys(budgetType);
    }
}
