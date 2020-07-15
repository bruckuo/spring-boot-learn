package com.bear.spring.boot.web.statusmachine.custom;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 处理器
 * @author: xiankelai
 * @date: 2020-07-10
 * @time: 11:00
 */
@Data
public class StatusMachineConfig<S, E, H> {

    /**
     * 当前状态
     */
    private S currentStatus;

    /**
     * 存放事件处理器
     */
    private Map<E, H> eventHandlerMap;

    /**
     * 存放次态
     */
    private Map<E, S> nextStatusMap;

    public StatusMachineConfig(S currentStatus) {
        this.currentStatus = currentStatus;
        this.eventHandlerMap = new HashMap<>();
        this.nextStatusMap = new HashMap<>();
    }
}
