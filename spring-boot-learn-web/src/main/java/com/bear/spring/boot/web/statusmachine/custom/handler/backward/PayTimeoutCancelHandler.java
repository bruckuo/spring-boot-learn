package com.bear.spring.boot.web.statusmachine.custom.handler.backward;

import com.bear.spring.boot.web.statusmachine.OrderStatusEnum;
import com.bear.spring.boot.web.statusmachine.custom.Handler;
import com.bear.spring.boot.web.statusmachine.custom.StatusMachineDomain;
import com.bear.spring.boot.web.statusmachine.custom.bean.OrderMachine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 实物订单状态机实体
 * @author: xiankelai
 * @date: 2020-07-10
 * @time: 11:59
 */
@Service
@Slf4j
public class PayTimeoutCancelHandler implements Handler<OrderStatusEnum> {

    @Override
    public void handle(StatusMachineDomain domain, OrderStatusEnum orderStatus) {
        OrderMachine tgOrderMachine = (OrderMachine) domain;
        log.info("状态机处理->订单支付超时 orderNo:{} === {} --> {}", tgOrderMachine.getOrderNo(), domain.getCurrentStatus().toString(), orderStatus.toString());
        // TODO 处理核销逻辑
        domain.setNextStatus(orderStatus);
        //消息发送
    }
}
