package com.bear.spring.boot.web.service.base;

import com.bear.spring.boot.web.domain.base.Criteria;
import com.bear.spring.boot.web.domain.query.Page;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @ description: 查询抽象
 * @ author: guojiang.x
 * @ created: 2017-05-27 下午4:32
 */
public abstract class AbstractBaseService<M extends Criteria, C extends M, PK> implements BaseService<M, C, PK>{

    public abstract List<M> selectList(C c);

    public abstract int count(C c);

    public Page<M> selectPage(C modelQuery, Integer offset, Integer limit) {
        Page<M> page = new Page<>();
        Assert.notNull(modelQuery, "查询条件不能为空.");
        int count = this.count(modelQuery);
        page.setTotal(count);
        if (count == 0)
            return page;
        page.setRows(this.selectList(this.buildOffset(modelQuery, offset, limit)));
        return page;
    }

    private C buildOffset(C criteria, Integer offset, Integer limit) {
        Assert.notNull(criteria, "查询条件不能为空.");
        Assert.notNull(offset, "页数不能为空.");
        Assert.notNull(limit, "查询条数不能为空.");
        Assert.isTrue(offset > 0, "页数必须大于0");
        Assert.isTrue(limit > 0, "页数必须大于0");
        criteria.addExtField("start", (offset - 1) * limit);
        criteria.addExtField("limit", limit);
        return criteria;
    }

}