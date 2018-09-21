package lk.dialog.mou.web.service.user;

import lk.dialog.mou.domain.User;

/**
 * Created by Aux072 on 17/09/2018.
 */
public interface UserService {
    User getUserByUserId(Long id);
    User addUser(User user);
    User getUserByUserName(String userName);
}
