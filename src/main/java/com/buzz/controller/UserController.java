package com.buzz.controller;

import com.buzz.common.KeyGenerator;
import com.buzz.model.user.User;
import com.buzz.service.UserService;
import com.buzz.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Jedis jedis;

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User get(@PathVariable Long id) {
        if (id != null) {
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
            return user;
        }
        return null;
    }
}
