package com.buzz.test.core;

import com.buzz.api.UserCreate;
import com.buzz.model.user.User;
import com.buzz.service.UserService;
import com.buzz.service.impl.UserServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DynamicProxyTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        UserService userService = Reflection.newProxy(UserService.class, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                UserService userService1 = new UserService() {
                    @Override
                    public User get(Long id) {
                        User user = new User();
                        user.setId(id);
                        return user;
                    }

                    @Override
                    public List<User> getByIds(List<Long> ids) {
                        return new ArrayList<User>();
                    }

                    @Override
                    public User getByLoginId(String LoginId) {
                        User user = new User();
                        user.setLogin_id(LoginId);
                        return user;
                    }

                    @Override
                    public Long create(UserCreate user) {
                        return 12312L;
                    }

                    @Override
                    public boolean update(User user) {
                        return false;
                    }

                    @Override
                    public boolean delete(Long id) {
                        return false;
                    }
                };


                return method.invoke(userService1, args);
            }
        });

        System.out.println(userService.get(1L));

    }
}
