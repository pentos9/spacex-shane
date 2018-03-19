package com.buzz.controller;


import com.buzz.api.UserCreate;
import com.buzz.model.user.User;
import com.buzz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRegisterController {
    private Logger logger = LoggerFactory.getLogger(UserRegisterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public boolean register(@RequestBody UserCreate userCreate) {
        Assert.notNull(userCreate, "user can not be null");

        Assert.notNull(userCreate.getUsername(), "username can not be null");
        Assert.notNull(userCreate.getPassword(), "password can not be null");
        Assert.notNull(userCreate.getLogin_id(), "login_id can not be null");
        Assert.notNull(userCreate.getPhone(), "phone can not be null");
        Assert.notNull(userCreate.getAddress(), "address can not be null");


        User user = new User();
        user.setUsername(userCreate.getUsername());
        user.setPassword(userCreate.getPassword());
        user.setPhone(userCreate.getPhone());
        user.setAddress(userCreate.getAddress());
        user.setLogin_id(userCreate.getLogin_id());

        boolean result = true;

        try {
            Long userId = userService.insert(user);
            logger.info(String.format("userId:[%s]", userId));
        } catch (Exception e) {
            logger.info(String.format("exception:[%s]", e));
            result = false;
        }

        return result;
    }
}
