package com.buzz.service;

import com.buzz.model.user.User;

import java.util.List;

public interface UserService {
    User get(Long id);

    List<User> getByIds(List<Long> ids);

    User getByLoginId(String LoginId);

    Long insert(User user);

    boolean update(User user);

    boolean delete(Long id);
}
