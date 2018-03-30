package com.buzz.controller;

import com.buzz.api.UserCreate;
import com.buzz.common.codec.CodecUtil;
import com.buzz.model.JsonResult;
import com.buzz.model.user.User;
import com.buzz.service.UserService;
import com.buzz.token.TokenManager;
import com.buzz.token.TokenModel;
import com.buzz.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserLoginController {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CodecUtil codecUtil;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult register(@RequestBody UserCreate userCreate) {
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

        String passwordMD5 = codecUtil.md5Hex(userCreate.getPassword());
        user.setPassword(passwordMD5);


        User dbUser = userService.getByLoginId(userCreate.getLogin_id());
        if (dbUser != null) {
            return new JsonResult(false, "注册失败，用户名已经被占用", "");
        }

        Long userId = userService.insert(user);
        userCreate.setId(userId);
        logger.info(String.format("userId:[%s]", userId));

        return new JsonResult(true, "注册成功！", JsonUtil.toJson(userCreate));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestParam String loginId, @RequestParam String password) {

        Assert.notNull(loginId, "loginId can not be null");
        Assert.notNull(password, "password can not be null");

        User user = userService.getByLoginId(loginId);
        String passwordMD5 = codecUtil.md5Hex(password);


        if (user == null) {
            return new JsonResult(false, "登录失败，用户不存在", "");
        }

        if (!passwordMD5.equals(user.getPassword())) {
            return new JsonResult(false, "登录失败，用户名或者密码不正确", "");
        }

        TokenModel model = tokenManager.setToken(user.getId());
        return new JsonResult(true, "登录成功！", "");
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
    public JsonResult logout(@RequestParam String loginId) {
        Assert.notNull(loginId, "loginId can not be null");

        User user = userService.getByLoginId(loginId);

        if (user != null) {
            boolean result = tokenManager.deleteToken(user.getId());
            logger.info(String.format("logout result:[%s]", result));
        }

        return new JsonResult(true, "退出成功！", "");
    }
}
