package com.bear.spring.boot.web.domain.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @ description: 抽象实体
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:34
 */
@Data
public class KeyValue<T, M> implements Serializable {
    private static final long serialVersionUID = -4333467962012235042L;
    private T key;
    private M value;

    public KeyValue(T key, M value) {
        this.key = key;
        this.value = value;
    }
}
