package com.bear.spring.boot.web.statusmachine.custom;

/**
 * @description: 处理器
 * @author: xiankelai
 * @date: 2020-07-10
 * @time: 11:33
 */
public class StatusMachine<S, E, H extends Handler> {

    private StatusMachineConfigProcessor<S, E, H> statusMachineConfigProcessor;

    public StatusMachine(StatusMachineConfigProcessor<S, E, H> statusMachineConfigProcessor) {
        this.statusMachineConfigProcessor = statusMachineConfigProcessor;
    }

    /**
     * 触发状态流转
     *
     * @param domain 参数
     * @param event 事件
     */
    public void transition(StatusMachineDomain<S> domain, E event) {
        S currentState = domain.getCurrentStatus();
        H handler = statusMachineConfigProcessor.getHandler(currentState, event);
        S nextState = statusMachineConfigProcessor.getNextStatus(currentState, event);
        handler.handle(domain, nextState);
    }
}
