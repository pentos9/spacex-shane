package com.buzz.service.impl;

import com.buzz.model.user.User;
import com.buzz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User get(Long id) {
        User user = new User();

        if (id == null) {
            id = 0L;
        }

        user.setId(id);
        user.setUsername("Elon Musk");
        user.setPassword("password");
        user.setAddress("life on mars");
        user.setPhone("00978-89762");

        logger.info(String.format("id:[%d],user:[%s]", id, user));
        return user;
    }

    @Override
    public List<User> list(List<Long> ids) {
        return null;
    }
}
