package com.bear.spring.boot.web.statusmachine;

import java.util.EnumSet;

/**
 * @description:
 * @author: xiankelai
 * @date: 2020-07-14
 * @time: 16:43
 */
public enum OrderStatusEnum {
    INIT(0, "初始状态"),
    TO_SHARE(1, "待分享"),
    TO_TAKE_MEDICINE(2, "待取药"),
    COMPLETE(3, "已完成"),
    GROUP_EXPIRED(4, "拼团已过期"),
    EXCHANGE_EXPIRED(5, "兑换超时"),
    CANCEL(6, "已取消"),
    MANUAL_CANCEL(7, "所有人手动取消"),
    ACTIVITY_EXPIRED(8, "活动已过期"),
    RETURNED(9, "已退货"),
    TO_DELIVERY(10, "待发货"),
    TO_RECEIPT(11, "待收货"),
    TO_TAKE_ORDER(12, "待接单"),
    TO_PAY(13, "待支付");

    private int status;

    private String value;

    OrderStatusEnum(int status, String value) {
        this.status = status;
        this.value = value;
    }

    public static OrderStatusEnum valueOf(int statusCode) {
        for (OrderStatusEnum st : EnumSet.allOf(OrderStatusEnum.class)) {
            if (st.status == statusCode)
                return st;
        }
        throw new IllegalArgumentException("invalid status code");
    }

    public String getValue() {
        return value;
    }

    public int getStatus() {
        return status;
    }}
