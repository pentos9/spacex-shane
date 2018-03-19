package com.buzz.token;

import com.buzz.constants.Constants;

import com.buzz.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    private RedisTemplate<String, String> redisClient;

//    @Autowired
//    public void setRedisClient(RedisTemplate redisClient) {
//        this.redisClient = redisClient;
//        redisClient.setKeySerializer(new JdkSerializationRedisSerializer());
//    }

    @Override
    public TokenModel createToken(long userId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        redisClient.boundValueOps(Long.toString(userId)).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel tokenModel) {
        if (tokenModel == null) {
            return false;
        }

        String token = redisClient.boundValueOps(Long.toString(tokenModel.getUserId())).get();
        if (token == null || !token.equals(tokenModel.getToken())) {
            return false;
        }

        redisClient.boundValueOps(Long.toString(tokenModel.getUserId())).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return false;
    }


    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] params = authentication.split("_");
        if (params.length != 2) {
            return null;
        }

        long userId = Long.parseLong(params[0]);
        String token = params[1];

        return new TokenModel(userId, token);
    }

    @Override
    public String getTokenByUserId(long userId) {
        String token = redisClient.boundValueOps(Long.toString(userId)).get();
        return token;
    }

    @Override
    public boolean deleteToken(long userId) {
        redisClient.delete(Long.toString(userId));
        return true;
    }
}
