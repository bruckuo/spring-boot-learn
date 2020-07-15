package com.bear.spring.boot.web.statusmachine.custom.enums;

import com.bear.spring.boot.web.statusmachine.OrderStatusEnum;
import com.bear.spring.boot.web.statusmachine.custom.Handler;
import com.bear.spring.boot.web.statusmachine.custom.handler.backward.CompleteCancelHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.backward.PayTimeoutCancelHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.backward.RefuseCancelHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.backward.WaitDeliveryCancelHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.backward.WaitReceiptCancelHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.backward.WaitTakeMedicineCancelHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.forward.ConfirmReceiveHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.forward.DeliveryHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.forward.PayHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.forward.SubmitOrder4NoPayHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.forward.SubmitOrder4PayHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.forward.TakeMedicineHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.forward.TakeOrder4deliveryHandler;
import com.bear.spring.boot.web.statusmachine.custom.handler.forward.TakeOrder4pickupHandler;

/**
 * @description: 订单状态事件处理枚举
 * @author: xiankelai
 * @date: 2020-07-13
 * @time: 12:00
 */
public enum OrderStatusEventEnum {
    /**
     * 事件1
     * 下单:0->12
     */
    SUBMIT_ORDER_NO_PAY(OrderStatusEnum.INIT, new SubmitOrder4NoPayHandler(), OrderStatusEnum.TO_TAKE_MEDICINE),

    /**
     * 事件2
     * 下单:0->13
     */
    SUBMIT_ORDER_PAY(OrderStatusEnum.INIT, new SubmitOrder4PayHandler(), OrderStatusEnum.TO_PAY),

    /**
     * 事件3
     * 付款:13->12
     */
    PAY(OrderStatusEnum.TO_PAY, new PayHandler(), OrderStatusEnum.TO_TAKE_ORDER),

    /**
     * 事件4
     * 邮寄: 商家接单12->10
     */
    TAKE_ORDER_TO_DELIVERY(OrderStatusEnum.TO_TAKE_ORDER, new TakeOrder4deliveryHandler(), OrderStatusEnum.TO_DELIVERY),

    /**
     * 事件5
     * 自提: 商家接单12->2
     */
    TAKE_ORDER_TO_PICKUP(OrderStatusEnum.TO_TAKE_ORDER, new TakeOrder4pickupHandler(), OrderStatusEnum.TO_TAKE_MEDICINE),

    /**
     * 事件6
     * 取药:2->3
     */
    TAKE_MEDICINE(OrderStatusEnum.TO_TAKE_MEDICINE, new TakeMedicineHandler(), OrderStatusEnum.COMPLETE),

    /**
     * 事件7
     * 发货:10->11
     */
    DELIVERY(OrderStatusEnum.TO_DELIVERY, new DeliveryHandler(), OrderStatusEnum.TO_RECEIPT),

    /**
     * 事件8
     * 确认收货:11->3
     */
    CONFIRM_RECEIVE(OrderStatusEnum.TO_RECEIPT, new ConfirmReceiveHandler(), OrderStatusEnum.COMPLETE),

    /**
     * 事件9
     * 支付取消 13->6
     */
    PAY_TIMEOUT_CANCEL(OrderStatusEnum.TO_PAY, new PayTimeoutCancelHandler(), OrderStatusEnum.CANCEL),

    /**
     * 事件10
     * 商家拒单:12->6
     */
    REFUSE(OrderStatusEnum.TO_TAKE_ORDER, new RefuseCancelHandler(), OrderStatusEnum.CANCEL),

    /**
     * 事件11
     * 待发货取消 10->6
     */
    WAIT_DELIVERY_CANCEL(OrderStatusEnum.TO_DELIVERY, new WaitDeliveryCancelHandler(), OrderStatusEnum.CANCEL),

    /**
     * 事件12
     * 待收货取消 11->6
     */
    WAIT_RECEIPT_CANCEL(OrderStatusEnum.TO_RECEIPT, new WaitReceiptCancelHandler(), OrderStatusEnum.CANCEL),

    /**
     * 事件13
     * 待取药取消 2->6
     */
    WAIT_TAKE_MEDICINE_CANCEL(OrderStatusEnum.TO_TAKE_MEDICINE, new WaitTakeMedicineCancelHandler(), OrderStatusEnum.CANCEL),

    /**
     * 事件14
     * 完成取消 3->6
     */
    COMPLETE_CANCEL(OrderStatusEnum.COMPLETE, new CompleteCancelHandler(), OrderStatusEnum.CANCEL),

    ;

    private OrderStatusEnum source;

    private Handler handler;

    private OrderStatusEnum target;

    OrderStatusEventEnum(OrderStatusEnum source, Handler handler, OrderStatusEnum target) {
        this.source = source;
        this.handler = handler;
        this.target = target;
    }

    public OrderStatusEnum getSource() {
        return source;
    }

    public OrderStatusEnum getTarget() {
        return target;
    }

    public Handler getHandler() {
        return handler;
    }
}
