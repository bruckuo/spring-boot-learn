package com.bear.spring.boot.web.statusmachine.custom.bean;

import com.bear.spring.boot.web.statusmachine.OrderStatusEnum;
import com.bear.spring.boot.web.statusmachine.custom.StatusMachineDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @description: 实物订单状态机实体
 * @author: xiankelai
 * @date: 2020-07-10
 * @time: 11:59
 */
@Data
@NoArgsConstructor
public class OrderMachine implements StatusMachineDomain<OrderStatusEnum> {

    /**
     * 订单号
     */
    @NonNull
    private String orderNo;

    /**
     *  订单状态 执行前->初始状态; 执行后->执行事件后状态
     */
    @NonNull
    private OrderStatusEnum status;

    /**
     * 触发该动作操作人
     */
    private String operator;

    @Override
    public OrderStatusEnum getCurrentStatus() {
        return this.status;
    }

    @Override
    public void setNextStatus(OrderStatusEnum orderStatus) {
        this.status = orderStatus;
    }
}
