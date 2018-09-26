package lk.dialog.mou.db.repository.impl.mappers;

import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.domain.ReleaseFrequency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aux072 on 18/09/2018.
 */
public class ReleaseFrequencyMapper implements RowMapper<ReleaseFrequency> {
    @Override
    public ReleaseFrequency mapRow(ResultSet resultSet, int i) throws SQLException {
        ReleaseFrequency releaseFrequency = new ReleaseFrequency();
        releaseFrequency.setFrequencyTypeId(resultSet.getLong("RELEASE_FREQUENCY_ID"));
        releaseFrequency.setFrequencyCode(resultSet.getString("FREQUENCY_CODE"));
        //releaseFrequency.setDescription(resultSet.getString("DESCRIPTION"));
        return releaseFrequency;
    }
}
