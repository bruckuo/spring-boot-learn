package com.bear.spring.boot.web.statusmachine.custom;

/**
 * @description: 处理器
 * @author: xiankelai
 * @date: 2020-07-10
 * @time: 11:38
 */
public interface Handler<S> {

    /**
     * 业务处理方法
     *
     * @param domain 参数
     * @param s 下一个状态
     */
    void handle(StatusMachineDomain<S> domain, S s);
}
