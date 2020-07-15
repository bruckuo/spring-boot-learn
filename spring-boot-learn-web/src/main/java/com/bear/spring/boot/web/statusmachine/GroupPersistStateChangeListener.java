package com.bear.spring.boot.web.statusmachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @description:
 * @author: xiankelai
 * @date: 2020-07-14
 * @time: 17:06
 */
@Component
@Slf4j
public class GroupPersistStateChangeListener implements PersistStateMachineHandler.PersistStateChangeListener {

    public void onPersist(State<OrderStatusEnum, OrderEventEnum> state, Message<OrderEventEnum> message, Transition<OrderStatusEnum, OrderEventEnum> transition, StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine) {
        if (message != null && message.getHeaders().containsKey("group")) {
            Group group = message.getHeaders().get("group", Group.class);
            Assert.isTrue(null != group);
        }
        System.out.println("GroupPersistStateChangeListener============>:"+ transition.getSource().getId().name());
        System.out.println("GroupPersistStateChangeListener============>:"+ transition.getTarget().getId().name());
    }
}
