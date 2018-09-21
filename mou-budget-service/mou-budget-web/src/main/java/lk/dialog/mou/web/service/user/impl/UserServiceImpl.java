package lk.dialog.mou.web.service.user.impl;

import lk.dialog.mou.db.repository.UserRepository;
import lk.dialog.mou.domain.User;
import lk.dialog.mou.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aux072 on 17/09/2018.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User getUserByUserId(Long id) {
        return userRepository.getUserByUserId(id);
    }

    @Override
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }
}
