package com.bear.spring.boot.web.service.base;

import com.bear.spring.boot.web.domain.base.Criteria;
import com.bear.spring.boot.web.domain.query.Page;

import java.util.List;

/**
 * @ description: 抽象类
 * @ author: guojiang.x
 * @ created: 2017-05-27 下午3:59
 */
public interface BaseService<M extends Criteria, C extends M, PK> {

    /**
     * 根据主键查询
     * @param id id
     * @return M
     */
    M selectOne(PK id);
    /**
     * 插入
     * @param m m
     * @return int
     */
    int insert(M m);
    /**
     * 批量插入
     * @param list 实体
     * @return int
     */
    int insertBatch(List<M> list);
    /**
     * 更新
     * @param m m
     * @return int
     */
    int update(M m);
    /**
     * 批量更新
     * @param list list
     * @return int
     */
    int updateBatch(List<M> list);
    /**
     * 查询集合
     * @param c 查询条件
     * @return 结果
     */
    List<M> selectList(C c);
    /**
     * 计数
     * @param c 条件
     * @return int
     */
    int count(C c);
    /**
     * 分页查询
     * @param modelQuery 查询条件
     * @param offset 页数 必须大于0
     * @param limit 查询数量 必须大于0
     * @return query
     */
    Page<M> selectPage(C modelQuery, Integer offset, Integer limit);

}
