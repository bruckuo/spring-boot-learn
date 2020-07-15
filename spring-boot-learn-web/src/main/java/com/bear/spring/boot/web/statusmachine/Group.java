package com.bear.spring.boot.web.statusmachine;

import lombok.Data;

/**
 * @description:
 * @author: xiankelai
 * @date: 2020-07-14
 * @time: 16:48
 */
@Data
public class Group {
    private int groupId;
    private String groupName;
    private int state;
    private boolean isAdvance;
}
