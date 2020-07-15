package com.bear.spring.boot.web.statusmachine;


/**
 * @description: 订单状态事件处理枚举
 * @author: xiankelai
 * @date: 2020-07-13
 * @time: 12:00
 */
public enum OrderEventEnum {
    /**
     * 事件1
     * 下单:0->12
     */
    SUBMIT_ORDER_NO_PAY(OrderStatusEnum.INIT,  OrderStatusEnum.TO_TAKE_MEDICINE),

    /**
     * 事件2
     * 下单:0->13
     */
    SUBMIT_ORDER_PAY(OrderStatusEnum.INIT, OrderStatusEnum.TO_PAY),

    /**
     * 事件3
     * 付款:13->12
     */
    PAY(OrderStatusEnum.TO_PAY, OrderStatusEnum.TO_TAKE_ORDER),

    /**
     * 事件4
     * 邮寄: 商家接单12->10
     */
    TAKE_ORDER_TO_DELIVERY(OrderStatusEnum.TO_TAKE_ORDER, OrderStatusEnum.TO_DELIVERY),

    /**
     * 事件5
     * 自提: 商家接单12->2
     */
    TAKE_ORDER_TO_PICKUP(OrderStatusEnum.TO_TAKE_ORDER, OrderStatusEnum.TO_TAKE_MEDICINE),

    /**
     * 事件6
     * 取药:2->3
     */
    TAKE_MEDICINE(OrderStatusEnum.TO_TAKE_MEDICINE, OrderStatusEnum.COMPLETE),

    /**
     * 事件7
     * 发货:10->11
     */
    DELIVERY(OrderStatusEnum.TO_DELIVERY, OrderStatusEnum.TO_RECEIPT),

    /**
     * 事件8
     * 确认收货:11->3
     */
    CONFIRM_RECEIVE(OrderStatusEnum.TO_RECEIPT, OrderStatusEnum.COMPLETE),

    /**
     * 事件9
     * 支付取消 13->6
     */
    PAY_TIMEOUT_CANCEL(OrderStatusEnum.TO_PAY, OrderStatusEnum.CANCEL),

    /**
     * 事件10
     * 商家拒单:12->6
     */
    REFUSE(OrderStatusEnum.TO_TAKE_ORDER, OrderStatusEnum.CANCEL),

    /**
     * 事件11
     * 待发货取消 10->6
     */
    WAIT_DELIVERY_CANCEL(OrderStatusEnum.TO_DELIVERY, OrderStatusEnum.CANCEL),

    /**
     * 事件12
     * 待收货取消 11->6
     */
    WAIT_RECEIPT_CANCEL(OrderStatusEnum.TO_RECEIPT, OrderStatusEnum.CANCEL),

    /**
     * 事件13
     * 待取药取消 2->6
     */
    WAIT_TAKE_MEDICINE_CANCEL(OrderStatusEnum.TO_TAKE_MEDICINE, OrderStatusEnum.CANCEL),

    /**
     * 事件14
     * 完成取消 3->6
     */
    COMPLETE_CANCEL(OrderStatusEnum.COMPLETE, OrderStatusEnum.CANCEL),

    ;

    private OrderStatusEnum source;

    private OrderStatusEnum target;

    OrderEventEnum(OrderStatusEnum source, OrderStatusEnum target) {
        this.source = source;
        this.target = target;
    }

    public OrderStatusEnum getSource() {
        return source;
    }

    public OrderStatusEnum getTarget() {
        return target;
    }
}
