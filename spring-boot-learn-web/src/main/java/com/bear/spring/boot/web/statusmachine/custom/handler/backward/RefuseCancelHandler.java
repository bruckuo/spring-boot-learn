package com.bear.spring.boot.web.statusmachine.custom.handler.backward;

import com.bear.spring.boot.web.statusmachine.OrderStatusEnum;
import com.bear.spring.boot.web.statusmachine.custom.Handler;
import com.bear.spring.boot.web.statusmachine.custom.StatusMachineDomain;
import com.bear.spring.boot.web.statusmachine.custom.bean.OrderMachine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 商家拒单
 * @author: xiankelai
 * @date: 2020-07-10
 * @time: 14:45
 */
@Service
@Slf4j
public class RefuseCancelHandler implements Handler<OrderStatusEnum> {

    @Override
    public void handle(StatusMachineDomain<OrderStatusEnum> domain, OrderStatusEnum orderStatus) {
        OrderMachine tgOrderMachine = (OrderMachine) domain;
        log.info("状态机处理->商家拒单 orderNo:{} === {} --> {}", tgOrderMachine.getOrderNo(), domain.getCurrentStatus().toString(), orderStatus.toString());
        // TODO 处理核销逻辑
        domain.setNextStatus(orderStatus);
        //消息发送
    }
}
