package com.bear.spring.boot.web.statusmachine.custom;

/**
 * @description: 状态机领域
 * @author: xiankelai
 * @date: 2020-07-10
 * @time: 11:39
 */
public interface StatusMachineDomain<S> {

    /**
     * 获取当前状态
     * @return S：状态
     */
    S getCurrentStatus();

    /**
     * 设置次态
     */
    void setNextStatus(S s);
}
