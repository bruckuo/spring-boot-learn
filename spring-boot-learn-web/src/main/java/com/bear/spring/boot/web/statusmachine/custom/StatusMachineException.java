package com.bear.spring.boot.web.statusmachine.custom;

/**
 * @description: 处理器
 * @author: xiankelai
 * @date: 2020-07-10
 * @time: 11:35
 */
public class StatusMachineException extends RuntimeException {

    private static final long serialVersionUID = -9004654525744427379L;

    public StatusMachineException(String message) {
        super(message);
    }
}
