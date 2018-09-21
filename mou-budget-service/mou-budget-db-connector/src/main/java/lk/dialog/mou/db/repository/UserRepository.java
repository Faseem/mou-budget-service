package lk.dialog.mou.db.repository;

import lk.dialog.mou.domain.User;

import java.util.List;

/**
 * Created by Aux072 on 17/09/2018.
 */
public interface UserRepository {
    User getUserByUserId(Long id);
    User addUser(User user);
    User getUserByUserName(String userName);
}
