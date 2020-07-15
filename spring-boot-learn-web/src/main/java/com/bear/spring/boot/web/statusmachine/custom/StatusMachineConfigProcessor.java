package com.bear.spring.boot.web.statusmachine.custom;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 处理器
 * @author: xiankelai
 * @date: 2020-07-10
 * @time: 11:10
 */
public class StatusMachineConfigProcessor<S, E, H> {

    /**
     * 当前状态
     */
    private S currentStatus;

    /**
     * 事件
     */
    private E event;

    /**
     * 处理器
     */
    private H handler;

    /**
     * 次态
     */
    private S nextStatus;

    private final Map<S, StatusMachineConfig<S, E, H>> stateMachineConfigurationMap = new HashMap<>();

    public StatusMachineConfigProcessor source(S s) {
        this.currentStatus = s;
        return this;
    }

    public StatusMachineConfigProcessor event(E e) {
        this.event = e;
        return this;
    }

    public StatusMachineConfigProcessor handler(H h) {
        this.handler = h;
        return this;
    }

    public StatusMachineConfigProcessor target(S s) {
        this.nextStatus = s;
        return this;
    }

    public void build() {

        if (Objects.isNull(this.currentStatus)) {
            throw new StatusMachineException("currentStatus未配置");
        }

        if (Objects.isNull(this.event)) {
            throw new StatusMachineException("event未配置");
        }

        if (Objects.isNull(this.nextStatus)) {
            throw new StatusMachineException("nextStatus未配置");
        }

        StatusMachineConfig<S, E, H> statusMachineConfig = stateMachineConfigurationMap.get(this.currentStatus);
        if (Objects.isNull(statusMachineConfig)) {
            statusMachineConfig = new StatusMachineConfig<>(this.currentStatus);
        }
        statusMachineConfig.getEventHandlerMap().put(this.event, this.handler);
        statusMachineConfig.getNextStatusMap().put(this.event, this.nextStatus);
        stateMachineConfigurationMap.put(this.currentStatus, statusMachineConfig);

        this.currentStatus = null;
        this.event = null;
        this.handler = null;
        this.nextStatus = null;
    }

    public H getHandler(S s, E e) {
        StatusMachineConfig sc = stateMachineConfigurationMap.get(s);
        if (Objects.isNull(sc)) {
            throw new StatusMachineException(String.format("状态：%s 未配置", s.toString()));
        }
        if (Objects.isNull(sc.getEventHandlerMap().get(e))) {
            throw new StatusMachineException(String.format("状态：%s 事件: %s 未配置处理器", s.toString(), e.toString()));
        }
        return (H) sc.getEventHandlerMap().get(e);
    }

    public S getNextStatus(S s, E e) {
        StatusMachineConfig sc = stateMachineConfigurationMap.get(s);
        if (Objects.isNull(sc)) {
            throw new StatusMachineException(String.format("状态：%s 未配置", s.toString()));
        }
        return (S) sc.getNextStatusMap().get(e);
    }

}
