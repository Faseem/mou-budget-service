package lk.dialog.mou.db.repository.impl;

import lk.dialog.mou.db.repository.UserRepository;
import lk.dialog.mou.db.repository.impl.mappers.UserRowMapper;
import lk.dialog.mou.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aux072 on 17/09/2018.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Autowired
    @Qualifier("mou-named-param-jdbc")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("mou-jdbc")
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByUserId(Long id) {
        return null;
    }

    @Override
    public User addUser(User user) {
        Map<String,String> parameters = new HashMap<>();
        parameters.put("USER_NAME", user.getUserName());
        namedParameterJdbcTemplate.update(
                "INSERT INTO MOU.USER (" +
                        "USER_NAME)" +
                        " VALUES(:USER_NAME)",
                parameters);
        return getUserByUserName(user.getUserName());
    }

    @Override
    public User getUserByUserName(String userName) {
        String query = "SELECT USER_ID,\n" +
                "       USER_NAME\n" +
                "FROM MOU.USER\n" +
                "WHERE USER_NAME = :userName";
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("userName", userName);
        User user = null;
        try {
            user = namedParameterJdbcTemplate.queryForObject(query, parameters,
                    new UserRowMapper());
        }catch (Exception e){

        }
        return user;
    }
}
