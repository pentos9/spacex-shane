package com.buzz.controller.redis;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("shane/redis")
public class RedisLuaScriptController {

    private Logger logger = LoggerFactory.getLogger(RedisLuaScriptController.class);
    private static String INCR_LUA = StringUtils.EMPTY;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    static {
        StringBuilder builder = new StringBuilder();
        builder.append("local mock_id = redis.call(\"get\",KEYS[1]) ");
        builder.append("print( type(mock_id)) ");
        builder.append("if not mock_id then ");
        builder.append("    mock_id=redis.call(\"incr\",KEYS[2]) ");//返回的是 lua number 类型
        builder.append("    print( type(mock_id)) ");
        builder.append("    redis.call(\"set\",KEYS[1],tostring(mock_id) ) ");
        builder.append("    return tostring(mock_id) ");
        builder.append("else ");
        builder.append("    print( type(mock_id)) ");
        builder.append("    return tostring(mock_id) ");
        builder.append("end ");
        INCR_LUA = builder.toString();
    }

    @RequestMapping(value = "/lua", method = RequestMethod.GET)
    public String executeWithLuaScript(String deviceId) {
        logger.info("RedisLuaScriptController#executeWithLuaScript starts now");
        List<String> keys = Lists.newArrayList();
        keys.add("shane:mock-id:" + deviceId);
        keys.add("shane:global:mock-id:increment");
        RedisScript<String> script = new DefaultRedisScript<>(INCR_LUA, String.class);
        String result = stringRedisTemplate.execute(script, keys);
        logger.info("executeWithLuaScript#stringRedisTemplate.execute(script, keys) result ->{}", result);
        return result;
    }


}
