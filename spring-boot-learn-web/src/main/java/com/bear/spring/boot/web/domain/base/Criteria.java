package com.bear.spring.boot.web.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ description: 扩展
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:34
 */
@Data
public class Criteria implements Serializable {

    public static final String SORT_DIRECTION_ASC = "ASC";

    public static final String SORT_DIRECTION_DESC = "DESC";

    public static final String TABLE_NAME = "TABLE_NAME";

    public static final List<String> ORDER_BY = Lists.newArrayList(SORT_DIRECTION_ASC, SORT_DIRECTION_DESC);

    private static final long serialVersionUID = -1125404285642990101L;

    @JsonIgnore
    private List<KeyValue<String, String>> sortItemMap;

    @JsonIgnore
    private Map<String, Object> extFields;

    public <C extends Criteria> C addExtField(String fieldName, Object filedValue) {
        if (this.extFields == null) {
            this.extFields = new HashMap<>();
        }
        this.extFields.put(fieldName, filedValue);
        return (C) this;
    }

}
