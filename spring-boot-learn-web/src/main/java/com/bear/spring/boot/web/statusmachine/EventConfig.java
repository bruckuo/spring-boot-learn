package com.bear.spring.boot.web.statusmachine;

import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @description:
 * @author: xiankelai
 * @date: 2020-07-14
 * @time: 19:43
 */
@WithStateMachine
public class EventConfig {

    @OnTransition(source = "INIT", target = "TO_TAKE_MEDICINE")
    public void pay(Message<OrderEventEnum> message) {
        System.out.println("---------用户无需支付，待取药");
    }

    @OnTransition(source = "INIT", target = "TO_PAY")
    public void receive(Message<OrderEventEnum> message) {
        System.out.println("---------用户待支付");
    }
}
