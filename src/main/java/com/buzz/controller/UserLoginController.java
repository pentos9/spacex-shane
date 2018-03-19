package com.buzz.controller;

import com.buzz.authorization.annotation.Authorization;
import com.buzz.constants.ResultStatus;
import com.buzz.model.ResultModel;
import com.buzz.model.user.User;
import com.buzz.repository.UserRepository;
import com.buzz.service.UserService;
import com.buzz.token.TokenManager;
import com.buzz.token.TokenModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserLoginController {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel login(@RequestParam String loginId, @RequestParam String password) {

        Assert.notNull(loginId, "loginId can not be null");
        Assert.notNull(password, "password can not be null");

        User user = userService.getByLoginId(loginId);

        if (user == null || //user not exist
                !password.equals(user.getPassword())) { // password error
            return ResultModel.ok(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
        }

        TokenModel model = tokenManager.createToken(user.getId());
        return ResultModel.ok(model);
    }

    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    @ResponseBody
    public boolean isLogin(@RequestParam String loginId) {

        Assert.notNull(loginId);
        User user = userService.getByLoginId(loginId);

        boolean isLogin = false;
        if (user != null) {
            String token = tokenManager.getTokenByUserId(user.getId());
            if (StringUtils.isNotBlank(token)) {
                isLogin = true;
            }
        }

        logger.info(String.format("isLogin:[%s]", isLogin));

        return isLogin;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultModel logout(@RequestParam String loginId) {
        Assert.notNull(loginId, "loginId can not be null");

        User user = userService.getByLoginId(loginId);

        if (user != null) {
            boolean result = tokenManager.deleteToken(user.getId());
            logger.info(String.format("logout result:[%s]", result));
        }

        return ResultModel.ok();
    }
}
