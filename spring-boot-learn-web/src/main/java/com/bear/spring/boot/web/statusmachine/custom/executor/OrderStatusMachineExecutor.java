package com.bear.spring.boot.web.statusmachine.custom.executor;

import com.bear.spring.boot.web.statusmachine.OrderStatusEnum;
import com.bear.spring.boot.web.statusmachine.custom.Handler;
import com.bear.spring.boot.web.statusmachine.custom.StatusMachine;
import com.bear.spring.boot.web.statusmachine.custom.StatusMachineConfigProcessor;
import com.bear.spring.boot.web.statusmachine.custom.bean.OrderMachine;
import com.bear.spring.boot.web.statusmachine.custom.enums.OrderStatusEventEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 订单状态机执行器
 * @author: xiankelai
 * @date: 2020-07-13
 * @time: 14:06
 */
@Service
@Slf4j
public class OrderStatusMachineExecutor {

    /**
     * 初始化状态流转执行器
     * @return StatusMachineConfigProcessor
     */
    private StatusMachineConfigProcessor<OrderStatusEnum, OrderStatusEventEnum, Handler> initConfigProcessor() {
        StatusMachineConfigProcessor<OrderStatusEnum, OrderStatusEventEnum, Handler> holder = new StatusMachineConfigProcessor<>();
        for (OrderStatusEventEnum event : OrderStatusEventEnum.values()) {
            holder.source(event.getSource()).event(event).handler(event.getHandler()).target(event.getTarget()).build();
        }
        return holder;
    }

    /**
     * 初始化状态流转执行器
     * @param event 执行事件
     * @return
     */
    @SuppressWarnings("all")
    private StatusMachineConfigProcessor<OrderStatusEnum, OrderStatusEventEnum, Handler> initConfigProcessor(OrderStatusEventEnum event) {
        StatusMachineConfigProcessor<OrderStatusEnum, OrderStatusEventEnum, Handler> holder = new StatusMachineConfigProcessor<>();
        holder.source(event.getSource()).event(event).handler(event.getHandler()).target(event.getTarget()).build();
        return holder;
    }


    /**
     * 执行状态机器
     * @param order 参数
     * @param event 事件
     */
    public void execute(OrderMachine order, OrderStatusEventEnum event) {
        log.info("execute() -> orderNo:{}, event:{}", order.getOrderNo(), event.toString());
        StatusMachine<OrderStatusEnum, OrderStatusEventEnum, Handler> stateMachine = new StatusMachine<>(initConfigProcessor(event));
        stateMachine.transition(order, event);
        log.info("execute() -> orderNo:{}, event:{}, status:{}, send MQ", order.getOrderNo(), event.toString(), order.getStatus().toString());
    }
}
