package com.buzz.rpc;


import com.buzz.model.user.User;

public interface UserRpcService {
    User getUser(long userId);
}
