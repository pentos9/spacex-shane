package com.buzz.common.switcher;

import java.util.List;

public interface SwitcherService {
    /**
     * 获取开关
     *
     * @param name
     * @return
     */
    Switcher getSwitcher(String name);

    /**
     * 获取说有开关
     *
     * @return
     */
    List<Switcher> getAllSwitchers();


    /**
     * 初始化开关
     *
     * @param name
     * @param initValue
     */
    void initSwitcher(String name, boolean initValue);


    /**
     * 是否开启
     *
     * @param name
     * @return
     */
    boolean isOpen(String name);

    /**
     * 开关是否开启
     *
     * @param name
     * @param defaultValue
     * @return 开关存在时返回开关值，开关不存在时设置开关为默认值，并返回默认值。
     */

    boolean isOpen(String name, boolean defaultValue);


    /**
     * 设置开关状态
     *
     * @param name
     * @param value
     */
    void setValue(String name, boolean value);


    /**
     * 注册开关监听器
     *
     * @param switchName
     * @param listener
     */
    void registerListener(String switchName, SwitcherListener listener);

    /**
     * unregister a switcher
     *
     * @param switcherName
     * @param listener
     */
    void unRegisterListener(String switcherName, SwitcherListener listener);

}
