package com.buzz.service;

import com.buzz.model.user.User;

import java.util.List;

public interface UserService {
    User get(Long id);

    List<User> getByIds(List<Long> ids);

    Long insert(User user);
}
