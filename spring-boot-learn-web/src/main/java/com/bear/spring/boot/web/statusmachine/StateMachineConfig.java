package com.bear.spring.boot.web.statusmachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @description:
 * @author: xiankelai
 * @date: 2020-07-14
 * @time: 16:41
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderStatusEnum, OrderEventEnum> {


    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderEventEnum> states) throws Exception {
        states.withStates()
                .initial(OrderStatusEnum.INIT)
                .states(EnumSet.allOf(OrderStatusEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderEventEnum> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderEventEnum.SUBMIT_ORDER_NO_PAY.getSource()).target(OrderEventEnum.SUBMIT_ORDER_NO_PAY.getTarget()).event(OrderEventEnum.SUBMIT_ORDER_NO_PAY)
                .and()

                .withExternal()
                .source(OrderEventEnum.SUBMIT_ORDER_PAY.getSource())
                .target(OrderEventEnum.SUBMIT_ORDER_PAY.getTarget())
                .event(OrderEventEnum.SUBMIT_ORDER_PAY)
                .and()

                .withExternal()
                .source(OrderEventEnum.PAY.getSource()).target(OrderEventEnum.PAY.getSource()).event(OrderEventEnum.PAY)
                .and()

                .withExternal()
                .source(OrderEventEnum.TAKE_ORDER_TO_DELIVERY.getSource()).target(OrderEventEnum.TAKE_ORDER_TO_DELIVERY.getTarget()).event(OrderEventEnum.TAKE_ORDER_TO_DELIVERY)
                .and()

                .withExternal()
                .source(OrderEventEnum.TAKE_ORDER_TO_PICKUP.getSource()).target(OrderEventEnum.TAKE_ORDER_TO_PICKUP.getTarget()).event(OrderEventEnum.TAKE_ORDER_TO_PICKUP)
                .and()

                .withExternal()
                .source(OrderEventEnum.TAKE_MEDICINE.getSource()).target(OrderEventEnum.TAKE_MEDICINE.getTarget()).event(OrderEventEnum.TAKE_MEDICINE)
                .and()

                .withExternal()
                .source(OrderEventEnum.DELIVERY.getSource()).target(OrderEventEnum.DELIVERY.getTarget()).event(OrderEventEnum.DELIVERY)
                .and()

                .withExternal()
                .source(OrderEventEnum.CONFIRM_RECEIVE.getSource()).target(OrderEventEnum.CONFIRM_RECEIVE.getTarget()).event(OrderEventEnum.CONFIRM_RECEIVE)
                .and()

                .withExternal()
                .source(OrderEventEnum.PAY_TIMEOUT_CANCEL.getSource()).target(OrderEventEnum.PAY_TIMEOUT_CANCEL.getTarget()).event(OrderEventEnum.PAY_TIMEOUT_CANCEL)
                .and()

                .withExternal()
                .source(OrderEventEnum.REFUSE.getSource()).target(OrderEventEnum.REFUSE.getTarget()).event(OrderEventEnum.REFUSE);
    }

}

