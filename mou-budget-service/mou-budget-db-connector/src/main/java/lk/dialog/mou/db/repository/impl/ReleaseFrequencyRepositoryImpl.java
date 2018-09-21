package lk.dialog.mou.db.repository.impl;

import lk.dialog.mou.db.repository.BudgetTypeRepository;
import lk.dialog.mou.db.repository.ReleaseFrequencyRepository;
import lk.dialog.mou.db.repository.impl.mappers.BudgetTypeMapper;
import lk.dialog.mou.db.repository.impl.mappers.ReleaseFrequencyMapper;
import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.domain.ReleaseFrequency;
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
public class ReleaseFrequencyRepositoryImpl implements ReleaseFrequencyRepository {

    @Autowired
    @Qualifier("mou-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("mou-jdbc")
    private JdbcTemplate jdbcTemplate;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ReleaseFrequency getReleaseFrequencyById(Long releaseFrequencyId) {
        String query = "SELECT * FROM MOU.RELEASE_FREQUENCY WHERE RELEASE_FREQUENCY_ID=:RELEASE_FREQUENCY_ID";
        Map<String, Object> params = new HashMap<>();
        params.put("RELEASE_FREQUENCY_ID", releaseFrequencyId);

        namedParameterJdbcTemplate.query(query, params,new ReleaseFrequencyMapper());
        List<ReleaseFrequency> releaseFrequencyList = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            ReleaseFrequency releaseFrequencyReturned = new ReleaseFrequency();
            releaseFrequencyReturned.setFrequencyName(rs.getString("FREQUENCY_NAME"));
            releaseFrequencyReturned.setDescription(rs.getString("DESCRIPTION"));
            releaseFrequencyReturned.setFrequencyTypeId(rs.getLong("RELEASE_FREQUENCY_ID"));
            return releaseFrequencyReturned;
        });
        return releaseFrequencyList.size() > 0 ? releaseFrequencyList.get(0) : null;
    }

    @Override
    public ReleaseFrequency getReleaseFrequencyByKeys(ReleaseFrequency releaseFrequency) {
        String query = "SELECT * FROM MOU.RELEASE_FREQUENCY WHERE FREQUENCY_NAME=:FREQUENCY_NAME AND DESCRIPTION=:DESCRIPTION";
        Map<String, Object> params = new HashMap<>();
        params.put("FREQUENCY_NAME", releaseFrequency.getFrequencyName());
        params.put("DESCRIPTION", releaseFrequency.getDescription());

        namedParameterJdbcTemplate.query(query, params,new ReleaseFrequencyMapper());
        List<ReleaseFrequency> releaseFrequencyList = namedParameterJdbcTemplate.query(query, params, (rs, i) -> {
            ReleaseFrequency releaseFrequencyReturned = new ReleaseFrequency();
            releaseFrequencyReturned.setFrequencyName(rs.getString("FREQUENCY_NAME"));
            releaseFrequencyReturned.setDescription(rs.getString("DESCRIPTION"));
            releaseFrequencyReturned.setFrequencyTypeId(rs.getLong("RELEASE_FREQUENCY_ID"));
            return releaseFrequencyReturned;
        });
        return releaseFrequencyList.size() > 0 ? releaseFrequencyList.get(0) : null;
    }

    @Override
    public ReleaseFrequency addReleaseFrequency(ReleaseFrequency releaseFrequency) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("FREQUENCY_NAME", releaseFrequency.getFrequencyName());
        parameters.put("DESCRIPTION", releaseFrequency.getDescription());

        namedParameterJdbcTemplate.update("INSERT INTO MOU.RELEASE_FREQUENCY (" +
                        "FREQUENCY_NAME," +
                        "DESCRIPTION)" +
                        " VALUES(:FREQUENCY_NAME, :DESCRIPTION)",
                parameters);

        return getReleaseFrequencyByKeys(releaseFrequency);
    }
}
