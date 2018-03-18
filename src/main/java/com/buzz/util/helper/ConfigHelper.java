package com.buzz.util.helper;

import com.buzz.util.PropsUtil;
import com.buzz.util.constant.ConfigConstant;

import java.util.Properties;

/**
 * 配置信息帮助类
 */
public class ConfigHelper {

    private static final Properties CONFIG_PROPS = PropsUtil.loadProperties(ConfigConstant.CONFIG_FILE);

    private static final String APP_BASE_PACKAGE = "com.buzz";

    public static String getAppBasePackage() {
        return APP_BASE_PACKAGE;
    }

    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH);
    }

    public static String getAppAssetPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH);
    }
}
