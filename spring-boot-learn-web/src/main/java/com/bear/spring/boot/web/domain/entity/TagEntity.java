package com.bear.spring.boot.web.domain.entity;

import com.bear.spring.boot.web.domain.base.Criteria;
import lombok.Data;

import java.util.Date;

@Data
public class TagEntity extends Criteria {
	private static final long serialVersionUID = -8821501224337002254L;
	/**
	 * 自增ID
	 */
	private Long id;
	/**
	 * 标记
	 */
	private String tag;
	/**
	 * 时间偏移
	 */
	private String tagTimeNo;
	/**
	 * 集群
	 */
	private Integer clusterEnv;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 *  来源
	 */
	private Integer source;
	/**
	 * 类型 0:h 1:H 2:其他 默认值:-1
	 */
	private Integer tagType;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}