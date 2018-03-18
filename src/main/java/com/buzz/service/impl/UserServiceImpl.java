package com.buzz.service.impl;

import com.buzz.model.user.User;
import com.buzz.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Override
    public User get(Long id) {
        System.out.println("userGet ...:" + id);
        return null;
    }

    @Override
    public List<User> list(List<Long> ids) {
        return null;
    }
}
