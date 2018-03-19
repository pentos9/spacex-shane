package com.buzz.token;

/**
 * Created by lucas on 3/2/18.
 */
public interface TokenManager {
    /**
     * 创建一个Token关联制定用户
     *
     * @param userId
     * @return
     */
    TokenModel createToken(long userId);


    /**
     * 检查Token是否有效
     *
     * @param tokenModel
     * @return
     */
    boolean checkToken(TokenModel tokenModel);

    /**
     * 从字符串中解析Token
     *
     * @param authentication
     * @return
     */
    TokenModel getToken(String authentication);

    /**
     * 根据用户ID获取Token
     *
     * @param userId
     * @return
     */
    String getTokenByUserId(long userId);

    /**
     * 清楚Token
     *
     * @param userId
     * @return
     */
    boolean deleteToken(long userId);

}
