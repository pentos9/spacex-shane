package com.buzz.repository;

import com.buzz.model.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lucas on 3/2/18.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
