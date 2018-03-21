package com.buzz.mapper;

import com.buzz.mapper.provider.UserProvider;
import com.buzz.model.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getById(@Param("id") long id);

    @SelectProvider(type = UserProvider.class, method = "getByIds")
    List<User> getByIds(@Param("ids") List<Long> ids);

    @Select("SELECT * FROM user WHERE login_id = #{loginId}")
    User getByLoginId(@Param("loginId") String loginId);

    @Insert("INSERT INTO user(username,login_id, password,phone,address) VALUES(#{user.username}, #{user.login_id}, #{user.password}, #{user.phone}, #{user.address})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    Long insert(@Param("user") User user);

    @Update("UPDATE user set username = #{user.username},password = #{user.password},phone = #{user.phone},address = #{user.address} where id = #{user.id}")
    Long update(@Param("user") User user);
}
