package com.bear.spring.boot.web.domain.query;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ description: 分页查询
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:35
 */
@Data
public class Page<T> implements Serializable {
    private static final long serialVersionUID = -8387562513921100674L;
    /**
     * 分页结果总数
     */
    private int total;

    /**
     * 分页结果数据信息
     */
    private List<T> rows;
}
