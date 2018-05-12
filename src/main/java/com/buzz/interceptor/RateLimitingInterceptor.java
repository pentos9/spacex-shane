package com.buzz.interceptor;

import com.buzz.interceptor.entity.SimpleRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitingInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(RateLimitingInterceptor.class);

    @Value("${rate.limit.enabled}")
    private boolean enabled;

    @Value("${rate.hourly.limit}")
    private int hourlyLimit;

    private Map<String, SimpleRateLimiter> limiters = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!enabled) {
            return true;
        }

        String clientIp = request.getHeader("Client-Id");

        //let non-client-ip pass
        if (clientIp == null) {
            return true;
        }

        SimpleRateLimiter simpleRateLimiter = getRateLimiter(clientIp);

        boolean allowRequest = simpleRateLimiter.tryAcquire();
        if (!allowRequest) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        }

        response.setHeader("X-RateLimit-Limit", String.valueOf(hourlyLimit));

        return allowRequest;
    }

    private SimpleRateLimiter getRateLimiter(String clientIp) {

        if (limiters.containsKey(clientIp)) {
            return limiters.get(clientIp);
        } else {
            synchronized (clientIp.trim()) {
                // double-checked locking to avoid multiple-reinitializations
                if (limiters.containsKey(clientIp)) {
                    return limiters.get(clientIp);
                } else {
                    SimpleRateLimiter limiter = SimpleRateLimiter.create(hourlyLimit, TimeUnit.HOURS);
                    limiters.put(clientIp, limiter);
                    return limiter;
                }
            }
        }
    }
}
