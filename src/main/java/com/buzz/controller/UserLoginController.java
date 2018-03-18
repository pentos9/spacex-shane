package com.buzz.controller;

import com.buzz.authorization.annotation.Authorization;
import com.buzz.constants.ResultStatus;
import com.buzz.model.ResultModel;
import com.buzz.model.user.User;
import com.buzz.repository.UserRepository;
import com.buzz.token.TokenManager;
import com.buzz.token.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class UserLoginController {
    //@Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResultModel> login(@RequestParam String username, @RequestParam String password) {

        Assert.notNull(username, "username can not be null");
        Assert.notNull(password, "password can not be null");

        User user = userRepository.findByUsername(username);

        if (user == null || //user not exist
                "".equals(password)) { // password error
            return new ResponseEntity<ResultModel>(ResultModel.ok(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }

        TokenModel model = tokenManager.createToken(user.getId());
        return new ResponseEntity<ResultModel>(ResultModel.ok(model), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    public ResponseEntity<ResultModel> logout(@RequestParam("userId") long userId) {
        tokenManager.deleteToken(userId);
        return new ResponseEntity<ResultModel>(ResultModel.ok(ResultModel.ok()), HttpStatus.OK);
    }
}
