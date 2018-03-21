package com.buzz.controller;

import com.buzz.cache.KeyGenerator;
import com.buzz.mapper.UserMapper;
import com.buzz.model.user.User;
import com.buzz.service.UserService;
import com.buzz.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Jedis jedis;

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User get(@PathVariable Long id) {

        if (id == null) {
            return null;
        }

        String key = KeyGenerator.generateKey(id);
        String userJson = jedis.get(key);

        if (userJson != null) {
            User user = JsonUtil.fromJson(userJson, User.class);
            return user;
        }

        User user = userService.get(id);
        if (user != null) {
            jedis.set(id.toString(), JsonUtil.toJson(user));
        }

        List<Long> userIds = new ArrayList<>();
        userIds.add(id);
        userIds.add(2L);
        userIds.add(3L);
        userIds.add(4L);

        List<User> userList = userService.getByIds(userIds);

        return user;

    }

    @RequestMapping(value = "/user/mget", method = RequestMethod.POST)
    @ResponseBody
    public List<User> getByIds(@RequestBody List<Long> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        List<User> users = userService.getByIds(ids);

        return users;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public User create(@RequestBody User user) {
        Long id = userService.insert(user);
        user = get(id);
        logger.info(String.format("user:[%s]", user));

        return user;

    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    public User update(@RequestBody User userUpdate) {
        boolean result = userService.update(userUpdate);
        User user = userService.get(userUpdate.getId());

        return user;
    }

}
