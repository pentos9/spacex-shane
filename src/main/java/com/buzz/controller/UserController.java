package com.buzz.controller;

import com.buzz.model.user.User;
import com.buzz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private UserService userService;

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User get(Long id) {
        if (id != null) {
            User user = userService.get(id);
            return user;
        }
        return null;
    }
}
