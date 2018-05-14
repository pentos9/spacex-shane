package com.buzz.common.enums;

public enum MsgDelayedLevel {
    // 1MS 主要服务异步任务
    ONE_MS_DELAYED(0, 1L),
    // 1秒
    ONE_S_DELAYED(1, 1000L),

    // 5秒
    FIVE_S_DELAYED(2, 5 * 1000L),

    // 10秒
    TEN_S_DELAYED(3, 10 * 1000L),

    // 20秒
    TWENTY_S_DELAYED(4, 20 * 1000L),

    // 30秒
    THIRTY_S_DELAYED(5, 30 * 1000L),

    // 1分钟
    ONE_M_DELAYED(6, 60 * 1000L),

    // 5分钟
    FIVE_M_DELAYED(7, 5 * 60 * 1000L),

    // 10分钟
    TEN_M_DELAYED(8, 10 * 60 * 1000L),

    // 20分钟
    TWENTY_M_DELAYED(9, 20 * 60 * 1000L),

    // 30分钟
    THIRTY_M_DELAYED(10, 30 * 60 * 1000L),

    // 1小时
    ONE_H_DELAYED(11, 60 * 60 * 1000L),

    // 2小时
    TWO_H_DELAYED(12, 2 * 60 * 60 * 1000L),

    // 3小时
    THREE_H_DELAYED(13, 3 * 60 * 60 * 1000L),

    // 6小时
    SIX_H_DELAYED(14, 6 * 60 * 60 * 1000L),

    // 12小时
    TWELVE_H_DELAYED(15, 12 * 60 * 60 * 1000L),

    // 1天
    ONE_D_DELAYED(16, 24 * 60 * 60 * 1000L),

    // 2天
    TWO_D_DELAYED(17, 2 * 24 * 60 * 60 * 1000L),;

    private int level;
    private long delayedMs;

    MsgDelayedLevel(int level, long delayedMs) {
        this.level = level;
        this.delayedMs = delayedMs;
    }
}
