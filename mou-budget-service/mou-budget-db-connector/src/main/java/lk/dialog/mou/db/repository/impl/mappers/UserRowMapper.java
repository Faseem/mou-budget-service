package lk.dialog.mou.db.repository.impl.mappers;

import lk.dialog.mou.domain.MOUAgreement;
import lk.dialog.mou.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aux072 on 17/09/2018.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getLong("USER_ID"));
        user.setUserName(resultSet.getString("USER_NAME"));
        return user;
    }
}
