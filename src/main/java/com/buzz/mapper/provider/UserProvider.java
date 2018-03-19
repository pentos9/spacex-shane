package com.buzz.mapper.provider;


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
}
