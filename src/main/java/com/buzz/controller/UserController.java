package com.buzz.controller;

import com.buzz.api.UserCreate;
import com.buzz.cache.KeyGenerator;
import com.buzz.constants.Constants;
import com.buzz.mapper.UserMapper;
import com.buzz.model.user.User;
import com.buzz.service.UserService;
import com.buzz.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Jedis jedis;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable Long id) {

        if (id == null) {
            return null;
        }

        String key = KeyGenerator.generateKey(id);

        String userJson = jedis.get(key);

        if (StringUtils.isNotBlank(userJson)) {
            User user = JsonUtil.fromJson(userJson, User.class);
            if (user != null) {
                return user;
            }
        }

        User user = userService.get(id);
        if (user != null) {
            jedis.set(id.toString(), JsonUtil.toJson(user));
        }

        return user;

    }

    @RequestMapping(value = "/mget", method = RequestMethod.POST)
    public List<User> getByIds(@RequestBody List<Long> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        List<User> users = userService.getByIds(ids);

        return users;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody UserCreate user) {

        User dbUser = userService.getByLoginId(user.getLogin_id());
        if (dbUser != null) {
            return Constants.ZERO;
        }

        Long id = userService.create(user);

        return id;

    }

    @RequestMapping(method = RequestMethod.PUT)
    public User update(@RequestBody User userUpdate) {
        boolean result = userService.update(userUpdate);
        logger.debug(String.format("#UserController.update result:%s", result));
        User user = userService.get(userUpdate.getId());

        return user;
    }

}
