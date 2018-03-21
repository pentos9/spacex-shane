package com.buzz.service.impl;

import com.buzz.mapper.UserMapper;
import com.buzz.model.user.User;
import com.buzz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(Long id) {
        User user = userMapper.getById(id);
        logger.info(String.format("id:[%d],user:[%s]", id, user));
        return user;
    }

    @Override
    public List<User> getByIds(List<Long> ids) {
        List<User> users = userMapper.getByIds(ids);
        return users;
    }

    @Override
    public User getByLoginId(String loginId) {
        User user = userMapper.getByLoginId(loginId);
        return user;
    }

    @Override
    public Long insert(User user) {
        Long effectRows = userMapper.insert(user);
        Long id = user.getId();
        logger.info(String.format("effectRows:[%d],id:[%d]", effectRows, id));
        return id;
    }

    @Override
    public boolean update(User user) {
        long effectRows = userMapper.update(user);

        logger.info(String.format("effectRows:[%d]", effectRows));
        return effectRows > 0;
    }

    @Override
    public boolean delete(Long id) {
        return true;
    }


}
