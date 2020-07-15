package com.bear.spring.boot.web.statusmachine.service;

import com.bear.spring.boot.web.statusmachine.Group;
import com.bear.spring.boot.web.statusmachine.GroupPersistStateChangeListener;
import com.bear.spring.boot.web.statusmachine.OrderEventEnum;
import com.bear.spring.boot.web.statusmachine.OrderStatusEnum;
import com.bear.spring.boot.web.statusmachine.PersistStateMachineHandler;
import com.bear.spring.boot.web.statusmachine.UserPersistStateChangeListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: xiankelai
 * @date: 2020-07-14
 * @time: 16:58
 */
@Service
public class GroupService {

    @Resource
    private PersistStateMachineHandler handler;
    @Resource
    private GroupPersistStateChangeListener groupPersistStateChangeListener;
    @Resource
    private UserPersistStateChangeListener userPersistStateChangeListener;

    public boolean handleAction(int groupId, String event) {
        Group group = new Group();
        group.setGroupId(groupId);
        group.setAdvance(false);
        group.setGroupName("订单组");
        group.setState(0);
        //发送事件去触发状态机
        handler.handleEventWithState(MessageBuilder.withPayload(OrderEventEnum.valueOf("SUBMIT_ORDER_NO_PAY"))
                .setHeader("group", group).build(), OrderStatusEnum.valueOf(group.getState()));
        group.setState(0);

        handler.handleEventWithState(MessageBuilder.withPayload(OrderEventEnum.valueOf("SUBMIT_ORDER_PAY"))
                .setHeader("group", group).build(), OrderStatusEnum.valueOf(group.getState()));
        return true;
    }
}
