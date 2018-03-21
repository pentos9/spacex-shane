package com.buzz.mapper.provider;


import com.buzz.model.user.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class UserProvider {
    private final String table = "user";

    public String getByIds(List<Long> ids) {
        StringBuilder whereBuilder = new StringBuilder();
        SQL sql = new SQL();

        whereBuilder.append("id in (").append(StringUtils.join(ids, ",")).append(")");

        sql.SELECT("*").FROM(table).WHERE(whereBuilder.toString());

        return sql.toString();
    }

    public String update(User user) {
        SQL sql = new SQL();

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("update user set ");

        if (user.getUsername() != null) {
            sqlBuilder.append("username =  ").append(user.getUsername());
        }

        if (user.getPassword() != null) {
            sqlBuilder.append("password = ").append(user.getPassword());
        }

        if (user.getPhone() != null) {
            sqlBuilder.append("phone = ").append(user.getPhone());
        }

        if (user.getAddress() != null) {
            sqlBuilder.append("address = ").append(user.getAddress());
        }
        sqlBuilder.append("where id = ").append(user.getId());

        return sqlBuilder.toString();
    }
}
