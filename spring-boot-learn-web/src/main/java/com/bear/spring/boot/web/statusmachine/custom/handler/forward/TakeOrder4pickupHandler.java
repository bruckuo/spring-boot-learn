package com.bear.spring.boot.web.statusmachine.custom.handler.forward;

import com.bear.spring.boot.web.statusmachine.OrderStatusEnum;
import com.bear.spring.boot.web.statusmachine.custom.Handler;
import com.bear.spring.boot.web.statusmachine.custom.StatusMachineDomain;
import com.bear.spring.boot.web.statusmachine.custom.bean.OrderMachine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 商家接单 自提
 * @author: xiankelai
 * @date: 2020-07-10
 * @time: 15:18
 */
@Service
@Slf4j
public class TakeOrder4pickupHandler implements Handler<OrderStatusEnum> {

    @Override
    public void handle(StatusMachineDomain<OrderStatusEnum> domain, OrderStatusEnum orderStatus) {
        OrderMachine tgOrderMachine = (OrderMachine) domain;
        log.info("状态机处理->接单(自提) orderNo:{} === {} --> {}", tgOrderMachine.getOrderNo(), domain.getCurrentStatus().toString(), orderStatus.toString());
        // TODO 处理核销逻辑
        domain.setNextStatus(orderStatus);
    }
}